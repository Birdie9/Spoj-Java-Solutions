import java.util.*;
import java.util.StringTokenizer;
 
import java.io.*;
public class Main {
	//static long mod= 1000000000;
	static int[] rank;
	static int[] par;
	static int[] num;
	public static void createset(int a)
	{
		par[a]=a;
		rank[a]=0;
		num[a]=1;
		return;
	}
	public static int findset(int a)
	{
		if(a!=par[a]) par[a] = findset(par[a]);
		return par[a];
	}
	public static long union(int a,int b)
	{
		int pa = findset(a);
		int pb=findset(b);
		if(pa==pb) return  num[pa];
		long ret=0;
		if(rank[pa]>rank[pb]) {par[pb]=pa; ret = num[pa] +num[pb]; num[pa]=num[pb]+num[pa];}
		else{ par[pa] = pb; ret =  num[pa]+num[pb]; num[pb] += num[pa];}
		if(rank[pa]==rank[pb]) rank[pb]++;
		return ret;
	}
	public static void main(String[] args)throws java.lang.Exception {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st = new StringTokenizer(r.readLine());
		int test= Integer.parseInt(st.nextToken());
		for(int i=0;i<test;i++)
		{
			st = new StringTokenizer(r.readLine());
			int n= Integer.parseInt(st.nextToken());
			rank = new int[2*n];
			par=new int[2*n];
			num = new int[2*n];
			HashMap<String,Integer> map = new HashMap<String, Integer>();
			int key=0;
			for(int j=0;j<n;j++)
			{
				st = new StringTokenizer(r.readLine());
				String s1=st.nextToken(), s2=st.nextToken();
				int p1,p2;
				if(map.containsKey(s1))
					p1 = map.get(s1);
				else 
				{
					map.put(s1, key++);
					p1=key-1;
					createset(p1);
				}
				if(map.containsKey(s2))
					p2 = map.get(s2);
				else 
				{
					map.put(s2, key++);
					p2=key-1;
					createset(p2);
				}
				pw.println(union(p1,p2));

			}
		}
		
		pw.close();
	}
}