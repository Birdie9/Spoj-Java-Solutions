#include <iostream>  
#include <queue>  
#include<stdio.h>
#include <climits>
#define MAX_INT    1061109567
#define gc getchar_unlocked
using namespace std;  
  
class Node  
{  
public:  
    int v, d;  
    Node( int v1 ,int d1): v(v1), d(d1){}
    
};  
  
struct CompareNode : public std::binary_function<Node*, Node*, bool>                                                                                       
{  
  bool operator()(const Node* lhs, const Node* rhs) const  
  {  
     return lhs->d > rhs->d;  
  }  
};  
  

int n;
    int mat[1000][1000];
   int mat2[1000][1000];
   int fromS[1000];
   int fromT[1000];
   
void dijkstra(int s,int* dist)
    {
        bool done[n];
        for(int i=0;i<n;i++) {dist[i]=MAX_INT;done[i] = false;}
        dist[s] = 0;
        priority_queue<Node*,vector<Node*>, CompareNode > pq;
        pq.push(new Node(s,0));
        while(!pq.empty())
        {
            Node* cur= pq.top();
            //printf(">>>> %d\n",cur->d);
            pq.pop();
            if(cur->d==MAX_INT) return;
            if(done[cur->v]) continue;
            done[cur->v]=true;
            for( int i=0;i<n;i++)
            {
                if(mat[cur->v][i]!=MAX_INT && !done[i] && dist[i]>mat[cur->v][i]+dist[cur->v])
                {
                    dist[i] = mat[cur->v][i]+dist[cur->v];
                        pq.push(new Node(i,dist[i]));
                }
            }
            delete cur;
        }
        return;
    }

void dijkstra2(int s,int* dist)
    {
        bool done[n];
        for(int i=0;i<n;i++) {dist[i]=MAX_INT;done[i] = false;}
        dist[s] = 0;
        priority_queue<Node*,vector<Node*>, CompareNode > pq;
        pq.push(new Node(s,0));
        while(!pq.empty())
        {
            Node* cur= pq.top();
            //printf(">>>> %d\n",cur->d);
            pq.pop();
            if(cur->d==MAX_INT) return;
            if(done[cur->v]) continue;
            done[cur->v]=true;
            for( int i=0;i<n;i++)
            {
                if(mat2[cur->v][i]!=MAX_INT && !done[i] && dist[i]>mat2[cur->v][i]+dist[cur->v])
                {
                    dist[i] = mat2[cur->v][i]+dist[cur->v];
                        pq.push(new Node(i,dist[i]));
                }
            }
            delete cur;
        }
        return;
    }


    int scanint()
{
    register int c = gc();
    int x = 0;
    for(;(c<48 || c>57);c = gc());
    for(;c>47 && c<58;c = gc()) {x = (x<<1) + (x<<3) + c - 48;}
        return x;
}
 int main()
    {
        
        int test = scanint();
        for(;test>0;test--)
        {
            n=scanint();
            int m = scanint(), k = scanint(), s = scanint(), t = scanint();
            s--;
            t--;
           for(int i=0;i<n;i++)
            for(int j=0;j<n;j++) {mat[i][j]=MAX_INT;mat2[i][j]=MAX_INT;}
            for(int i=0;i<m;i++)
            {
                int a = scanint(), b= scanint(), c= scanint();
                a--;
                b--;
                mat[a][b]=c;
                mat2[b][a]=c;
            }
            
            
            dijkstra(s, fromS);
           
            dijkstra2(t,fromT);
            int shortest = fromS[t];
           // for(int i=0;i<n;i++) printf("%d ", fromS[i]);
             //               for(int i=0;i<n;i++) printf("%d ", fromT[i]);

            for(int i=0;i<k;i++)
            {
                int a = scanint(), b= scanint(), c= scanint();
                a--;
                b--;
                if(fromS[a]!=MAX_INT && fromT[b]!=MAX_INT &&( shortest > fromS[a] +c+ fromT[b]))
                {
                    shortest = fromS[a] +c+ fromT[b];
                }
                if(fromS[b]!=MAX_INT && fromT[a]!=MAX_INT && (shortest > fromS[b] +c+ fromT[a]))
                {
                    shortest = fromS[b] +c+ fromT[a];
                }
            }
            printf("%d\n", shortest==MAX_INT? -1:shortest);
        }
        
        return 0;
    }