import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

public class pageRep {

	static int total_pages;
	static int total_frames;
//	static int frames[];
	static Vector<Integer> frames;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the total number of pages: ");
		total_pages=sc.nextInt();
		System.out.println("Enter the page numbers :");
		Vector<Integer> page_num=new Vector<Integer>();
		for(int i=0;i<total_pages;i++)
		{
			int num=sc.nextInt();
			page_num.add(num);
		}
		
		System.out.println("Enter total number of frames (<"+total_pages+"):");
		total_frames=sc.nextInt();
		frames=new Vector<Integer>();
		for(int i=0;i<total_frames;i++)
			frames.add(-1);
		
		HashMap<Integer,Integer>age;
		
		Iterator<Integer> it,itf;
		int fault=0;
		float frate;
		System.out.println("Show Menu?(yes or no)");
		String choice=sc.next();
		while(choice.equalsIgnoreCase("yes"))
		{
			System.out.println("MENU:\n1.FIFO\n2.LRU\n3.Optimal Algorithm");
			int option=sc.nextInt();
			switch(option)
			{
			case 1: System.out.println("Pages in main memory:\n");
					for(int i=0;i<total_frames;i++)
						frames.set(i,-1);
			
					int k=0;
					it=page_num.iterator();
					while(it.hasNext())
					{
						int temp=it.next();
						if(!frames.contains(temp))
						{
							frames.set(k, temp);
							k+=1;
							fault+=1;
							if(k==total_frames)
								k=0;
							itf=frames.iterator();
							while(itf.hasNext())
							{
								System.out.print(itf.next()+"\t");
							}
							System.out.println("\n");
						}
					}
					frate=(float)fault/(float)total_pages;
					System.out.println("Fault Rate:"+(frate));
					break;
			case 2:	System.out.println("Pages in main memory:\n");
					for(int i=0;i<total_frames;i++)
						frames.set(i,-1);
					
					age=new HashMap<Integer,Integer>();
					it=page_num.iterator();
					int l=0,a=0;
					fault=0;
					while(it.hasNext())
					{
						int temp=it.next();
						if(l<frames.size())
						{
							if(!frames.contains(temp))
							{
								frames.set(l, temp);
								l+=1;
								fault+=1;
							}
							age.put(temp, a);
						}
						else {
							if(!frames.contains(temp))
							{
								int lru=Integer.MAX_VALUE,val=Integer.MIN_VALUE;
								itf=frames.iterator();
								while(itf.hasNext())
								{
									int tempf=itf.next();
									if(age.get(tempf)<lru)
									{
										lru=age.get(tempf);
										val=tempf;
									}
								}
								age.remove(val);
								frames.set(frames.indexOf(val), temp);
								fault+=1;
							}
							age.put(temp, a);
						}
						itf=frames.iterator();
						while(itf.hasNext())
						{
							System.out.print(itf.next()+"\t");
						}
						System.out.println("\n");
						a+=1;
					}
					frate=(float)fault/(float)total_pages;
					System.out.println("Fault Rate:"+(frate));
					break;
			case 3:	System.out.println("Pages in main memory:\n");
					for(int i=0;i<total_frames;i++)
						frames.set(i,-1);
			
					it=page_num.iterator();
					fault=0;
					int m=0,p=0;
					while(it.hasNext())
					{
						int temp=it.next();
						if(m<frames.size())
						{
							if(!frames.contains(temp))
							{
								frames.set(m, temp);
								m+=1;
								fault+=1;
							}
						}
						else {
							if(!frames.contains(temp))
							{
								int farthest=frames.firstElement(),max=0;
								itf=frames.iterator();
								while(itf.hasNext()) {
									int tempf=itf.next();
									int index=page_num.indexOf(tempf, p+1);
									if(index==-1)
									{
										farthest=tempf;
										break;
									}
									else{
										if(index>max) {
											max=index;
											farthest=tempf;
										}
									}
								}
								frames.set(frames.indexOf(farthest), temp);
								fault++;
							}
						}
						itf=frames.iterator();
						while(itf.hasNext())
						{
							System.out.print(itf.next()+"\t");
						}
						System.out.println("\n");
						p+=1;
					}
					frate=(float)fault/(float)total_pages;
					System.out.println("Fault Rate:"+(frate));
					break;
			default:System.out.println("Invalid choice");
			}
			System.out.println("Show Menu Again(yes or no)");
			choice=sc.next();
		}
		sc.close();
	}
}
