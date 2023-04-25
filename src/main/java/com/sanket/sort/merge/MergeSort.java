package com.sanket.sort.merge;

import java.util.ArrayList;
import java.util.List;

public class MergeSort<T extends Comparable<T>> {

    public List<T> sort(List<T> list, int startIndex, int endIndex) {

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
        List<T> left = sort(list, startIndex, mid);
        List<T> right = sort(list, mid + 1, endIndex);

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
}
