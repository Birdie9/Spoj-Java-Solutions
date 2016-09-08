import java.io.*;
import java.math.*;
import java.util.*;
public class Main
{
	 static int[][] dp;
	 static int[][] sum;
	static int find(String l,int k, int st, int en)
	{
		
		if(dp[st][en]!=-1) return dp[st][en];
		int ret=0;
	
		for(int i=1;i<l.length();i++)
		{
			int f =sum[en+1][i+en];
			//System.out.print(f.toString()+" ");
			if(f>=k)
				ret += find(l.substring(i, l.length()), f, en+1, i+en);
		}
		if(sum[en+1][en+l.length()] >= k ) ret++;
		//System.out.println();
		//System.out.print(l+" "+ st+" " +en);
		//System.out.println(" "+ ret);
		return dp[st][en]= ret;
	}
	public static void main(String[] args) throws java.lang.Exception
	{
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw= new PrintWriter(System.out);
		int test=0;
		while(true)
		{
			String l = r.readLine();
			if(l.equals("bye")) break;
			test++;
			dp = new int[l.length()+1][l.length()+1];
			sum=  new int[l.length()+1][l.length()+1];
			for(int i=0;i<l.length()+1;i++) for(int j=0;j<l.length()+1;j++) dp[i][j]=-1;
			for(int len=1;len<=l.length();len++)for(int strt=1;strt<=l.length()-len+1;strt++)
			{
				int en= strt+len-1;
				if(len==1) sum[strt][en]= l.charAt(strt-1) - '0';
				else sum[strt][en] = sum[strt][en-1]+ l.charAt(en-1) -'0';
			}
			int ans=find(l,0,0, 0  );
			pw.println(test+". " +ans);

		}
		pw.close();
		return;

	}
}