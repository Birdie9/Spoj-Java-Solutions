import java.io.*;
import java.util.*;
class Node{
	ArrayList<Node> ch;
	int[] ca;
	char c;
	int inorderst;
	int inorderen;
	int n;
	int no=0;
	Node(int a)
	{
		n=a;
		ca=new int[26];
		ch =new ArrayList<Node>();
	}
}
class Main{
	public static void init(Node root, char[] str)
	{
		root.c = str[root.n];
		root.ca[root.c-'a']++;

		for(Node ch: root.ch)
			init(ch, str);
		for(Node ch:root.ch)
			for(int i=0;i<26;i++)
				root.ca[i]+=ch.ca[i];
		for(int i=0;i<26;i++)
			if(root.ca[i]%2 != 0) root.no++;

	}
	static int inord(Node root, int i, int[] order)
	{
		order[i]=root.n;
		root.inorderst=i;
		int j=1;
		int k=0;
		i++;
		for(Node ch: root.ch)
			{
				k  = inord(ch, i, order);
				i+=k;
				j+=k;
			}
		root.inorderen = i-1;
		return j;
	}
	static char change(Node root,int x,int inordx,  char c)
	{
		if(root.ca[c-'a']%2==0) root.no++;
			else root.no--;
			root.ca[c-'a']++;
		if(root.n==x)
		{
			if(root.ca[root.c-'a']%2==0) root.no++;
			else root.no--;
			root.ca[root.c-'a']--;
			char ret  = root.c;
			root.c=c;
			return ret;
		}
		for(Node ch: root.ch)
			if(ch.inorderst<=inordx && ch.inorderen>= inordx)
			{
				char ret = change(ch, x, inordx,c);
				if(root.ca[ret-'a']%2==0) root.no++;
				else root.no--;
				root.ca[ret-'a']--;
				return ret;
				
			}
		return 'a';
	}
	static boolean ans(Node root, int x, int inordx)
	{
		if(root.n==x)
		{
			if(root.no==1 ) return true;
			if(root.no==0) return true;
			return false;
		}
		for(Node ch: root.ch)
			if(ch.inorderst<=inordx && ch.inorderen>= inordx)
				return ans(ch, x, inordx);
		return false;
	}
	public static Node makeTree(ArrayList<ArrayList<Integer>> l, int cur,int par)
	{	
		Node n = new Node(cur);
		for(int i: l.get(cur))
			if(i!=par)
			{
				n.ch.add(makeTree(l,i,cur));
			}
		return n;
	}
	public static void main(String[] args) throws java.lang.Exception
	{
		PrintWriter pw  = new PrintWriter(System.out);
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer s ;
		while(!(s= new StringTokenizer(r.readLine())).hasMoreTokens());
		int n = Integer.parseInt(s.nextToken());
		ArrayList<ArrayList<Integer>> l = new ArrayList<ArrayList<Integer>> ();
		for(int i=0;i<n;i++) l.add(new ArrayList<Integer>());
		for(int i=0;i<n-1;i++)
		{
			while(!s.hasMoreTokens())s= new StringTokenizer(r.readLine());
			int a = Integer.parseInt(s.nextToken());
			while(!s.hasMoreTokens())s= new StringTokenizer(r.readLine());
			int b=Integer.parseInt(s.nextToken());
			l.get(a-1).add(b-1);
			l.get(b-1).add(a-1);
		}
		Node root =null;
		try{
		root= makeTree(l, 0, -1);
		}catch(Exception e){return;}
		
		
		char[] str = r.readLine().toCharArray();
		if(str.length<n) return;
		
		init(root,  str);
		
		int[] inorda =new int[100000];
		int[] getorder =new int[100000];//= new int[n];
		try{
			inorda=new int[n];
		inord(root, 0, inorda);
		}
				catch(Exception e)
				{
					pw.println("NO");
				}
				try{
		getorder = new int[n];
		}
				catch(Exception e)
				{
					pw.println("NO");
				}
		for(int i=0;i<n;i++)
			getorder[inorda[i]]=i;
		while(!s.hasMoreTokens())s= new StringTokenizer(r.readLine());
		
		int m = Integer.parseInt(s.nextToken());
		
		for(int i=0;i<m;i++)
		{
			while(!s.hasMoreTokens())s= new StringTokenizer(r.readLine());
			if(s.nextToken().charAt(0)=='0')
			{
				int x = Integer.parseInt(s.nextToken());
				while(!s.hasMoreTokens())s= new StringTokenizer(r.readLine());
				char c = s.nextToken().charAt(0);
				try{
				change(root,x-1, getorder[x-1],c);
				}
				catch(Exception e)
				{
					pw.println("NO");
				}
			}
			else
			{
				while(!s.hasMoreTokens())s= new StringTokenizer(r.readLine());
				int x = Integer.parseInt(s.nextToken());
				try{
				if(ans(root, x-1, getorder[x-1]))
					pw.println("YES");
				else pw.println("NO");
				}
				catch(Exception e)
				{
					pw.println("NO");
				}
			}
		}
		r.close();
		pw.close();
		return;
	}
}