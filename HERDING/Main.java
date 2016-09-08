import java.io.*;
import java.util.*;
class Co
{
	int i,j;
	Co(int a,int b)
	{
		i=a;
		j=b;
	}
}
public class Main
{
	public static void main(String[] args) throws java.lang.Exception
	{
		BufferedReader r= new BufferedReader(new InputStreamReader(System.in));
		String line  = r.readLine();
		StringTokenizer st = new StringTokenizer(line);
		int n = Integer.parseInt(st.nextToken()),m=Integer.parseInt(st.nextToken());
		char[][] mat = new char[n][m];
		for(int i=0;i<n;i++)
		{
			mat[i] = r.readLine().toCharArray();
		}
		Stack<Co> stack = new Stack<Co>();
		int count=0;
		
		boolean[][] done = new boolean[n][m];
		boolean[][] trap = new boolean[n][m];
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<m;j++)
			{
				if(!done[i][j])
				{
					
					stack.push(new Co(i,j));
					done[i][j]=true;
					while(!stack.empty())
					{
						Co cur = stack.peek();
						boolean isLeaf=true;
						int nexti=cur.i,nextj=cur.j;
						switch(mat[cur.i][cur.j])
						{
							case 'S':
							nexti++;
							if(!done[cur.i+1][cur.j]){ stack.push(new Co(cur.i+1,cur.j));
							isLeaf=false;
							done[cur.i+1][cur.j]=true;
							}
							break;
							case 'N':
							nexti--;
							if(!done[cur.i-1][cur.j]){ stack.push(new Co(cur.i-1,cur.j));
							isLeaf=false;
							done[cur.i-1][cur.j]=true;
							}
							break;
							case 'E':
							nextj++;
							if(!done[cur.i][cur.j+1]){ stack.push(new Co(cur.i,cur.j+1));
							isLeaf=false;
							done[cur.i][cur.j+1]=true;
							}
							break;
							case 'W':
							nextj--;
							if(!done[cur.i][cur.j-1]){ stack.push(new Co(cur.i,cur.j-1));
							isLeaf=false;
							done[cur.i][cur.j-1]=true;
							}
							break;

						}
						if(isLeaf)
						{
							stack.pop();
							trap[cur.i][cur.j]=true;
						}
						if(isLeaf&& !trap[nexti][nextj])
						{
							count++;
							//System.out.println()
						}
						
					}
				}
			}
		}
		
		System.out.println(count);
		return;

	}
}