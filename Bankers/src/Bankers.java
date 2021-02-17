
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Bankers 
{
	HashMap<String, Resource> allocated;
	HashMap<String, Resource> maxNeed;
	HashMap<String, Resource> Remaining;
	Resource available;
	BufferedReader read;

	public Bankers()
	{
		try
		{
			read = new BufferedReader(new FileReader("maxNeed.txt"));
			maxNeed = new HashMap<String, Resource>();
			String line = "";
			while((line = read.readLine()) != null)
			{
				String[] parts = line.split("\\s+");
				if(parts.length != 4) 
				{
					throw new IOException("Invalid input format in maxNeed.txt");
				}
				maxNeed.put(parts[0], new Resource(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3])));
			}
			read = new BufferedReader(new FileReader("allocated.txt"));
			allocated = new HashMap<String, Resource>();
			Remaining = new LinkedHashMap<String, Resource>();
			while((line = read.readLine()) != null)
			{
				String[] parts = line.split("\\s+");
				if(parts.length != 4) 
				{
					throw new IOException("Invalid input format in allocated.txt");
				}
				Resource temp = maxNeed.get(parts[0]);
				if(temp==null) 
				{
					throw new IOException("Entries in input files mismatch");
				}
				Remaining.put(parts[0], new Resource(temp.getA()-Integer.parseInt(parts[1]), temp.getB()-Integer.parseInt(parts[2]), temp.getC()-Integer.parseInt(parts[3])));
				allocated.put(parts[0], new Resource(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3])));
			}
			read.close();
			if(maxNeed.size() != allocated.size())
			{
				throw new IOException("Different number of entries in input files");
			}
			read = new BufferedReader(new FileReader("system.txt"));
			if((line = read.readLine()) != null)
			{
				String[] parts = line.split("\\s+");
				if(parts.length != 3) 
				{
					throw new IOException("Invalid input format in system.txt");
				}
				available = new Resource(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
			}
			else 
			{
				throw new IOException("Invalid input format in system.txt");
			}
			read.close();
		}
		catch(IOException e) 
		{
			System.out.println("Error while reading input files");
			System.err.println(e);
			System.exit(1);
		}
	}

	public void isSafe()
	{
		int count = 0, processCnt = allocated.size();
		boolean[] visited = new boolean[processCnt];
		for(int i=0; i<processCnt; i++)
		{
			visited[i] = false;
		}
		
		ArrayList<String> safeSequence = new ArrayList<String>();
		ArrayList<String> processes = new ArrayList<String>(Remaining.keySet());
		
		while(count < processCnt)
		{
			boolean flag = true;
			for(int i=0; i<processCnt; i++)
			{
				if(!visited[i])
				{
					
					if(Remaining.get(processes.get(i)).isZero())
					{
						safeSequence.add(processes.get(i));
						visited[i] = true;
						count++;
						flag = false;
						available.addResource(allocated.get(processes.get(i)));
					}
					else if(!Remaining.get(processes.get(i)).greaterThan(available))
					{
						safeSequence.add(processes.get(i));
						visited[i] = true;
						count++;
						flag = false;
						available.addResource(allocated.get(processes.get(i)));
					}
				}
			}
			if(flag)
			{
				break;
			}
		}
	
		if(count < processCnt)
		{
			System.err.println("System is unsafe");
		}
		else
		{
			System.out.println("Following is the safe sequence");
			for(int i=0; i<processCnt; i++)
			{
				System.out.print(safeSequence.get(i));
				if(i==processCnt-1)
				{
					System.out.println("\n");
				}
				else
				{
					System.out.print(" -> ");
				}
			}
		}
	}
}