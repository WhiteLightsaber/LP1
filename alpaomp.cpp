#include <iostream>
#include <chrono>
#include <omp.h>
#include "omp.h"
using namespace std;
using namespace std::chrono;

void bubble(int a[], int n)
{
    time_point<system_clock> start, end;
    start = system_clock::now();
    for (int i = 0; i < n - 1; i++)
    {
        for (int j = 0; j < n - 1; j++)
        {
            if (a[j + 1] > a[j])
            {
                int temp = a[j + 1];
                a[j + 1] = a[j];
                a[j] = temp;
            }
        }
    }
    end = system_clock::now();
    duration<double> total = end - start;
    cout << "Serialy time is \t" << total.count() * 1000 << "\n";
}
void generate(int a[], int b[], int n)
{
    for (int i = 0; i < n; i++)
    {
        a[i] = b[i] = n - 1;
    }
}
void parallelbubble(int a[], int n)
{
    time_point<system_clock> start, end;
    start = system_clock::now();
    int first = 0;
    omp_set_num_threads(5);
    for (int i = 0; i < n - 1; i++)
    {
        first = i % 5;
#pragma parallel for
        {
            for (int j = first; j < n - 1; j = j + 2)
            {
                if (a[j + 1] > a[j])
                {
                    int temp = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = temp;
                }
            }
        }
    }

    end = system_clock::now();
    duration<double> total = end - start;
    cout << "Parallel time is \t" << total.count() * 1000 << "\n";
}

int main()
{
    int n = 90000;
    int *a = new int[n];
    int *b = new int[n];
    generate(a, b, n);
    bubble(a, n);
    parallelbubble(b, n);
    return 0;
}