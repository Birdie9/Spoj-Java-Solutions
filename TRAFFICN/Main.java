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
	Dist(int a,int b)
	{
		v=a;
		d=b;
	}
}

public class Main
{
	static int[][] transpose(int[][] mat)
	{
		int n = mat[0].length;
		int[][] ret = new int[n][n];
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				ret[j][i]= mat[i][j];
			}
		}
		return ret;
	}
	public static int[] dijkstra(ArrayList<ArrayList<Dist>> mat,int s,int n)
	{
		
		
		int[] dist = new int[n];
		boolean[] done = new boolean[n];
		for(int i=0;i<n;i++) dist[i]=Integer.MAX_VALUE;
		dist[s] = 0;
		PriorityQueue<Dist> pq = new PriorityQueue<Dist>(new Comparator<Dist>(){
			public int compare(Dist a, Dist b)
			{
				return a.d-b.d;
			}
		});
		pq.add(new Dist(s,0));
		while(!pq.isEmpty())
		{
			Dist cur= pq.poll();
			if(cur.d==Integer.MAX_VALUE) return dist;
			if(done[cur.v]) continue;
			done[cur.v]=true;
			for(Dist i: mat.get(cur.v))
			{
				if(!done[i.v] && dist[i.v]>i.d+dist[cur.v])
				{
					dist[i.v] = i.d+dist[cur.v];
						pq.add(new Dist(i.v,dist[i.v]));
				}
			}
		}
		return dist;
	}
	public static void main(String[] args)
	{
		PrintWriter pw = new PrintWriter(System.out);
		InputReader r= new InputReader(new BufferedInputStream(System.in));
		int test = r.readInt();
		for(;test>0;test--)
		{
			int n=r.readInt(), m = r.readInt(), k = r.readInt(), s = r.readInt(), t = r.readInt();
			s--;
			t--;
			ArrayList<ArrayList<Dist>> m1 = new ArrayList<ArrayList<Dist>>();
			ArrayList<ArrayList<Dist>> m2 =new ArrayList<ArrayList<Dist>>();
			for(int i=0;i<n;i++)
					{m1.add(new ArrayList<Dist>());
					m2.add(new ArrayList<Dist>());}
			for(int i=0;i<m;i++)
			{
				int a = r.readInt(), b= r.readInt(), c= r.readInt();
				a--;
				b--;
				m1.get(a).add(new Dist(b,c));
				m2.get(b).add(new Dist(a,c));
			}
			int[] fromS = dijkstra(m1, s,n);
			int[] fromT = dijkstra(m2,t,n);
			int shortest = fromS[t];
			for(int i=0;i<n;i++) System.out.println(fromS[i]);
							for(int i=0;i<n;i++) System.out.println(fromT[i]);

			for(int i=0;i<k;i++)
			{
				int a = r.readInt(), b= r.readInt(), c= r.readInt();
				a--;
				b--;
				if(fromS[a]!=Integer.MAX_VALUE && fromT[b]!=Integer.MAX_VALUE && shortest > fromS[a] +c+ fromT[b])
				{
					shortest = fromS[a] +c+ fromT[b];
					System.out.println(shortest+" " + c);
				}
				if(fromS[b]!=Integer.MAX_VALUE && fromT[a]!=Integer.MAX_VALUE && shortest > fromS[b] +c+ fromT[a])
				{
					shortest = fromS[b] +c+ fromT[a];
					System.out.println(shortest+" "+ c);
				}
			}
			pw.println(shortest==Integer.MAX_VALUE? -1:shortest);
		}
		pw.close();
		return;
	}
}