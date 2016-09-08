import java.io.*;
import java.util.*;

public class Main{
	static char[][] mat;
	static boolean[][] done;
	//static in n;
	static int n,m;
	static boolean bfs(int ox)
	{
		int si=0,sj=0,ti=0,tj=0;
		int[][] adj = {{1,1,1,0,0,-1,-1,-1},
					   {1,-1,0,1,-1,0,1,-1}};
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<m;j++)
			{
				if(mat[i][j]=='S')
				{
					si=i;
					sj=j;
				}
				else if(mat[i][j]=='T')
				{
					ti=i;
					tj=j;
				}
			}
		}
		Queue<Co> q = new LinkedList<Co>();
		q.add(new Co(si,sj,ox));
		while(!q.isEmpty())
		{
			Co cur = q.poll();
			//System.out.println(cur.i+" "+ cur.j);
			for(int i=0;i<8;i++)
			{
				int ni = cur.i + adj[0][i];
				int nj= cur.j +adj[1][i];
				if(valid(ni,nj) ) 
				{
					done[ni][nj]=true;
					if(ni==ti && nj==tj)
					{
						if(cur.o >0)
							return true;
						return false;
					}
					q.add(new Co(ni,nj,cur.o-1));
				}
			}
		}
		return false;
	}
	static boolean valid(int i,int j){if( (i>=0) && (j>=0) && (i<n) && (j<m) && (!done[i][j]) && (mat[i][j]!='#') ) return true;
	return false; }
	public static void main(String[] args)
	{
		InputReader r =new InputReader(new BufferedInputStream(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		//int n,m,i,j;
		int i,j;
		int t;
		t=r.readInt();
		for(;t>0;t--)
		{
			int ox = r.readInt();
			n = r.readInt();
			m= 	r.readInt();
			//int si,sj;
			mat= new char[n][m];
			done=new boolean[n][m];
			for( i=0;i<n;i++)
			{
				mat[i] = r.readA(m);
				
			}
			if(bfs(ox)) pw.println("Possible");
			else pw.println("Impossible"); 
	}
		pw.close();
		return;
	}
}
