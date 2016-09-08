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
public class Main{
	static int gcd(int a ,int b)
	{
		while(b!=0)
		{
			int temp=b;
			b= a%b;
			a=temp;
		}
		return a;
	}
	public static void main(String[] args)
	{
		InputReader r  = new InputReader(new BufferedInputStream(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		int t = r. readInt();
		for(;t>0;t--)
		{
			int a = r.readInt();
			int b=r.readInt();
			if(b>a)
			{
				b ^=a;
				a^=b;
				b^=a;
			}
			int c = r.readInt();
			if(c>a || c%gcd(a,b)!=0) {pw.println(-1); continue;}
			else if(c==0)
			{
				pw.println(0);
				continue;
			}
			class State
			{
				int a,b,d;
				State(int n,int m)
				{
					a=n;
					b=m;
					d=0;
				}
				State(int n,int m,int k)
				{
					a=n;
					b=m;
					d=k;
				}
				public int hashCode()
				{
					return 31*new Integer(a).hashCode()+ new Integer(b).hashCode();
				}
				public boolean equals(Object s1)
				{
					State s;
					if(s1==this ) return true;
					if(!(s1 instanceof State)) return false;
					s= (State) s1;
					return this.a==s.a && this.b==s.b;
				}
			}
			int shortest=-1;
			//HashSet<State> done = new HashSet<State>();
			Queue<State> q = new LinkedList<State>();
			//done.add(new State(0,0));
			q.add(new State(0,0,0));
			while(!q.isEmpty())
			{
				State cur = q.poll();
				int a1=cur.a, b1= cur.b, d = cur.d;
				//System.out.println(a1+" "+b1+" "+ a+" "+b);
				if(a1==0){//System.out.println("inside 1");
					
						//done.add(new State(a,b1));
						q.add(new State(a,b1,d+1));
						//System.out.println(a+" ");
						if(a==c)
						{
							
							shortest=d+1;
							break;
						}
					
				}
				else if(b1==b)
				{
					//System.out.println("inside 2");
					
					//	done.add(new State(a1,0));
						q.add(new State(a1,0,d+1));
					
				}
				else if(b1<b && a1>= b-b1){
					//System.out.println("inside 3");
				
					//done.add(new State(a1-b+b1,b));
					q.add(new State(a1-b+b1,b,d+1));
					//System.out.print(a1-b+b1+" ");
					if(a1-b+b1==c)
					{
						
						shortest=d+1;
						break;
					}
					//System.out.print(b+" ");
					if(b==c)
					{
						
						shortest=d+1;
						break;
					}
				
				}
				else if(b1<b && a1<b-b1){//System.out.println("inside 4");
				
				//	done.add(new State(0,a1+b1));
					q.add(new State(0,a1+b1,d+1));
				//	System.out.print(a1+b1+" ");
					if(a1+b1==c)
					{
						
						shortest = d+1;
						break;
					}
				}
			
			}
			//done = new HashSet<State>();
			q = new LinkedList<State>();
			//done.add(new State(0,0));
			q.add(new State(0,0,0));
			b^=a;
			a^=b;
			b^=a;
			while(!q.isEmpty())
			{
				State cur = q.poll();
				int a1=cur.a, b1= cur.b, d = cur.d;
				if(a1==0){
					
						//done.add(new State(a,b1));
						q.add(new State(a,b1,d+1));
						if(a==c)
						{
							if(d+1<shortest)
								shortest=d+1;
							break;
						}
					
				}
				else if(b1==b)
				{
					
						//done.add(new State(a1,0));
						q.add(new State(a1,0,d+1));
					
				}
				
				else if(b1<b && a1>= b-b1){
				
				//	done.add(new State(a1-b+b1,b));
					q.add(new State(a1-b+b1,b,d+1));
					if(a1-b+b1==c)
					{
						if(d+1<shortest)
							shortest=d+1;
						break;
					}
					if(b==c)
					{
						if(d+1<shortest)
							shortest=d+1;
						break;
					}
				
			}
				else if(b1<b && a1<b-b1){
				
					//done.add(new State(0,a1+b1));
					q.add(new State(0,a1+b1,d+1));
					if(a1+b1==c)
					{
						if(d+1<shortest)
							shortest = d+1;
						break;
					}
				
			}
			}

			pw.println(shortest);
		}
		pw.close();
		return;
	}
}


