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
	Dist(int a, int b)
	{
		v=a;
		d=b;
	}
}
public class Main{
public static void main(String[] args) throws java.lang.Exception
{
	InputReader r= new InputReader(new BufferedInputStream(System.in));
	PrintWriter pw= new PrintWriter(System.out);
	int te= r.readInt();
	for(;te>0;te--)
	{
		int n=r.readInt(), m=r.readInt(), s=r.readInt(), t=r.readInt();
		s--;
		t--;
		ArrayList<ArrayList<Dist>> ad= new ArrayList<ArrayList<Dist>>();
		int[] dist= new int[n];
		for(int i=0;i<n;i++){ ad.add(new ArrayList<Dist>()); dist[i] = Integer.MAX_VALUE;}
		for(int i=0;i<m;i++)
		{
			int a=r.readInt(), b=r.readInt(), c=r.readInt();
			a--;
			b--;
			ad.get(a).add(new Dist(b,c));
			ad.get(b).add(new Dist(a,c));
		}
		PriorityQueue<Dist> pq = new PriorityQueue<Dist>(new Comparator<Dist>(){
			public int compare(Dist a , Dist b)
			{
				return a.d - b.d;
			}
		});
		pq.add(new Dist(s,0));
		boolean[] done = new boolean[n];
		dist[s]=0;

		while(!pq.isEmpty())
		{
			Dist cur = pq.poll();
			if(done[cur.v]) continue;
			done[cur.v]=true;

			if(dist[cur.v]==Integer.MAX_VALUE) break;
			if(cur.v==t ) break;
			for(Dist i:ad.get(cur.v))
			{
				if(!done[i.v] && dist[i.v]>dist[cur.v]+i.d)
				{
					dist[i.v] = dist[cur.v]+i.d;
					pq.add(new Dist(i.v, dist[i.v]));
				}
			}
		}
		//for(int i=0;i<n;i++) pw.print(dist[i]+" ");
		if(dist[t]==Integer.MAX_VALUE) pw.println("NONE");
		else pw.println(dist[t]);

	}
	pw.close();
	return;
}
}