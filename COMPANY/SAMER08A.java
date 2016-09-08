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
class Dist
{
	int v, d;
}

public class Main
{
	static int[][] transpose(int[][] mat)
	{
		int n = mat[0].length;
		int[][] ret = new int[n][n];
		for(int i=0;i<n;i++)
		{
			for(int i=0;j<n;j++)
			{
				ret[j][i]= mat[i][j];
			}
		}
		return ret;
	}
	public static int[] dijkstra(int src,int[][] mat)
	{
		int n = mat[0].length;
		int[] ret = new int[n];
		boolean[] done = new boolean[n];
		for(int i=0;i<n;i++) ret[i]=Integer.MAX_VALUE;
		ret[src] = 0;
		PriorityQueue<Dist> pq = new PriorityQueue<Dist>(new Comparator<Dist>(){
			public int compare(Dist a, Dist b)
			{
				return a.d-b.d;
			}
		});
		pq.add(new Dist(src,0));
		while(!pq.isEmpty())
		{
			Dist cur = pq.poll();
			if(cur.d==Integer.MAX_VALUE) return ret;
			if(done[cur.v])  continue;
			if(ret[cur.v]>= cur.d)
			{
				ret[cur.v] = cur.d;
				done[cur.v]= true;
				int tempd=Integer.MAX_VALUE, tempi=-1;
				for(int i=0;i<n;i++)
				{
					if(!done[i] && mat[cur.v][i]!=Integer.MAX_VALUE && ret[i]>mat[cur.v][i]+ret[cur.v])
					{
						pq.add(new Dist(i,mat[cur.v][i]+ret[cur.v]));
						ret[i] = mat[cur.v][i]+ret[cur.v];
					}
				}
			}
		}
		return ret;
	}
	
	public static void main(String[] args)
	{
		InputReader r =new InputReader(new BufferedInputStream(System.in));
		PrintWriter pw= new PrintWriter(System.out);

		while(true)
		{	int n,m;
			n = r.readInt(); 
			m=r.readIn();
			if(n==0 && m==0) return;
			int[][] mat= new int[n][n];
			//init mat
			int[][] path = new int[m][2];
			int s = r.readInt(), t= r.readInt();
			for(int i=0;i<m;i++)
			{
				int a= r.readInt(), b=r.readInt(),c=r.readInt();
				//adj.get(a).add(new Dist(b,c));
				mat[a][b]=c;
				path[i][0]=a;
				path[i][1]=b;

			}
			for(int i=0;i<n;i++) dist[i] = Integer.MAX_VALUE;
			int[] distfromS = dijkstra( s,mat);
			int[] distfromT = dijkstra(t,transpose(mat));
			int shortest  = distfromS[t];
			for(int i=0;i<m;i++)
			{
				int a=path[i][0];
				int b=path[i][1];
				if(distfromS[a] + mat[a][b]+distfromT[b] == shortest) mat[a][b]=Integer.MAX_VALUE;
			}
			int[] ans = dijkstra(s,mat);
			pw.println(ans[t]);
		}
		pw.close();
		return;
	}
}