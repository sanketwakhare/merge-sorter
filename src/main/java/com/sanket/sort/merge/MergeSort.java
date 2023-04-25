package com.sanket.sort.merge;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MergeSort<T extends Comparable<T>> implements Callable<List<T>> {

    List<T> numbers;

    public MergeSort(List<T> list) {
        if (list == null || list.size() == 0) this.numbers = new ArrayList<>();
        else this.numbers = list;
    }

    public List<T> sort(List<T> list, int startIndex, int endIndex) throws Exception {

        // initialize result list
        List<T> result = new ArrayList<>();

        // edge cases
        if (startIndex > endIndex || startIndex == -1 || endIndex == -1) return result;
        // if only single element is present, return
        if (startIndex == endIndex) {
            result.add(list.get(startIndex));
            return result;
        }

        // divide and conquer
        int mid = (endIndex - startIndex) / 2 + startIndex;

        MergeSort<T> leftSorter = new MergeSort<>(list.subList(startIndex, mid + 1));
        MergeSort<T> rightSorter = new MergeSort<>(list.subList(mid + 1, endIndex + 1));

        // add threading for the merge sort
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<List<T>> leftSortedFuture = executorService.submit(leftSorter);
        Future<List<T>> rightSortedFuture = executorService.submit(rightSorter);

        List<T> left = leftSortedFuture.get();
        List<T> right = rightSortedFuture.get();

        executorService.shutdown();

        // combine the sorted lists
        return merge(left, right);
    }

    public List<T> merge(List<T> left, List<T> right) {

        List<T> result = new ArrayList<>();
        int leftIndex = 0;
        int rightIndex = 0;

        // compare and add the elements to result list
        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex).compareTo(right.get(rightIndex)) < 0) {
                result.add(left.get(leftIndex));
                leftIndex++;
            } else {
                result.add(right.get(rightIndex));
                rightIndex++;
            }
        }

        // add the remaining elements from left or right lists
        while (leftIndex < left.size()) {
            result.add(left.get(leftIndex));
            leftIndex++;
        }
        while (rightIndex < right.size()) {
            result.add(right.get(rightIndex));
            rightIndex++;
        }

        // return the combined result list
        return result;
    }

    @Override
    public List<T> call() throws Exception {
        // initialize indexes based on numbers input and call sort method
        int startIndex = this.numbers.size() > 0 ? 0 : -1;
        int endIndex = this.numbers.size() > 0 ? this.numbers.size() - 1 : -1;
        return this.sort(this.numbers, startIndex, endIndex);
    }
}
