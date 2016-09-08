import java.io.*;
import java.util.*;
public class Main{
public static void main (String[] args) throws java.lang.Exception
{
	BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
	PrintWriter pw= new PrintWriter(System.out);
	StringTokenizer st;
while(true)

{	 st = new StringTokenizer(r.readLine());
	int R = Integer.parseInt(st.nextToken()), C= Integer.parseInt(st.nextToken());
	if(R==0 && C==0 )break;
	R++;C++;
	int[][] mat = new int[R*C][C*R];
	int cur=0;
	String line;

	for(int j=0;j<R-1;j++)
	{
		line = r.readLine();
		st=  new StringTokenizer(line);
		int k=0;
		for(int i=0;i<C-1;i++)
		{
			String a = st.nextToken(),b=st.nextToken();
			int time = (a.charAt(0)=='0')?0:2520/(a.charAt(0)-'0');
			//System.out.println(a.charAt(0)+" "+ cur+" "+ (cur+1));
			char dir=b.charAt(0);
			
			switch(dir)
			{
				case '*':
					mat[cur][cur+1] = time;
					mat[cur+1][cur] = time;
					break;
				case '>':

					mat[cur][cur+1] = time;
					break;
				case '<':
					mat[cur+1][cur] = time;
					break;
				default:
					System.out.println("___________ "+ dir);
					break;

			}
			cur++;
		}
		line = r.readLine();
		st=  new StringTokenizer(line);
		k=0;
		cur-=(C-1);
		for(int i=0;i<C;i++)
		{
			String a = st.nextToken(),b=st.nextToken();
			int time = (a.charAt(0)=='0')?0:2520/(a.charAt(0)-'0');
			//System.out.println(a.charAt(0)+" "+ cur+" "+ (cur+C));
			char dir=b.charAt(0);
			switch(dir)
			{
				case '*':
					mat[cur][cur+C] = time;
					mat[cur+C][cur] = time;
					break;
				case 'v':

					mat[cur][cur+C] = time;
					break;
				case '^':
					mat[cur+C][cur] = time;
					break;
				default:
					System.out.println("___________ "+ dir);
					break;

			}
			cur++;
		} 
	}
	line = r.readLine();
	st=  new StringTokenizer(line);
	int k=0;
	for(int i=0;i<C-1;i++)
	{
		String a = st.nextToken(),b=st.nextToken();
			int time = (a.charAt(0)=='0')?0:2520/(a.charAt(0)-'0');
			//System.out.println(a.charAt(0)+" "+ cur+" "+ (cur+1));
			char dir=b.charAt(0);
		switch(dir)
		{
			case '*':
				mat[cur][cur+1] = time;
				mat[cur+1][cur] = time;
				break;
			case '>':

				mat[cur][cur+1] = time;
				break;
			case '<':
				mat[cur+1][cur] = time;
				break;
			default:
					System.out.println("___________ "+ dir);
					break;

		}
		cur++;
	}
	//go from 0 to R*C-1
	class Ob
	{
		int v;
		int dist;
		Ob(int v,int d)
		{
			this.v=v;
			dist=d;
		}
	}
	PriorityQueue<Ob> pq =new PriorityQueue<Ob>(new Comparator<Ob>(){
		public int compare(Ob a,Ob b)
		{
			return a.dist-b.dist;
		}
	});
	pq.add(new Ob(0,0));
	boolean[] done  = new boolean[R*C];
	int[] dist= new int[R*C];
	for(int i=0;i<R*C;i++) dist[i] = Integer.MAX_VALUE;
	
	dist[0]=0;
	while(!pq.isEmpty())
	{
		Ob cur1  = pq.poll();
		int v = cur1.v;
		if(dist[v]==Integer.MAX_VALUE) break;
		if(done[v]==true) continue;
		done[v]=true;
		//int a=-1,b=-1,c=-1,d=-1;
		if(v%C!=0){
			if(mat[v][v-1]!=0 && !done[v-1] && dist[v-1]> dist[v]+mat[v][v-1])
			{
				dist[v-1] = dist[v]+mat[v][v-1];
				pq.add(new Ob(v-1,dist[v-1]));
			}
		}
		if(v%C!=C-1){
			if(mat[v][v+1]!=0 && !done[v+1] && dist[v+1]> dist[v]+mat[v][v+1])
			{
				dist[v+1] = dist[v]+mat[v][v+1];
				pq.add(new Ob(v+1,dist[v+1]));
			}
		}
		if(v>C-1){
			if(mat[v][v-C]!=0 && !done[v-C] && dist[v-C]> dist[v]+mat[v][v-C])
			{
				dist[v-C] = dist[v]+mat[v][v-C];
				pq.add(new Ob(v-C,dist[v-C]));
			}
		}
		if(v+C<R*C){
			if(mat[v][v+C]!=0 && !done[v+C] && dist[v+C]> dist[v]+mat[v][v+C])
			{
				dist[v+C] = dist[v]+mat[v][v+C];
				pq.add(new Ob(v+C,dist[v+C]));
			}
		}
	}
	if(dist[R*C-1]==Integer.MAX_VALUE)  pw.println("Holiday");
	else pw.println(dist[R*C-1]+" blips");
}
pw.close();
return;
}
}