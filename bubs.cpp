#include<iostream>
#include "omp.h"
#include<omp.h>
#include<chrono>
using namespace std;
using namespace std::chrono;

void binarysearchx(int a[],int start,int end,int key, int rank)
{
    while(start<=end){
        int m = (start+end)/2;
        if(a[m]==key){
            cout<<"Found by Process\t"<<rank<<endl;
            return;
        }
        else if(a[m]<key){
            start = m+1;
        }
        else{
            end = m-1;
        }
    }
    cout<<"\nNot Found By Process\t"<<rank<<endl;
}
void perform(int a[],int n,int key){
    omp_set_num_threads(4);
    time_point<system_clock> start1,end1,end2,end3,end4;
    start1 = system_clock::now();
    #pragma omp parallel sections
		{
			#pragma omp section
			{
				binarysearchx(a,0,n/4,key,1);
                end1 = system_clock::now();
			}
			#pragma omp section
			{
				binarysearchx(a,n/4,n/2,key,2);
                end2 = system_clock::now();
			}
            #pragma omp section
			{
				binarysearchx(a,n/2,3*n/4,key,3);
                end3 = system_clock::now();
			}
            #pragma omp section
			{
				binarysearchx(a,3*n/4,n,key,4);
                end4 = system_clock::now();
			}            
		}
        duration<double> total = end1 - start1;
        cout << "\nParallel time is for 1 is \t" << total.count() * 1000 << "\n";
        total = end2-start1;
        cout << "Parallel time is for 2 is \t" << total.count() * 1000 << "\n";
        total = end3-start1;
        cout << "Parallel time is for 3 is \t" << total.count() * 1000 << "\n";
        total = end3-start1;
        cout << "Parallel time is for 4 is \t" << total.count() * 1000 << "\n";
                
}
int main(){
    int n=12000000;
    int key = 454391;
    int *a = new int[n];
    for(int i=0;i<n;i++){
        a[i] =i+1;
    }
    perform(a,n,key);
    return 0;
}