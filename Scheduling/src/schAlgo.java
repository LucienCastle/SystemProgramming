import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;
import java.util.function.Consumer;


class job{
	int pid,arrival_time,burst_time;
	int turnaround_time,waiting_time,completion_time,rem_burst;
	boolean flag=false;
	int priority;
	public job(int pid,int arrival_time,int burst_time){
		this.pid=pid;
		this.arrival_time=arrival_time;
		this.burst_time=burst_time;
		rem_burst=burst_time;
		priority=-1;
	}
	
	public void print_job(){
		System.out.println(pid+"\t"+arrival_time+"\t"+burst_time+"\t"+completion_time+"\t"+turnaround_time+"\t"+waiting_time);
	}
	
	public void reset(){
		System.out.println("Reset...");
		turnaround_time=0;
		waiting_time=0;
		completion_time=0;
		rem_burst=burst_time;
	}
}

class sort_by_pid implements Comparator<job>{
	public int compare(job o1, job o2) {
		// TODO Auto-generated method stub
		return o1.arrival_time-o2.arrival_time;
	}
}

class sort_by_priority implements Comparator<job>{
	public int compare(job o1, job o2) {
		// TODO Auto-generated method stub
		if(o1.arrival_time==o2.arrival_time)
			return o1.priority-o2.priority;
		else
			return o1.arrival_time-o2.arrival_time;
	}
}

public class schAlgo {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter number of jobs:");
		int num=sc.nextInt(),n=0,pid,art,bst;
		Vector<job>schedule=new Vector<job>();
		job j;
		while(n<num){
			System.out.println("Enter PID of job:"+(n+1));
			pid=sc.nextInt();
			System.out.println("Enter Arrival Time of job:"+(n+1));
			art=sc.nextInt();
			System.out.println("Enter Burst Time of job:"+(n+1));
			bst=sc.nextInt();
			j=new job(pid,art,bst);
			schedule.add(j);
			n++;
		}
		
		Iterator<job> it;
		System.out.println("Show Menu?(yes or no)");
		String ans=sc.next();
		while(ans.equalsIgnoreCase("yes"))
		{
			System.out.println("Choose Sorting algortihm:\n1.FCFS\n2.SJF(Preemptive)\n3.Priority(Non-preemptive)\n4.RoundRobin(Preemptive)");
			int choice=sc.nextInt();
			float total_ta=0,total_wt=0;
			float avg_wt,avg_ta;
			switch(choice)
			{
			case 1:schedule.forEach(new Consumer<job>(){
						public void accept(job t) {
							// TODO Auto-generated method stub
							t.reset();
						}
				    });
				   Collections.sort(schedule,new sort_by_pid());
				   for(int i=0;i<schedule.size();i++)
				   {
						if(i==0)
							schedule.get(i).completion_time=schedule.get(i).arrival_time+ schedule.get(i).burst_time;
						else{
							if(schedule.get(i).arrival_time>schedule.get(i-1).completion_time)
								schedule.get(i).completion_time=schedule.get(i).arrival_time+schedule.get(i).burst_time;
							else
								schedule.get(i).completion_time=schedule.get(i-1).completion_time+schedule.get(i).burst_time;
						}
						schedule.get(i).turnaround_time=schedule.get(i).completion_time-schedule.get(i).arrival_time;
						schedule.get(i).waiting_time=schedule.get(i).turnaround_time-schedule.get(i).burst_time;
						total_wt+=schedule.get(i).waiting_time;
						total_ta+=schedule.get(i).turnaround_time;
				   }
				   System.out.println("\npid  arrival  brust  complete turn waiting");
				   it=schedule.iterator();
				   while(it.hasNext()){
					   it.next().print_job();
				   }
				   avg_wt=(total_wt/num);
				   avg_ta=(total_ta/num);
				   System.out.println("Average waiting time:"+avg_wt);
				   System.out.println("Average turnaround time:"+avg_ta);
				   break;
			case 2:schedule.forEach(new Consumer<job>(){
						public void accept(job t) {
							// TODO Auto-generated method stub
							t.reset();
						}
		    	   });
				   int tot=0,st=0;
				   while(true)
				   {
						int min=99,c=num;
						if(tot==num)
							break;
						for(int i=0;i<num;i++)
						{
							if((schedule.get(i).arrival_time<=st)&&(!schedule.get(i).flag)&&(schedule.get(i).burst_time<min))
							{
								min=schedule.get(i).burst_time;
								c=i;
							}
						}
						
						if(c==num)
							st++;
						else{
							schedule.get(c).rem_burst--;
							st++;
							if(schedule.get(c).rem_burst==0)
							{
								schedule.get(c).completion_time=st;
								schedule.get(c).flag=true;
								tot++;
							}
						}
						
				    }
			        total_ta=0;
			        total_wt=0;
				    for(int i=0;i<schedule.size();i++)
				    {
					    schedule.get(i).turnaround_time=schedule.get(i).completion_time-schedule.get(i).arrival_time;
					    schedule.get(i).waiting_time=schedule.get(i).turnaround_time-schedule.get(i).burst_time;
					    total_wt+=schedule.get(i).waiting_time;
					    total_ta+=schedule.get(i).turnaround_time;
				    }
				    System.out.println("\npid  arrival  brust  complete turn waiting");
				    it=schedule.iterator();
				    while(it.hasNext()){
				   	   it.next().print_job();
				    }
				    avg_wt=(total_wt/num);
					avg_ta=(total_ta/num);
					System.out.println("Average waiting time:"+avg_wt);
					System.out.println("Average turnaround time:"+avg_ta);
				    break;
			case 3:schedule.forEach(new Consumer<job>(){
						public void accept(job t) {
							// TODO Auto-generated method stub
							t.reset();
						}
		    	   });
				   for(int i=0;i<schedule.size();i++)
				   {
						System.out.println("\nEnter priority for job "+(i+1));
						schedule.get(i).priority=sc.nextInt();
				   }
				   Collections.sort(schedule,new sort_by_priority());
				   for(int i=0;i<schedule.size();i++)
				   {
						if(i==0)
							schedule.get(i).completion_time=schedule.get(i).arrival_time+ schedule.get(i).burst_time;
						else{
							if(schedule.get(i).arrival_time>schedule.get(i-1).completion_time)
								schedule.get(i).completion_time=schedule.get(i).arrival_time+schedule.get(i).burst_time;
							else
								schedule.get(i).completion_time=schedule.get(i-1).completion_time+schedule.get(i).burst_time;
						}
						schedule.get(i).turnaround_time=schedule.get(i).completion_time-schedule.get(i).arrival_time;
						schedule.get(i).waiting_time=schedule.get(i).turnaround_time-schedule.get(i).burst_time;
						total_wt+=schedule.get(i).waiting_time;
						total_ta+=schedule.get(i).turnaround_time;
				   }
				   System.out.println("\npid  arrival  brust  complete turn waiting");
				   it=schedule.iterator();
				   while(it.hasNext()){
					   it.next().print_job();
				   }
				   avg_wt=(total_wt/num);
				   avg_ta=(total_ta/num);
				   System.out.println("Average waiting time:"+avg_wt);
				   System.out.println("Average turnaround time:"+avg_ta);
				   break;
			case 4:schedule.forEach(new Consumer<job>(){
						public void accept(job t) {
							// TODO Auto-generated method stub
							t.reset();
						}
		    		});
					System.out.println("Enter time quantum :");
					int quant=sc.nextInt();
					int t=0;
					while(true)
					{
						boolean done=true;
						for(int i=0;i<schedule.size();i++)
						{
							if(schedule.get(i).rem_burst>0)
							{
								done=false;
								if(schedule.get(i).rem_burst>quant)
								{
									t+=quant;
									schedule.get(i).rem_burst-=quant;
									for(int j1=0;j1<schedule.size();j1++)
									{
										if((j1!=i)&&schedule.get(j1).burst_time!=0)
											schedule.get(i).waiting_time+=quant;
									}
								}
								else{
									t+=schedule.get(i).rem_burst;
									schedule.get(i).waiting_time=t-schedule.get(i).burst_time;
									schedule.get(i).completion_time=t;
									schedule.get(i).rem_burst=0;
								}
							}
						}
						if(done==true)
						{
							total_ta=0;
					        total_wt=0;
						    for(int i=0;i<schedule.size();i++)
						    {
							    schedule.get(i).turnaround_time=schedule.get(i).burst_time+schedule.get(i).waiting_time;
							    total_wt+=schedule.get(i).waiting_time;
							    total_ta+=schedule.get(i).turnaround_time;
						    }
							break;
						}
					}
					System.out.println("\npid  arrival  brust  complete turn waiting");
					it=schedule.iterator();
					while(it.hasNext()){
						it.next().print_job();
					}
					avg_wt=(total_wt/num);
					avg_ta=(total_ta/num);
					System.out.println("Average waiting time:"+avg_wt);
					System.out.println("Average turnaround time:"+avg_ta);
					break;
			default:System.out.println("\nWrong choice");
					break;
			}
			System.out.println("Show Menu?(yes or no)");
			ans=sc.next();
		}
		// TODO Auto-generated method stub

	}

}
