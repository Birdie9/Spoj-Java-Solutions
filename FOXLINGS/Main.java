import java.io.*;
import java.util.*;
public class Main{
	static boolean[] done;
	static ArrayList<ArrayList<Integer>> adj;
	public static void main(String[] args) throws java.lang.Exception
	{
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int cnt=0;
		//int[] par = new int[n+1];
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		int k=-1;
		adj = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<m;i++)
		{
			st = new StringTokenizer(r.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(!map.containsKey(a)){map.put(a,++k); adj.add(new ArrayList<Integer>());}
			if(!map.containsKey(b)){map.put(b,++k); adj.add(new ArrayList<Integer>());}
			adj.get(map.get(a)).add(map.get(b));
			adj.get(map.get(b)).add(map.get(a));
		}
		cnt = n-k-1;
		if(k<0){System.out.println(cnt); return;}
		done = new boolean[k+1];
		for(int i=0;i<k;i++)
		{
			if(!done[i]) 
				{
					bfs(i);
					cnt++;
				}
		}
		System.out.println(cnt);
		return;
	}
	public static  void bfs(int src)
	{
		done[src]=true;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(src);
		while(!q.isEmpty())
		{
			int cur = q.poll();
			for(int i:adj.get(cur))
				if(!done[i])
				{
					done[i]=true;
					q.add(i);
				}
		}
	}
}