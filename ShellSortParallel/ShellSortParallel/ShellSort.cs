using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ShellSortParallel
{
    public class ShellSort
    {
        public void shellSort(int k, int h, int N, int[] A) {
            int i, j, v;
            for (i = k; i + h < N; i++)
            {
                j = i + h;
                v = A[j];

                while (j - h >= 0 && v < A[j - h])
                {
                    A[j] = A[j - h];
                    j = j - h;
                }

                A[j] = v;
            }

        }
    }
}
