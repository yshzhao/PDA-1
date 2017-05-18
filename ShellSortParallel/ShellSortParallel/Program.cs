using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace ShellSortParallel
{
    class Program
    {
        static void Main(string[] args)
        {
            List<Thread> threads = new List<Thread>();
            int N;
            int[] A = {1,3,2,12,6,30,15,11,23};
            N = A.Length;
            int h;
            h = N / 2;
            ShellSort sort = new ShellSort();
            while (h > 0)
            {
                for (int k = 0; k < h; k++)
                {
                    Thread t = new Thread(() => sort.shellSort(k,h,N,A));
                    t.Start();
                    threads.Add(t);
                }

                foreach (var thread in threads)
                    thread.Join();

                h = h / 2;
            }

            for(int i = 0; i < N ; i++)
            {
                Console.WriteLine(A[i]);
            }
            Console.ReadLine();
        }
    }
}
