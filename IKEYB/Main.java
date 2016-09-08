import java.io.*;
import java.util.*;
public class Main
{
	static int[] f;
	static PrintWriter pw;
	static String letters,keys;
	static int[] cumf;
	static int[] cumf2;
	static int[][] dp;
	static int[][] ans;
	static int L,K;
	static void do1(int test){
		pw.println("Keypad #"+test+":");
		int i=0;
		for(i=0;i<K-L;i++)
		{
			pw.print(keys.charAt(i)+": ");
			pw.println();
		}
		for(int j=0;i<K;i++, j++)
		{
			pw.print(keys.charAt(i)+": "+letters.charAt(j));
			pw.println();
		}
	}
	static int count(int curKey, int curLetter)
	{
		if(dp[curKey][curLetter]!=-1) return dp[curKey][curLetter];	
		int base = curLetter;
		int cost=Integer.MAX_VALUE;
		int ansi=L-1;
		int sub = curLetter==0? 0: cumf[curLetter-1];
		int sub1 = curLetter==0? 0: cumf2[curLetter-1];
		if(curKey==K-1)
		{
			int temp = cumf[L-1]-sub;
			temp = temp - (base* (cumf2[L-1]-sub1));
			ans[curKey][curLetter]= L-1;
			return dp[curKey][curLetter]= temp;
		}
		for(int i=curLetter; i<L;i++ )
		{
			int temp = cumf[i]-sub;
			temp = temp - (base* (cumf2[i]-sub1));
			if(i!=L-1)
				temp += count(curKey+1, i+1);
			if(temp<cost)
			{
				cost=temp;
				ansi=i; //till what character on this key
			}
		}
		ans[curKey][curLetter]= ansi;
		return dp[curKey][curLetter]= cost;
	}
	public static void main(String[]  args) throws java.lang.Exception
	{
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		pw = new PrintWriter(System.out);
		int t = Integer.parseInt(new StringTokenizer(r.readLine()).nextToken());
		for(int test=1;test<=t;test++)
		{
			StringTokenizer st =new StringTokenizer(r.readLine());
			K = Integer.parseInt(st.nextToken());
			L= Integer.parseInt(st.nextToken());
			keys = r.readLine();
			letters = r.readLine();
			f = new int[L];
			dp = new int[K][L];
			ans = new int[K][L];
			for(int i=0;i<K;i++)
				for(int j=0;j<L;j++)dp[i][j]=-1;
			cumf = new int[L];
			cumf2 = new int[L];
			for(int i=0;i<L;i++)
			{
				f[i] = Integer.parseInt(new StringTokenizer(r.readLine()).nextToken());
				if(i>0) cumf[i] = cumf[i-1];
				if(i>0) cumf2[i] = cumf2[i-1];
				cumf[i] += (i+1) *f[i];
				cumf2[i] +=  f[i];
			}
			//for(int i=0;i<L;i++) pw.print(cumf[])
			if(L<=K)
			{
				do1(test);
				continue;
			}
			count(0,0);
			pw.println("Keypad #"+test+":");
			int cur = 0;
			for(int i=0;i<K;i++)
			{
				pw.print(keys.charAt(i)+": ");
				for(int j=cur;j<=ans[i][cur];j++)
				{
					pw.print(""+letters.charAt(j));
				}
				cur=ans[i][cur]+1;
				pw.println();
			}
		}
		pw.close();
		return;
	}
}