import java.io.*;
import java.util.*;
class InputReader {
    
	private BufferedInputStream stream;
	private byte[] buf = new byte[1024];
	private int curChar;
	private int numChars;

	public InputReader(BufferedInputStream stream) {
		this.stream = stream;
	}

	public int read() {
		if (numChars == -1)
			throw new InputMismatchException();
		if (curChar >= numChars) {
			curChar = 0;
			try {
				numChars = stream.read(buf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (numChars <= 0)
				return -1;
		}
		return buf[curChar++];
	}

	public int readInt() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		int sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = read();
		}
		int res = 0;
		do {
			if (c < '0' || c > '9')
				throw new InputMismatchException();
			res *= 10;
			res += c - '0';
			c = read();
		} while (!isSpaceChar(c));
		return res * sgn;
	}
	public boolean isSpaceChar(int c) {
		return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
	}
}

class Co
{
	int x,y;
	int d;
	Co(int a,int b,int c)
	{
		x=a;
		y=b;
		d=c;
	}
}
public class Main

{
	static int[][] dp;
	static boolean valid(int i,int j,char[][] mat, boolean[][] done, int n,int m)
	{
		return i>=0 && j>=0 && i<n && j<m && mat[i][j]!='x' && !done[i][j];
	}
	static void print(int[] a, int k)
	{
		for(int i=0;i<k;i++) System.out.print(a[i]+" ");
			System.out.println();
	}
	static int[] bfs(int si,int sj, int tot, char[][] mat,int n,int m,int[] map)
	{
		int[] ret = new int[tot];
		for(int i=0;i<tot;i++) ret[i]=Integer.MAX_VALUE;

		boolean[][] done = new boolean[n][m];
		ret[map[si*m+sj]]=0;

		done[si][sj]=true;
		tot--;
		Queue<Co> q= new LinkedList<Co>();
		q.add(new Co(si,sj,0));
		while(!q.isEmpty())
		{
			Co cur = q.poll();
			int x=cur.x, y=cur.y, d=cur.d;
			int[][] adj = {{1,-1,0,0},
						   {0,0,1,-1}};
			for(int i=0;i<4;i++)
			{
				int x1=x+adj[0][i], y1=y+adj[1][i];
				if(valid(x1, y1, mat, done,n,m))
				{
					done[x1][y1]=true;
					if(mat[x1][y1]=='*')
					{
						tot--;
						ret[map[m*x1+y1]] = d+1;
						if(tot==0) {return ret;	}
					}
					q.add(new Co(x1,y1,d+1));
				}
			}
		}
	
		return ret;
	}
	
	public static void main(String[] args) throws java.lang.Exception
	{
		PrintWriter pw = new PrintWriter(System.out);
		//InputReader r= new InputReader(new BufferedInputStream(System.in));
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			int n,m;
			StringTokenizer st = new StringTokenizer(r.readLine());
			m=Integer.parseInt(st.nextToken());
			n=Integer.parseInt(st.nextToken());
			if(n==0 && m==0) break;
			char[][]  mat = new char[n][m];
			int[] map = new int[n*m];
			for(int i=0;i<n*m;i++) map[i]=-1;
			int si=-1,sj=-1;
			ArrayList<Integer> dirty = new ArrayList<Integer>();
			int bit=0;
			for(int i=0;i<n;i++)
			{
				mat[i] = r.readLine().toCharArray();
				for(int j=0;j<m;j++) 
				{
					if(mat[i][j]=='o')
						{si=i;
						sj=j;}
					else if(mat[i][j]=='*')
					{
						dirty.add(m*i+j);
						map[m*i+j] =bit;
						bit++;
					}
					
				}
			}
			//print(map, n*m);
			dp = new int[bit][1<<bit];
			for(int i=0;i<bit;i++) for(int j=0;j<(1<<bit);j++) dp[i][j]=-1;
			int[][] apsp =  new int[bit][bit];
			for(int i:dirty)
			{
				apsp[map[i]] = bfs(i/m, i%m , bit, mat,n,m,map);
				//print(apsp[map[i]], bit);
			}
			int[] pfs = new int[bit];
			int tot = bit;
			for(int i=0;i<tot;i++) pfs[i]=Integer.MAX_VALUE;

			boolean[][] done = new boolean[n][m];
			done[si][sj]=true;

			Queue<Co> q= new LinkedList<Co>();
			q.add(new Co(si,sj,0));
			while(!q.isEmpty())
			{
				Co cur = q.poll();
				int x=cur.x, y=cur.y, d=cur.d;
				int[][] adj = {{1,-1,0,0},{0,0,1,-1}};
				for(int i=0;i<4;i++)
				{
					int x1=x+adj[0][i], y1=y+adj[1][i];
					if(valid(x1, y1, mat, done,n,m))
					{
						done[x1][y1]=true;
						if(mat[x1][y1]=='*')
						{
							tot--;
							pfs[map[m*x1+y1]]= d+1;
							if(tot==0) break;
						}
						q.add(new Co(x1,y1,d+1));
					}
				}
			}
			int ans = Integer.MAX_VALUE;
			//print(pfs, bit);
			boolean reachable = true;
			for(int i:dirty)
			{
				if( pfs[map[i]]==Integer.MAX_VALUE){ pw.println(-1); reachable=false; break;}
			}
			if(reachable)
			{
				for(int i:dirty)
				{
					int d = pfs[map[i]];
					d+= search(apsp, 1<<(map[i]), map , map[i], bit);
					if(d<ans) ans=d;
				}
				pw.println(ans);
			}
		}
		pw.close();
		return;
	}
	static int search(int[][] apsp , int mask, int[] map, int cur,int tot)
	{
		if(dp[cur][mask]!=-1) return dp[cur][mask];
		if(mask== ((1<<tot)-1)) return dp[cur][mask]=0;
		int ans=Integer.MAX_VALUE;
		for(int i=0;i<tot;i++)
		{
			if(cur!= i && ((mask &(1<<i))==0))
			{
				int temp = search(apsp, mask |(1<<i), map, i , tot);
				//System.out.println("apsp:"+ apsp[cur][i]+ +"search:" + temp);

				int d = apsp[cur][i]+ temp;
				if(d<ans) ans=d;
			}
		}
		
		return dp[cur][mask] = ans;
	}
}