import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;
class InputReader {
    
	private BufferedInputStream stream;
	private byte[] buf = new byte[1024];
	private int curChar;
	private int numChars;
	private SpaceCharFilter filter;

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
	public String readLine(){
		int c=read();
		StringBuilder res = new StringBuilder();
		while (c!=10){
		
			res.appendCodePoint(c);
			c = read();
		} 
		//System.out.println("***"+res.toString());
		return res.toString();
	}
	public String readString() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		StringBuilder res = new StringBuilder();
		do {
			res.appendCodePoint(c);
			c = read();
		} while (!isSpaceChar(c));
		return res.toString();
	}

	public boolean isSpaceChar(int c) {
		if (filter != null)
			return filter.isSpaceChar(c);
		return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
	}

	public String next() {
		return readString();
	}

	public interface SpaceCharFilter {
		public boolean isSpaceChar(int ch);
	}
}
class ij
{
	int i,j;
	ij(int a,int b)
	{
		i=a;
		j=b;
	}
}
class ijList
{
	ArrayList<ij> list ;
	ijList()
	{
		list= new ArrayList<ij>();
	}
}
public class Main{
	public static long sqr(long a)
	{
		return a*a;
	}
	public static long max(long a,long b,long c)
	{
		return (a>b?(a>c?a:c):(b>c?b:c));
	}

	public static void main(String[] args)
	{
		InputReader r = new InputReader(new BufferedInputStream(System.in));
		//Scanner r =new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		int n=r.readInt();
		int[] a = new int[n+1];
		int[] b = new int[n+1];
		long[][] dp = new long[n+1][n+1];
		long[] sumb = new long[n+1];
		long[] suma = new long[n+1];
		ijList[][] lastP = new ijList[n+1][n+1];
		for(int i=0;i<=n;i++)
		{
			for(int j=0;j<=n;j++)
			{
				lastP[i][j] = new ijList();	
			}
		}

		for(int i=1;i<=n;i++)
		{
			 a[i] = r.readInt();
			 suma[i] = suma[i-1]+a[i];
		}
		for(int i=1;i<=n;i++)
		{
			b[i]=r.readInt();
			sumb[i] = sumb[i-1]+b[i];
		}
		
		for(int i=0;i<=n;i++)
		{
			dp[0][i] =-(sumb[i]*sumb[i]);
			dp[i][0]= -(suma[i]*suma[i]);
			lastP[0][i].list.add(new ij(0,0));
			lastP[i][0].list.add(new ij(0,0));
		}
		for(int i=1;i<=n;i++)
		{
			for(int j=1;j<=n;j++)
			{
				dp[i][j]=dp[i-1][j-1]+a[i]*b[j];
				lastP[i][j].list.add(new ij(i,j));
				//i,j-1
				for(ij temp:lastP[i][j-1].list)
				{
					if(dp[i][temp.j]-sqr(sumb[j]-sumb[temp.j]) > dp[i][j])
					{
						dp[i][j] = dp[i][temp.j]-sqr(sumb[j]-sumb[temp.j]);
						lastP[i][j].list.clear();
						lastP[i][j].list.add(new ij(temp.i,temp.j));
					}
					else if(dp[i][temp.j]-sqr(sumb[j]-sumb[temp.j]) == dp[i][j])
						lastP[i][j].list.add(new ij(temp.i,temp.j));
				}
				for(ij temp:lastP[i-1][j].list)
				{
					if(dp[temp.i][j]-sqr(suma[i]-suma[temp.i]) > dp[i][j])
					{
						dp[i][j] = dp[temp.i][j]-sqr(suma[i]-suma[temp.i]);
						lastP[i][j].list.clear();
						lastP[i][j].list.add(new ij(temp.i,temp.j));
					}
					else if(dp[temp.i][j]-sqr(suma[i]-suma[temp.i]) == dp[i][j])
						lastP[i][j].list.add(new ij(temp.i,temp.j));
				}
				
			}
		}

		pw.println(dp[n][n]);
		pw.close();
		return;
	}
}