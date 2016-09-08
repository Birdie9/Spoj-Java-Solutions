
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
public class Main{
	static int maxPath(Node n)
	{
		int maxP=0;
		int h1=-1, h2=-1;
		for(Node c:n.ch)
		{
			if(c.ht>=h1){h2=h1; h1=c.ht; }
			else if(c.ht>h2) h2= c.ht;
			maxP = max(maxP, maxPath(c));
		}
		h1++;
		h2++;
		maxP =max( h1+h2, maxP);
		return maxP;
	}
	static int max(int a,int b)
	{
		return a>b?a:b;
	}
	static Node makeTree(ArrayList<ArrayList<Integer>> l , int v,int par)
	{
		Node n = new Node(v);
		for(int i:l.get(v))
		{
			if(i!=par) n.ch.add(makeTree(l,i,v));
		}
		for(Node c:n.ch)
		{
			n.ht=max(n.ht, 1+c.ht);
		}
		return n;
	}
	public static void main(String[] args)
	{
		InputReader r =new InputReader(new BufferedInputStream(System.in));
		PrintWriter pw  = new PrintWriter(System.out);
		int n=r.readInt();
		if(n==0){pw.println(0);pw.close(); return;}
		ArrayList<ArrayList<Integer>> l = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<n;i++) l.add(new ArrayList<Integer>());
		for(int i=0;i<n-1;i++)
		{
			int a=r.readInt(), b=r.readInt();
			a--;
			b--;
			l.get(a).add(b);
			l.get(b).add(a);
		}
		Node root = makeTree(l, 0,-1);
		pw.println(maxPath(root));
		pw.close();
		return;
	}
}
