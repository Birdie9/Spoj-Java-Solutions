import java.util.*;
import java.io.*;

public class Main{
	static void getAtt(boolean[] attv,boolean[] tv,int[][][] or, int[][] tow,int r,int s)
	{
		for(int i=0;i<tv.length;i++)
		{
			if(tv[i])
			{
				int a=tow[i][0];
				int b=tow[i][1];
				if(i%2==0)
				{
					if(or[a][b][0]==0)
					{
						for(int i1=a-1;i1>=0;i1--)
							{if(or[i1][b][0]==-2)
								attv[or[i1][b][2]]=true;
							if(or[i1][b][0]==-4) break;}
					}
					else
					{
						for(int i1=a+1;i1<r;i1++)
							{if(or[i1][b][0]==-2)
								attv[or[i1][b][2]]=true;
							if(or[i1][b][0]==-4) break;}
					}
				}
				else
				{
					if(or[a][b][1]==0)
					{
						for(int i1=b-1;i1>=0;i1--)
							{if(or[a][i1][0]==-2)
								attv[or[a][i1][2]]=true;
							if(or[a][i1][0]==-4)
								break;
							}
					}
					else
					{
						for(int i1=b+1;i1<s;i1++)
							{if(or[a][i1][0]==-2)
								attv[or[a][i1][2]]=true;
							if(or[a][i1][0]==-4)
								break;
							}
					}
				}
			}
		}
		//for(int i=0;i<attv.length;i++)if(attv[i]) System.out.println(i);
	}
	static int not(int a, int t)
	{
		if(a<t) return t+a;
		else return a-t;
	}
	static ArrayList<Integer> getT(int[][][] or, int i, int j,int r,int s, boolean[] tv, int[][] tow)
	{
	//	System.out.println(i+" "+j+"--");
		ArrayList<Integer> ret = new ArrayList<Integer> ();
		for(int a=i-1;a>=0;a--)
		{
			if(or[a][j][0]==-4) break;
			else if(or[a][j][0]==-1) {ret.add(or[a][j][2]);break;}
			else if(or[a][j][0]==1) {ret.add(or[a][j][2]);break;}
			else if(or[a][j][0]>=0) break;
		}
		for(int a=i+1;a<r;a++)
		{
			if(or[a][j][0]==-4) break;
			else if(or[a][j][0]==-1){ ret.add(or[a][j][2]); break;}
			else if(or[a][j][0]==0) {ret.add(or[a][j][2]);break;}
			else if(or[a][j][0]>0) break;
		}
		for(int a=j-1;a>=0;a--)
		{
			if(or[i][a][0]==-4) break;
			else if(or[i][a][1]==-1){ ret.add(or[i][a][2]+1);break;}
			else if(or[i][a][1]==1) {ret.add(or[i][a][2]+1);break;}
			else if(or[i][a][1]>=0) break;
		}
		for(int a=j+1;a<s;a++)
		{
		//	System.out.println("checking"+ i+" "+a+"="+or[i][a][1]);
			if(or[i][a][0]==-4) break;
			else if(or[i][a][1]==-1) {ret.add(or[i][a][2]+1);break;}
			else if(or[i][a][1]==0) {ret.add(or[i][a][2]+1);break;}
			else if(or[i][a][1]>0) break;
		}
		//if(ret.size()==1) return ret;
		//else
		 if(ret.size()==2)
		{
			// if(tv[ret.get(0)])
			// {
			// 	ret.remove(0);
			// }
			// else if(tv[ret.get(1)]) ret.remove(1);
			//return ret;
		}
		else if(ret.size()==3)
		{
			int t1=ret.get(0);
			int t2=ret.get(1);
			int t3=ret.get(2);
			if((tow[t1][0]==tow[t2][0] ) ||(tow[t2][1]==tow[t1][1])){
				tv[t1]=true;
				tv[t2]=true;
				int k=0;
				if((tow[t1][0]==tow[t2][0] ) ) k=1;
				or[tow[t1][0]][tow[t1][1]][k]=0;
				or[tow[t2][0]][tow[t2][1]][k]=1;
				ret.remove(0);
				ret.remove(0);
			}
			else if((tow[t2][0]==tow[t3][0] ) ||(tow[t2][1]==tow[t3][1])){
				tv[t2]=true;
				tv[t3]=true;
				int k=0;
				if((tow[t3][0]==tow[t2][0] ) ) k=1;
				or[tow[t3][0]][tow[t3][1]][k]=0;
				or[tow[t2][0]][tow[t2][1]][k]=1;
				ret.remove(2);
				ret.remove(1);
			}
			else
			{
				tv[t1]=true;
				tv[t3]=true;
				int k=0;
				if((tow[t3][0]==tow[t1][0] ) ) k=1;
				or[tow[t3][0]][tow[t3][1]][k]=0;
				or[tow[t1][0]][tow[t1][1]][k]=1;
				ret.remove(0);
				ret.remove(2);
			}
			//return ret;
		}

		return ret;
	}
	static char getOr(int a,int b)
	{
		if(a==0 && b==0) return '4';
		if(a==1 && b==0) return '1';
		if(a==0 && b==1) return '3';
		else return '2';
	}
	static void dfs2util(int lead,int cur, boolean[] done, ArrayList<ArrayList<Integer>> out, int[] leader)
	{
		leader[cur]=lead;
		done[cur]=true;
		for(int i:out.get(cur))
			if(!done[i])
				dfs2util(lead, i, done, out, leader);
	}
	static Stack<Integer> dfs2(ArrayList<ArrayList<Integer>> out,boolean[] tv,int t,Stack<Integer>  stk,int[] leader)
	{
		boolean[] done = new boolean[2*t];
		Stack<Integer> ret  =  new Stack<Integer>();
		for(int i=0;i<t;i++) if(tv[i])
		{
			done[i]=true;
			done[i+t]=true;
		}
		while(!stk.isEmpty())
		{
			int i=stk.pop();
			if(!done[i])
			{
				ret.push(i);
				dfs2util(i, i,done, out, leader);
			}
		}
		return ret;
	}
	static void dfs1util(int cur, boolean[]done, ArrayList<ArrayList<Integer>> out, Stack<Integer> ret)
	{
		done[cur]=true;
		for(int i: out.get(cur))
			if(!done[i])
				dfs1util(i, done, out, ret);
		ret.push(cur);
	}
	static Stack<Integer> dfs1(ArrayList<ArrayList<Integer>> out, boolean[] tv, int t){
		boolean[] done = new boolean[2*t];
		Stack<Integer> ret  =  new Stack<Integer>();
		for(int i=0;i<t;i++) if(tv[i])
		{
			done[i]=true;
			done[i+t]=true;
		}
		for(int i=0;i<2*t;i++)
		{
			if(!done[i]) dfs1util(i,done,out,ret);
		}
		return ret;
	}
	public static void main(String[] args) throws Exception
	{

		Main m = new Main();
		BufferedReader s1 = new BufferedReader(new InputStreamReader(System.in));
		String l = s1.readLine();
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st = new StringTokenizer(l);
		int r = Integer.parseInt(st.nextToken()), s = Integer.parseInt(st.nextToken());
		char[][] mat= new char[r][s];
		int[][][] or = new int[r][s][3];
		int[][] att = new int[r*s][2];
		int[][] tow = new int[r*s*2][2];
		int n=0;
		int t=0;
		for(int i=0;i<r;i++)
			{
				mat[i]=s1.readLine().toCharArray();
				for(int j=0;j<s;j++)
				{
					if(mat[i][j]=='n')
					{
						att[n][0]=i;
						att[n][1]=j;
						or[i][j][0]=-2;
						or[i][j][1]=-2;
						or[i][j][2]=n;
						n++;
					}
					else if(mat[i][j]=='T')
					{
						tow[t][0]=i;
						tow[t][1]=j;
						tow[t+1][0]=i;
						tow[t+1][1]=j;
						or[i][j][0]=-1;
						or[i][j][1]=-1;
						or[i][j][2]=t;//t=updown, t+1=rightleft
						t+=2;
					}
					else if(mat[i][j]=='#')
						{
							or[i][j][0]=-4;
							or[i][j][1]=-4;
							or[i][j][2]=-4;
						}
					else {
						or[i][j][0]=-3;
						or[i][j][1]=-3;
						or[i][j][2]=-3;
					}
				}
			}	
		boolean[] attv = new boolean[n];
		boolean[] tv = new boolean[t];
		for(int i=0;i<t;i++)
		{
			if(i%2==0)
			{
				for(int j=tow[i][0]-1;j>=0;j--)
				{
					if(mat[j][tow[i][1]]=='#') break;
					else if(mat[j][tow[i][1]]=='T')
					{
						tv[i]=true;
						or[tow[i][0]][tow[i][1]][0]=1;
					}
				}
				if(or[tow[i][0]][tow[i][1]][0]!=1)
				{
					for(int j=tow[i][0]+1;j<r;j++)
					{
						if(mat[j][tow[i][1]]=='#') break;
						else if(mat[j][tow[i][1]]=='T')
						{
							tv[i]=true;
							or[tow[i][0]][tow[i][1]][0]=0;
						}
					}
				}
			}
			else
			{
				for(int j=tow[i][1]-1;j>=0;j--)
				{
					if(mat[tow[i][0]][j]=='#') break;
					else if(mat[tow[i][0]][j]=='T')
					{
						tv[i]=true;
						or[tow[i][0]][tow[i][1]][1]=1;
					}
				}
				if(or[tow[i][0]][tow[i][1]][1]!=1)
				{
					for(int j=tow[i][1]+1;j<s;j++)
					{
						if(mat[tow[i][0]][j]=='#') break;
						else if(mat[tow[i][0]][j]=='T')
						{
							tv[i]=true;
							or[tow[i][0]][tow[i][1]][1]=0;
						}
					}
				}
			}
		}
		boolean[] tempn = new boolean[n];
		while(true){
			boolean d=false;
		for(int i1=0;i1<n;i1++)
		{
			if(tempn[i1]) continue;
			int i=att[i1][0];
			int j=att[i1][1];
			ArrayList<Integer> tlist = getT(or, i, j,r,s, tv,tow);
			if(tlist.size()==1)
			{
				tempn[i1]=true;
				d=true;
				int t1=tlist.get(0);
				//System.out.println("got"+t1);
				tv[t1]=true;
				if(t1%2==0)
				{
					if(tow[t1][0]<i)
					{
						or[tow[t1][0]][tow[t1][1]][0]=1;
					}
					else
					{
						or[tow[t1][0]][tow[t1][1]][0]=0;
					}
				}
				else
				{
					if(tow[t1][1]<j)
					{
						or[tow[t1][0]][tow[t1][1]][1]=1;
					}
					else
					{
						//System.out.println("here"+tow[t1][0]+" "+tow[t1][1]);
						or[tow[t1][0]][tow[t1][1]][1]=0;
					}
				}
			}
		}
		if(!d) break;
	}
		getAtt(attv, tv, or,tow,r,s);
		ArrayList<ArrayList<Integer>> out = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> in = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<2*t;i++) {out.add(new ArrayList<Integer>());in.add(new ArrayList<Integer>());}
		// 0--t-1--> ~x, t to 2*t-1-->x
		for(int i1=0;i1<n;i1++)
			if(!attv[i1])
			{
				int i=att[i1][0];
				int j=att[i1][1];
				ArrayList<Integer> tlist = getT(or, i, j,r,s, tv,tow);
				int t1= tlist.get(0);
				int t2=tlist.get(1);
				if(t1%2!=0)
				{
					t2^=t1;
					t1^=t2;
					t2^=t1;
				}
				if(tow[t1][0]<i)
				{
					t1+=t;
				}
				
				if(tow[t2][1]<j)
				{
					t2+=t;
				}
				//System.out.println(i+" "+j+" --> "+t1+" "+t2);
				out.get(not(t1, t)).add(t2);
				in.get(t2).add(not(t1,t));
				out.get(not(t2,t)).add(t1);
				in.get(t1).add(not(t2,t));
			}
		Stack<Integer> stk;
		stk = dfs1(out, tv, t);
		int[] leader = new int[2*t];
		int[] truthval = new int[2*t];
		for(int i=0;i<2*t;i++) truthval[i]=-1;
		Stack<Integer> revTop = dfs2(in,tv,t,stk,leader);
		while(!revTop.isEmpty())
		{
			int lead =  revTop.pop();
			if(truthval[lead]==-1) {
				truthval[leader[not(lead,t)]]=0;
				truthval[lead]=1;

			}
		}
		for(int i=0;i<r;i++)
		{
			{for(int j=0;j<s;j++)
				if(mat[i][j]=='T')
				{
					int t1 = or[i][j][2];
					//need truthval of t1 and t1+1
					if(or[i][j][0]==-1)
					{
						if(truthval[leader[t1]]==0) //facedown
						or[i][j][0]=1;
						else or[i][j][0]=0;
					}
					if(or[i][j][1]==-1)
					{
						if(truthval[leader[t1+1]]==0) //facedown
						or[i][j][1]=1;
						else or[i][j][1]=0;
					}
					pw.print(getOr(or[i][j][0], or[i][j][1]));
				}

				else pw.print(mat[i][j]);
			}
			pw.println();
		}
		pw.close();

	}
}
