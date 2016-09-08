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
class Main{
	//0 -120
	//1- 180
	//2-60
	static int abs(int a)
	{
		return a<0?-a:a;
	}
	public static void main(String[] args)
	{
		InputReader r = new InputReader(new BufferedInputStream(System.in));
		PrintWriter pw   = new PrintWriter(System.out);
		int t = r.readInt();
		for(;t>0;t--)
		{
			int n = r.readInt();
			int m=n;
			int[][][] way = new int[15][21][21];
			   way[0][10][10] = 1;
		    for ( int k = 1; k <= 14; k++) {
		        for ( int i = 1; i < 20; i++ ) {
		            for ( int j = 1; j < 20; j++ ) {
		                way[k][i][j] = way[k-1][i][j+1]+way[k-1][i][j-1]
		                                +way[k-1][i+1][j]+way[k-1][i-1][j]
		                                +way[k-1][i+1][j-1]+way[k-1][i-1][j+1];
		            }
		        }
		       // list[k] = way[k][8][8];
		    }
			pw.println(way[n][10][10]);
		}
		pw.close();
		return;
	}
}
// 0,1
// 120 180
// dp[0][0][n]??
// dp[m][1][n] = dp[m-1][1][n-1]+2*dp[m][0][n-1]+dp[m+1][1][n-1]+2*dp[m+1][1][n-1];
// dp[m][0][n] = 2*dp[m-1][1][n-1] +2*dp[m][0][n-1] +2*dp[m+1][1][n-1]
