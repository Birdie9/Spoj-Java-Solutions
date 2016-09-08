import java.io.*;
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


public class Main{
public static long min(long a,long b)
	{
		return a<b?a:b;
	}

public static void main(String[] args)
{
	
	InputReader r = new InputReader(new BufferedInputStream(System.in));
	PrintWriter pw= new PrintWriter(System.out);
	int N =r.readInt(), M=r.readInt(), K=r.readInt(), Q=r.readInt();
	long[][][] dp  = new long[N+1][N][N];
	long[][][] dpk = new long[N+1][N][N];
	for(int i=0;i<N;i++)
	{
		for(int j=0;j<N;j++)
		{
			
			dp[0][i][j] = -1;
			dpk[0][i][j] =-1;
			if(i==j) dp[0][i][j]=0;
			if(i==j && i<K) dpk[0][i][j]=0;
		}
	}
	for(int i=0;i<M;i++)
	{
		int a = r.readInt(), b=r.readInt(), c=r.readInt();
		a--;b--;
		if(dp[0][a][b]==-1)
			dp[0][a][b]=c;
		else dp[0][a][b] = min(dp[0][a][b],c);
		if(a<K || b<K) {
			if(dpk[0][a][b]==-1)
				dpk[0][a][b]=c;
			else dpk[0][a][b] = min(dpk[0][a][b],c);
		}
	}
	for(int k=1;k<=N;k++)
	{
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<N;j++)
			{
				dp[k][i][j] = dp[k-1][i][j];
				if( dp[k-1][i][k-1]!=-1 && dp[k-1][k-1][j]!=-1)
				{
					if(dp[k][i][j]==-1) dp[k][i][j] =  dp[k-1][i][k-1] +dp[k-1][k-1][j];
					else dp[k][i][j] = min(dp[k][i][j], dp[k-1][i][k-1] +dp[k-1][k-1][j]);
				}
			}
		}
	}
	for(int k=1;k<=N;k++)
	{
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<N;j++)
			{
				if(i<K || j<K)
					dpk[k][i][j] =dp[k][i][j];
				else
				{
					dpk[k][i][j]=dpk[k-1][i][j];
					if(k<=K)
					{
						
						if(dp[k-1][i][k-1]!=-1 && dp[k-1][k-1][j]!=-1)
						{
							if(dpk[k][i][j]==-1) dpk[k][i][j] = dp[k-1][i][k-1]+dp[k-1][k-1][j];
							else dpk[k][i][j] = min(dpk[k][i][j], dp[k-1][i][k-1]+dp[k-1][k-1][j]);
						}
					}
					else
					{
						if(dpk[k-1][i][k-1]!=-1 && dp[k-1][k-1][j]!=-1)
						{
							if(dpk[k][i][j]==-1) dpk[k][i][j]=dpk[k-1][i][k-1]+dp[k-1][k-1][j];
							else dpk[k][i][j] = min(dpk[k][i][j], dpk[k-1][i][k-1]+dp[k-1][k-1][j]);
						}
						if( dp[k-1][i][k-1]!=-1 && dpk[k-1][k-1][j]!=-1)
						{
							if(dpk[k][i][j]==-1) dpk[k][i][j]=dp[k-1][i][k-1]+dpk[k-1][k-1][j];
							else dpk[k][i][j] = min(dpk[k][i][j],dp[k-1][i][k-1]+dpk[k-1][k-1][j]);
						}
					}
				}

			}
		}
	}
	int num=0;
	long sum=0;

	for(int i=0;i<Q;i++)
	{
		int a = r.readInt(), b= r.readInt();
		a--;
		b--;
		if(dpk[N][a][b]!=-1)
		{
			num++;
			sum+= dpk[N][a][b];
		}
	}
	pw.println(num);
	pw.println(sum);
	pw.close();
	return;
}
}
