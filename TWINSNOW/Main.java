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
class Flake
{
	int[] arms;
	int[] arms2;
	int idx;
	Flake(int[] a)
	{
		idx=0;
		arms2 = new int[6];
		arms = new int[6];
		int minid =0;
		for(int i=0;i<6;i++) 
			{
				if(a[i]<a[minid]) minid=i;
				arms[i] = a[i];
				arms2[i]=a[i];
			}
			if(minid!=0)
			{
				int idx=0;
				int st=minid;
				while(idx<6)
				{
					arms2[idx]=a[st];
					idx++;
					st++;
					st%=6;
				}
			}
			Arrays.sort(arms);
		
	}
}

public class Main{
	static int maxlen;
	static boolean check(int[] a ,int[] b)
	{
		boolean ch = true;
		for(int i=0;i<6;i++)
		{
			if(a[i]!=b[i]) ch=false;
		}
		if(ch) return true;
		for(int i=5;i>=0;i--)
		{
			if(a[i]!=a[5-i]) return false;
		}
		return true;
	}
	public static void cntsort(Flake[] flakes, int st, int en)
	{
		int[] temp = new int[maxlen+1];
		for(int i=st;i<en;i++)
		{
			temp[flakes[i].arms[flakes[i].idx]] ++;
		}
		for(int i=1;i<=maxlen;i++) temp[i]=temp[i-1]+temp[i];
		Flake[] temp2 = new Flake[en-st];
		for(int i=en-1;i>=st;i--)
		{
			//System.out.println(temp2.length+" "+( temp[flakes[i].arms[flakes[i].idx]]-1)+", "+temp.length+" "+flakes[i].arms[flakes[i].idx] );
			temp2[temp[flakes[i].arms[flakes[i].idx]]-1]=flakes[i];
			temp[flakes[i].arms[flakes[i].idx]]--;
		}
		for(int i=0;i<temp2.length;i++)
		{
			flakes[st+i]=temp2[i];
		}
	}
	public static void main(String[] args)
	{

		InputReader r = new InputReader(new BufferedInputStream(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		int n = r.readInt();

		Flake[] flakes = new Flake[n];
		for(int i=0;i<n;i++)
		{
			int[] temp = new int[6];
			for(int j=0;j<6;j++) 
				{
					temp[j]=r.readInt();
					if(maxlen<temp[j]) maxlen=temp[j];

				}
			flakes[i] = new Flake(temp);
		}
		Arrays.sort(flakes,new Comparator<Flake>(){
			public int compare(Flake a, Flake b)
			{
				for(int i=0;i<6;i++)
				{
					if(a.arms[i]!=b.arms[i]) return a.arms[i]-b.arms[i];
				}
				return 0;
			}
			
		});
		/*int st=0;
		for(int curidx=1;curidx<6;curidx++)
		{
			st=0;
			int previdx=curidx-1;
			while(st<n-1)
			{
				int en=st;
				while(en<n && flakes[en].arms[previdx]==flakes[st].arms[previdx] )
					{
						flakes[en].idx=curidx;
						en++;
					}
				cntsort(flakes,st,en);
				st=en;
			}
		}*/
		/*for(int i=0;i<n;i++)
		{
			for(int j=0;j<6;j++) System.out.print(flakes[i].arms[j]+		" ");
				System.out.println();

		}*/
		int[] cmp =flakes[0].arms;
		int[] cmp2= flakes[0].arms2;
		for(int i=1;i<n;i++)
		{
			boolean eq=true;
			for(int j=0;j<6;j++)
			{
				if(cmp[j]!=flakes[i].arms[j]) eq=false;
			}
			if(eq && check(flakes[i].arms2,cmp2 ))
			{
				System.out.println("Twin snowflakes found.");
				return;
			}
			else {
				cmp = flakes[i].arms;
				cmp2 = flakes[i].arms2;			}
		}
		System.out.println("No two snowflakes are alike.");
		return;
	}
}