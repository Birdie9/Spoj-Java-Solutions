#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<math.h>
int mat[7][7];
int colsum[7];
int shifts[7];
int n;
void shiftrow(int row)
{
	int i;
	for(i=0;i<n;i++)
	{
		colsum[i]-=mat[row][i];
	}
	int temp=mat[row][n-1];
	for(i=n-2;i>=0;i--) mat[row][i+1]=mat[row][i];
	mat[row][0]=temp;
	for(i=0;i<n;i++)
	{
		colsum[i]+=mat[row][i];
	}
}
void shift(){
	int cur=n;
	do{
		cur--;
		shifts[cur]= (shifts[cur]+1)%n;
		shiftrow(cur);

	}while(shifts[cur]==0);
}
int getMax()
{
	int i;
	int mins = -1000000;
	for(i=0;i<n;i++)if(mins<colsum[i]) mins=colsum[i];
	return mins;
}
int main()
{
	int i,j;
	while(1){
		scanf("%d",&n);
		if(n==-1) return 0;
		memset(colsum, 0, sizeof(colsum));
		memset(shifts, 0, sizeof(shifts));
		for(i=0;i<n;i++)
		{
			for(j=0;j<n;j++)
			{
				scanf("%d",&mat[i][j]);
				colsum[j]+=mat[i][j];
			}
		}
		int mins = getMax();
		
		for(i=1;i<(int)pow((double)n,(double)n);i++)
		{
			shift();
			int temp=getMax();
			if(temp<mins) mins=temp;
		}
		printf("%d\n",mins);
	}
	return 0;
}