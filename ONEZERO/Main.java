import java.io.*;
import java.util.*;
import java.math.BigInteger;
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

public class Main
{
	public static void main(String[] args)
	{
		InputReader r = new InputReader(new BufferedInputStream(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		int k = r.readInt();
		for(;k>0;k--)
		{
			int n = r.readInt();
			
			if(n==0 || n==1) {pw.println(n); continue;}
			int p=n;
			int digit=0;
			//int d=-1;
			boolean bin=true;
			//boolean ones=false;
			//int one=0;
			while(p!=0)
			{
				int q=p%10;
				if(q!=1 && q!=0) bin=false;
				//if(q==0){ ones=true; one=0; d=digit;}
				//else if(ones && q> 1) ones=false;
				//else if(ones&& q==1) one++;
				p=p/10;
				digit++;
			}
			if(bin){pw.println( n); continue;}
			//System.out.println("d="+ d +" ones="+ones+" one="+one+" digit="+digit);
			//int temp=digit;
			boolean[] done = new boolean[n];
			/*int strt=0;
			if(ones)
			{
				strt =(n-n%(int)Math.pow(10,d+1) +(int) Math.pow(10,d));
			}
			else  strt = (int) Math.pow(10,digit);*/
			//done[strt%n] = true;
			//System.out.println(Integer.toBinaryString(strt));
			Queue<BigInteger> q = new LinkedList<BigInteger>();
			//q.add(strt);
			//if(strt%10 ==0){ if((strt+1)%n==0){pw.println(strt+1); continue; } q.add(strt+1);}
			//System.out.println(strt);
			q.add(new BigInteger("1"));
			done[1]=true;
			BigInteger big10 =  new BigInteger("10");
			BigInteger bign = new BigInteger(new Integer(n).toString());
			BigInteger big1 = new BigInteger("1");
			while(!q.isEmpty())
			{
				BigInteger cur = q.poll();
				//System.out.println((cur*10 ));
				int s1 =cur.multiply(big10 ).mod(bign).intValue(), s2=cur.multiply(big10 ).add(big1).mod(bign).intValue(); 
				if(!done[s1])
				{
					if(cur.multiply(big10 ).compareTo(bign)>0 && s1==0) {pw.println(cur.multiply(big10 )); break;}
					q.add(cur.multiply(big10 ));
					if(cur.multiply(big10 ).compareTo(bign)>0 )
					done[s1]=true;
					//.out.println(cur*10);

				} 
				if(!done[s2])
				{
					
					if(cur.multiply(big10 ).add(big1).compareTo(bign)>0 && s2==0) {pw.println(cur.multiply(big10 ).add(big1)); break;}
					q.add(cur.multiply(big10 ).add(big1));
					if(cur.multiply(big10 ).add(big1).compareTo(bign)>0 )
					done[s2]=true;
					//.out.println(cur*10);

				} 
			}
		}
		pw.close();
			return;
	}
}