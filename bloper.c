
#include<stdio.h>
#include<stdlib.h>
int dp[501][125251*2][2];
int base= 125250;
int main()
{
	int n,s,i,j;
	scanf("%d %d",&n,&s);
	int sum = (n*(n+1))/2;
	if(s>0)
	{
		if(s>sum) {printf("Impossible\n"); return 0;}
		sum = sum-s;
		if(sum&1) {printf("Impossible\n"); return 0;}
		sum =sum/2;
		char ops[n+1];
		ops[1]='+';
		for(i=0;i<=n;i++) ops[i]='+';
		for(i=n;i>1;i--)
		{
			if(sum>=i) {sum-=i;
			ops[i]='-';}
			if(sum==0) break;
		}
		if(sum) {printf("Impossible\n"); return 0;}
		for(i=1;i<n;i++)
		{
			printf("%d%c",i, ops[i+1]);
		}
		printf("%d\n",n);
	}
	else
	{
		sum = -sum;
		if(s<= sum) {printf("Impossible\n"); return 0;}
		sum = s-sum;
		if(sum&1) {printf("Impossible\n"); return 0;}
		sum =sum/2;
		char ops[n+1];
		sum--;
		for(i=0;i<=n;i++) ops[i]='-';
		ops[1]='+';
		for(i=n;i>1;i--)
		{
			if(sum>=i) {sum-=i;
			ops[i]='+';}
			if(sum==0) break;
		}
		if(sum) {printf("Impossible\n"); return 0;}
		for(i=1;i<n;i++)
		{
			printf("%d%c",i, ops[i+1]);
		}
		printf("%d\n",n);
	}
	
	return 0;
}