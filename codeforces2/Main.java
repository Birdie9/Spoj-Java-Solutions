import java.io.*;
import java.util.*;
public class Main
{
	class Node{
		int val, v;
		ArrayList<Node> out;
		ArrayList<Node> in;
		ArrayList<Node> same;
		int i,j;
		Node leader;
		boolean done;
		Node(int v, int i,int j){
			this.i=i;
			this.j=j;
			this.v=v;
			done=false;
			val=1;
			out = new ArrayList<Node>();
			in =  new ArrayList<Node>();
			same = new ArrayList<Node>();
		}
		public String toString()
		{
			return i+" "+j;
		}
	}


	static boolean[][] done;
	public static int max(int a,int b)
	{
		return a>b?a:b;
	}
	public void do1() throws java.lang.Exception
	{
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw= new PrintWriter(System.out);
		StringTokenizer st =new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken()), m=Integer.parseInt(st.nextToken());
		//int[][] org = new int[n][m];
		Node[][] mat= new Node[n][m];
		done= new boolean[n][m];
		Stack<Node> stk  = new Stack<Node>();
		for(int i=0;i<n;i++)
		{
			 st =new StringTokenizer(r.readLine());
			 for(int j=0;j<m;j++)
			 {
			 	int v=Integer.parseInt(st.nextToken());
			 	//org[i][j]=v;
			 	mat[i][j] = new Node(v,i,j);
			 }
		}
		Node[][] temp1 = new Node[n][m];
		Node[][] temp2=new Node[m][n];
		for(int i=0;i<n;i++)
		{
			Node[] temp = temp1[i];
			for(int j=0;j<m;j++) temp[j]=mat[i][j];
			Arrays.sort(temp, new Comparator<Node>(){
				public int compare(Node a, Node b)
				{
					return a.v-b.v;
				}
			});
			for(int j=0;j<m-1;j++)
			{
				if(temp[j].v==temp[j+1].v)
				{
					temp[j].same.add(temp[j+1]);
					temp[j+1].same.add(temp[j]);
				}
			}
		}
		for(int i=0;i<m;i++)
		{
			Node[] temp = temp2[i];
			for(int j=0;j<n;j++) temp[j]=mat[j][i];
			Arrays.sort(temp, new Comparator<Node>(){
				public int compare(Node a, Node b)
				{
					return a.v-b.v;
				}
			});

			for(int j=0;j<n-1;j++)
			{
				if(temp[j].v==temp[j+1].v) 
				{
					temp[j].same.add(temp[j+1]);
					temp[j+1].same.add(temp[j]);
				}
			}
		}
		ArrayList<Node> graph= new ArrayList<Node>();
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<m;j++)
			{
				if(!done[i][j])
				{
					done[i][j]=true;
					mat[i][j].leader=mat[i][j];
					graph.add(mat[i][j]);
					//System.out.println(mat[i][j].i+" "+mat[i][j].j);
					
					for(Node s:mat[i][j].same)
					{
						if(!done[s.i][s.j])
						dfs(s,mat[i][j]);
					}
					//if(mat[i][j].in.size()==0) stk.push(mat[i][j]);
					//System.out.println(mat[i][j].out);
					//System.out.println(mat[i][j].in);
				}
			}
		}
		for(int i=0;i<n;i++)
		{
			Node[] temp = temp1[i];

			for(int j=0;j<m-1;j++)
			{
				if(temp[j].v<temp[j+1].v)
				{
					temp[j].leader.out.add(temp[j+1].leader);
					temp[j+1].leader.in.add(temp[j].leader);
				}
			}
		}
		for(int i=0;i<m;i++)
		{
			Node[] temp = temp2[i];


			for(int j=0;j<n-1;j++)
			{
				if(temp[j].v<temp[j+1].v)
				{
					temp[j].leader.out.add(temp[j+1].leader);
					temp[j+1].leader.in.add(temp[j].leader);
				}
			}
		}
		
		for(Node r1:graph)
		{
			if(r1.in.size()==0) topsort(r1, 1);
		}
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<m;j++)
			{
				//System.out.println(mat[i][j]);
				//System.out.println( mat[i][j].leader);
				pw.print(mat[i][j].leader.val+" ");
			}
			pw.println("");
		}
		pw.close();
	}
	public static void main(String[] args) throws java.lang.Exception
	{
		Main m = new Main();
		m.do1();
		return;
	}

	void topsort(Node r,int val)
	{
		if(r.val<val) r.done=false;
		r.val=max(r.val, val);
		//Stack<Node> st = new Stack<Node>();
		if(!r.done )
		for(Node out:r.out)
		{
			topsort(out,r.val+1);
		}
		r.done=true;
	}
	void dfs(Node cur, Node leader)
	{
		done[cur.i][cur.j]=true;
		cur.leader=leader;
		Stack<Node> st = new Stack<Node>();
		st.push(cur);
		while(!st.isEmpty())
		{
			Node n = st.pop();
			for(Node s:n.same)
			{
				if(!done[s.i][s.j]) 
				{
					done[s.i][s.j]=true;
					s.leader=leader;
					st.push(s);
				}
			}
		}
		return;
	}
}

