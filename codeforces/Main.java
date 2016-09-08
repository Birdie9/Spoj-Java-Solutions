import java.io.*;
import java.util.*;
public class Main
{
	public static void main(String[] args) throws java.lang.Exception
	{
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw= new PrintWriter(System.out);
		StringTokenizer st =new StringTokenizer(r.readLine());
		int n=Integer.parseInt(st.nextToken()), a=Integer.parseInt(st.nextToken()), b=Integer.parseInt(st.nextToken()),t=Integer.parseInt(st.nextToken());
		char[] s = r.readLine().toCharArray();
		int T=t;
		int num1=0,num2=0,num3=0,num4=0;
		t-=1;
		if(s[0]=='w')t-=b;
		if(t>=0) num1=1;
		if(num1==0) {pw.println(0); pw.close();return;}
		//go right
		int t3=0;
		t-=a;
		for(int i=1;t>0 && i<s.length;i++)
		{
			if(s[i]=='h')
			{
				t-=1;
				num1++;
				t3+=1;
				t3+=a;
				t-=a;
			}
			else if(t>b && s[i]=='w')
			{
				t-= (b+1);
				t3+=(b+1);
				t3+=a;
				num1++;
				t-=a;
			}
			else break;
		}
		//System.out.println(num1);
		if(num1==n){pw.println(n); pw.close();return;}
		t=T;
		t-=1;
		if(s[0]=='w')t-=b;
		num2=1;

		//go left
		t-=a;
		int t2=0;
		for(int i=n-1;t>0 && i>0;i--)
		{
			if(s[i]=='h')
			{
				t-=1;
				t2+=1;
				t2+=a;
				num2++;
				t-=a;
			}
			else if(t>b && s[i]=='w')
			{
				t-= (b+1);
				t2+=(b+1);
				t2+=a;
				num2++;
				t-=a;
			}
			else break;
		}
		//System.out.println(num2);
		if(num2==n){pw.println(n); pw.close();return;}
		//System.out.println("leftidx= "+leftidx+"prevt = "+prevt);
		t=T;
		t-=1;
		if(s[0]=='w')t-=b;
		num3=1;
		int maxnum=0;
		int leftidx=n-num2+1;
		int prevt=t2;
		t-=a;
		//System.out.println("leftidx= "+leftidx+"prevt = "+prevt);
		for(int i=1;t>0 && i<s.length;i++)
		{
			if(s[i]=='h')
			{
				t-=1;
				num3++;
				t-= (i*a);
				//System.out.println("t="+t);
				if(prevt<=t) 
				{
					maxnum = max(maxnum, num3-leftidx+n);
				}
				else
				{
					for(;leftidx<n;)
					{
						prevt-=(s[leftidx]=='h'?1:(b+1));
						prevt-=a;
						leftidx++;
						if(prevt<=t) 
						{
							maxnum = max(maxnum, num3-leftidx+n);
							break;
						}
					}
					if(leftidx==n) break;

				}
				//System.out.println("leftidx= "+leftidx+"prevt = "+prevt);

			}
			else if(t>b && s[i]=='w')
			{
				t-= (b+1);
				num3++;
				t-= (i*a);
				if(prevt<=t) 
				{
					maxnum = max(maxnum, num3-leftidx+n);
				}
				else
				{
					for(;leftidx<n;)
					{
						prevt-=(s[leftidx]=='h'?1:(b+1));
						prevt-=a;
						leftidx++;
						if(prevt<=t) 
						{
							maxnum = max(maxnum, num3-leftidx+n);
							break;
						}
					}
					if(leftidx==n) break;

				}
				//System.out.println("leftidx= "+leftidx+"prevt = "+prevt);
			}
			else break;
			t+= (i*a);
			t-=a;
		}
		//System.out.println("maxnum="+maxnum);
		t=T;
		t-=1;
		if(s[0]=='w')t-=b;
		num4=1;
		int maxnum2=0;
		int rightidx=num1-1;
		prevt=t3;
		t-=a;
		for(int i=n-1;t>0 && i>0;i--)
		{
			if(s[i]=='h')
			{
				t-=1;
				num4++;
				t-= ((n-i)*a);
				
				if(prevt<=t) 
				{
					maxnum2 = max(maxnum2, num4+rightidx);
				}
				else
				{
					for(;rightidx>0;)
					{
						prevt-=(s[rightidx]=='h'?1:(b+1));
						prevt-=a;
						rightidx--;
						if(prevt<=t) 
						{
							maxnum2 = max(maxnum2, num4+rightidx);
							break;
						}
					}
					if(rightidx==0) break;

				}
			}
			else if(t>b && s[i]=='w')
			{
				t-= (b+1);
				num4++;
				t-= ((n-i)*a);
				
				if(prevt<=t) 
				{
					maxnum2 = max(maxnum2, num4+rightidx);
				}
				else
				{
					for(;rightidx>0;)
					{
						prevt-=(s[rightidx]=='h'?1:(b+1));
						prevt-=a;
						rightidx--;
						if(prevt<=t) 
						{
							maxnum2 = max(maxnum2, num4+rightidx);
							break;
						}
					}
					if(rightidx==0) break;
				}
			}
			else 
				break;
			t+=((n-i)*a);
			t-=a;
		}
		//System.out.println(maxnum2);
		pw.println(max(maxnum2,maxnum,num1,num2));
		pw.close();
	}
	public static int max(int a,int b)
	{
		return a>b?a:b;
	}
	public static int max(int a,int b,int c,int d)
	{
		return max(max(a,b),max(c,d));
	}
}
