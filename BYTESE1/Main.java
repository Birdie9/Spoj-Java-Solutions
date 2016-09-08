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
	int vi,vj, d;
	Dist(int a,int c,int b)
	{
		vi=a;
		vj=c;
		d=b;
	}
}

public class Main
{
public static void main(String[] args)
{
InputReader r =new InputReader(new BufferedInputStream(System.in));
PrintWriter pw= new PrintWriter(System.out);
int k = r.readInt();
for(;k>0;k--)
{
	int m = r.readInt(), n= r.readInt();
	int[][] a = new int[m][n];
	int[][] dist = new int[m][n];
	for(int i=0;i<m;i++)
		 for(int j=0;j<n;j++)
		 	{a[i][j]=r.readInt();dist[i][j]=Integer.MAX_VALUE;}
	int ti = r.readInt(), tj= r.readInt();
	ti--;
	tj--;
	int T=r.readInt();
	
	dist[0][0] = a[0][0];

	PriorityQueue<Dist> pq = new PriorityQueue<Dist>(new Comparator<Dist>(){
		public int compare(Dist a, Dist b)
		{
			return a.d-b.d;
		}
	});
	pq.add(new Dist(0,0,a[0][0]));
	boolean[][] done = new boolean[m][n];
	
	while(!pq.isEmpty())
	{
		Dist cur= pq.poll();
		if(cur.d== Integer.MAX_VALUE) break;
		if(done[cur.vi][cur.vj]) continue;
		done[cur.vi][cur.vj]=true;
		if(cur.vi==ti && cur.vj==tj) break;
		int vi=cur.vi;
		int vj=cur.vj;
		if(vi>0 && !done[vi-1][vj] && dist[vi-1][vj]> dist[cur.vi][cur.vj]+a[vi-1][vj])
		{
			 dist[vi-1][vj]= dist[cur.vi][cur.vj]+a[vi-1][vj];
			 pq.add(new Dist(vi-1,vj,dist[vi-1][vj]));
		}
		if(vj>0 && !done[vi][vj-1]&& dist[vi][vj-1]> dist[cur.vi][cur.vj]+a[vi][vj-1])
		{
			dist[vi][vj-1]= dist[cur.vi][cur.vj]+a[vi][vj-1];
			 pq.add(new Dist(vi,vj-1,dist[vi][vj-1]));
		}
		if(vi<m-1 && !done[vi+1][vj]&& dist[vi+1][vj]> dist[cur.vi][cur.vj]+a[vi+1][vj])
		{
			 dist[vi+1][vj]= dist[cur.vi][cur.vj]+a[vi+1][vj];
			  pq.add(new Dist(vi+1,vj,dist[vi+1][vj]));
		}
		if(vj<n-1 && !done[vi][vj+1]&& dist[vi][vj+1]> dist[cur.vi][cur.vj]+a[vi][vj+1])
		{
			 dist[vi][vj+1]= dist[cur.vi][cur.vj]+a[vi][vj+1];
			  pq.add(new Dist(vi,vj+1,dist[vi][vj+1]));
		}
	}
	if(dist[ti][tj]<=T)
	{
		pw.println("YES");
		pw.println(T-dist[ti][tj]);
	}
	else pw.println("NO");
}
pw.close();
return;
}
}