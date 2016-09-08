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

	public int nextInt() {
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
	static double[] area;
	static double[] price;
	static double[][] coupon;
	static int m;


	public static void main(String[] args)
	{
		//InputReader r = new InputReader(new BufferedInputStream(System.in));
		Scanner r =new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		while(true)
		{
			m  = r.nextInt();
			if(m==0) break;
			area = new double[m];
			price= new double[m];
			coupon = new double[m][m];
			
			for(int i=0;i<m;i++)
			{
				price[i] = r.nextInt();
				area[i] = r.nextInt();
				int n = r.nextInt();
				for(int j=0;j<m;j++) coupon[i][j]=1;
				for(int j=0;j<n;j++)
				{
					int x=r.nextInt(), y=r.nextInt();
					coupon[i][x-1] = coupon[i][x-1]*(1- ((double)y)/100);
				}
			}
			double dp[][] = new double[1<<m][2]; 
			double disc[][] = new double[1<<m][m];
			for(int j=0;j<m;j++)
			{
				disc[0][j] = 1;
			}
			for(int i=1;i<(1<<m);i++)
			{
				double temp  = Double.MAX_VALUE,tarea=-1,tprice=-1;
				for(int j=0;j<m;j++)
				{
					if(((1<<j)&i)!=0)
					{
						int state = ((~(1<<j))&i);
						for(int k=0;k<m;k++)
						{
							disc[i][k]= disc[state][k]* coupon[j][k];
						}
						break;
					}
				}
				for(int j=0;j<m;j++)
				{
					if(((1<<j)&i)!=0)
					{
						int state = ((~(1<<j))&i);
						double ar= dp[state][1]+ area[j];
						double pr =dp[state][0]+ disc[state][j]*price[j];
						if(temp> pr/ar){
							temp=pr/ar;
							tarea = ar;
							tprice=pr;
						} 
					}
				}
				dp[i][0]=tprice;
				dp[i][1]=tarea;
			}
			double ans= Double.MAX_VALUE;
			for(int i=1;i<(1<<m);i++)
			{
				if(dp[i][0]/dp[i][1] < ans) ans = dp[i][0]/dp[i][1];
			}
			
					
			pw.println(String.format("%.4f", ans));
		}
		pw.close();
		return;
	}
}