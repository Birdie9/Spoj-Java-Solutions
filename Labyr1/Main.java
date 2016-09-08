
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
class Node
{
	int v;
	int ht;
	ArrayList<Node> ch;
	Node(int a)
	{
		v=a;
		ch=new ArrayList<Node>();
		ht=0;
	}
}
class Co
{
	int x,y,len;
	Co(int a,int b)
	{
		x=a;
		y=b;
		len=0;
	}
}
public class Main{
	static Co dfs(Co s)
	{
		
	}

	public static void main(String[] args) throws java.lang.Exception
	{
		//InputReader r =new InputReader(new BufferedInputStream(System.in));
		BufferedReader  R =new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw  = new PrintWriter(System.out);
		StringTokenizer st =new StringTokenizer(R.readLine());
		int te = Integer.parseInt(st.nextToken());
		for(;te>0;te--)
		{
			int c,r;
			st =new StringTokenizer(R.readLine());
			c=Integer.parseInt(st.nextToken());
			r=Integer.parseInt(st.nextToken());
			int n=0;
			char[][] mat = new char[r][c];
			//int[][] map = new int[r][c];
			//ArrayList<ArrayList<Integer>> ad = new ArrayList<ArrayList<Integer>>();
			for(int i=0;i<r;i++)
			{
				mat[i] = R.readLine().toCharArray();
				
			}
			int si=-1,sj=-1;
			for(int i=0;i<r;i++)
				for(int j=0;j<c;j++)
				{
					if(mat[i][j]=='.')
					{
						si=i;
						sj=j;
					}
				}
			if(si==-1)
			{
				pw.println("Maximum rope length is 0.");
				continue;
			}
			Co next = dfs(new Co(si,sj));
			Co ans = dfs (next);
			
			pw.println("Maximum rope length is "+ans.len+".");
		}
		pw.close();
		return;
	}
}
