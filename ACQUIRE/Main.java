#define long1 long long int
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
long1 min(long1 a,long1 b)
	{
	;	return a<b?a:b;
	}
	int main()
	{
		
	int M,N,i;
	pair<int,int> l[50000];
	pair<int,int> a[50000];
	scanf("%d",&M);
	for (i=0; i<M; i++)
		scanf("%d %d",&a[i].first,&a[i].second);
	//Sort first by height and then by width (arbitrary labels)
	sort(l,l+M);
		long prevy=l[M-1].second;
		int k=1;
		
		a[0]=l[M-1];
		prevy=a[0].second;
		//System.out.println(a[0]);
		for(int i=M-2;i>=0;i--)
		{
			if(l[i].second>prevy) 
			{
				a[k]=l[i];
				//System.out.println(a[k]);
				k++;
				prevy=l[i].second;
				
			}
			
		}
		//for(int i=0;i<k;i++)System.out.println(a[i]);
		
		long[] dp  = new long[k+1];
		dp[0]=0;
		for(int i=1;i<=k;i++)
		{
			dp[i]=MAX_LONG;
			for(int j=0;j<i;j++)
			{
				dp[i] = min(dp[i], dp[j]+a[i-1].second*a[j].first);
			}
		}
		printf("%lld\n", dp[k]);
	}
