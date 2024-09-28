package org.shubhamshankar.problems;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParallelMergeSort {
    private ExecutorService executor;
    ParallelMergeSort() {
    }

    void sort(ArrayList<Integer> arrList) {
        this.executor = Executors.newFixedThreadPool(5);
        int sz = arrList.size();
        helperSort(arrList, 0, sz-1);
        executor.shutdown();
    }

    void helperSort(ArrayList<Integer> arrList, int start, int end)  {
        if(start == end)
            return;

        System.out.println(start + " " + end);
        int mid = (start + end)/2;
        Integer resultInteger = 0;
        Future<Integer> leftSort =  executor.submit(() -> helperSort(arrList, start, mid), resultInteger);
        Future<Integer> rightSort =  executor.submit(() -> helperSort(arrList, mid+1, end), resultInteger);

        try {
            leftSort.get();
            rightSort.get();

            ArrayList<Integer> temp = new ArrayList<>();

            int ptr1 = start, ptr2 = mid + 1;
            while ((ptr1 <= mid) && (ptr2 <= end)) {
                if (arrList.get(ptr1) <= arrList.get(ptr2)) {
                    temp.add(arrList.get(ptr1));
                    ptr1++;
                } else {
                    temp.add(arrList.get(ptr2));
                    ptr2++;
                }
            }

            while ((ptr1 <= mid)) {
                temp.add(arrList.get(ptr1));
                ptr1++;
            }

            while ((ptr2 <= end)) {
                temp.add(arrList.get(ptr2));
                ptr2++;
            }

            for (int i = start; i <= end; ++i) {
                arrList.set(i, temp.get(i-start));
            }
        }
        catch (ExecutionException | InterruptedException e) {
            System.out.println("in error: " + e.getMessage() + " message over:  " + start + " " + end);
        }
    }

}
