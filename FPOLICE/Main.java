import java.io.*;
import java.lang.*;
import java.util.*;
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

public class Main
{
	public static int min(int a,int b)
	{
		return a<b?a:b;
	}
	public static void main(String[]  args) 
	{
		InputReader r = new InputReader(new BufferedInputStream(System.in));
		PrintWriter pw  = new PrintWriter (System.out);
		int test = r.readInt();
		for(;test>0;test--)
		{
			int N =r.readInt();
			int T= r.readInt();
			int[][] time = new int[N][N];
			int[][] risk  = new int[N][N];
			for(int i=0;i<N;i++)
			{
				for(int j=0;j<N;j++)
					time[i][j] = r.readInt();
			}
			for(int i=0;i<N;i++)
			{
				for(int j=0;j<N;j++)
					risk[i][j] = r.readInt();
			}
			int[][] dp = new int[T+1][N];
			for(int t=0;t<=T;t++) 
			{
				dp[t][0]=0;
			}			
			for(int t=0;t<=T;t++)
			{
				for(int i=1;i<N;i++)
				{
					if(time[0][i]<=t) dp[t][i]=risk[0][i];
					else dp[t][i]=-1;
					if(t>0 && dp[t-1][i]!=-1)
					{
						if(dp[t][i]==-1) dp[t][i]=dp[t-1][i];
						else dp[t][i] = min(dp[t-1][i],dp[t][i]);
					}
					for(int j=1;j<N;j++)
					{
						if(j==i) continue;
						if(t>=time[j][i] && dp[t-time[j][i]][j]!=-1)
						{
							if(dp[t][i]==-1) dp[t][i]=dp[t-time[j][i]][j]+risk[j][i];
							else dp[t][i] = min(dp[t][i], dp[t-time[j][i]][j]+risk[j][i]);
						}	
					}						
				}
			}
			int minRisk=Integer.MAX_VALUE,minTime=0;
			for(int t=0;t<=T;t++)
			{
				if(dp[t][N-1]!=-1 && dp[t][N-1]<minRisk)
				{
					minRisk=dp[t][N-1];
					minTime =t;
				}
			}
			if(minRisk!=Integer.MAX_VALUE)
				pw.println(minRisk+" "+minTime);
			else pw.println(-1);
		}
		pw.close();
		return;
	}
}

