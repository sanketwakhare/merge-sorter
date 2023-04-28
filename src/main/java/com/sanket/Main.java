package com.sanket;

import com.sanket.services.FileReaderService;
import com.sanket.services.FileWriterService;
import com.sanket.services.InputFileNameFilter;
import com.sanket.sort.merge.MergeSort;
import com.sanket.sort.merge.SortedListMerger;

import java.io.File;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // read input files from given path
        File path = new File("files");
        String[] fileNames = path.list(InputFileNameFilter.getInstance());

        if (!Objects.isNull(fileNames)) {

            // Step 1: Read all files
            List<Thread> fileReaderThreads = new ArrayList<>();
            List<FileReaderService> fileReaderTasks = new ArrayList<>();
            for (String file1 : fileNames) {
                // maintain separate thread for reading input files
                FileReaderService fileReaderService = new FileReaderService("files/" + file1);
                Thread t = new Thread(fileReaderService);
                t.start();
                fileReaderTasks.add(fileReaderService);
                fileReaderThreads.add(t);
            }
            for (Thread thread : fileReaderThreads) {
                thread.join();
            }
            System.out.println("All files read successfully");

            // Step 2: Sort each list
            List<Integer> integers;
            List<Integer> result;
            List<Future<List<Integer>>> futureList = new ArrayList<>();

            ExecutorService executorService = Executors.newFixedThreadPool(10);
            for (FileReaderService currFileReaderTask : fileReaderTasks) {
                // sort list
                integers = currFileReaderTask.getList();
                MergeSort<Integer> sorter = new MergeSort<>(integers);
                // wait for future object/promise
                Future<List<Integer>> sortedFuture = executorService.submit(sorter);
                futureList.add(sortedFuture);
            }
            executorService.shutdown();

            // Step 3: Merge all sorted lists
            Queue<Future<List<Integer>>> mergedFutures = new LinkedList<>();
            executorService = Executors.newFixedThreadPool(10);
            for (int i = 0; i < futureList.size(); i += 2) {

                if (i == futureList.size() - 1) {
                    mergedFutures.add(futureList.get(i));
                    continue;
                }
                Future<List<Integer>> sortedFuture1 = futureList.get(i);
                Future<List<Integer>> sortedFuture2 = futureList.get(i + 1);
                mergeSortedFutureLists(executorService, mergedFutures, sortedFuture1, sortedFuture2);
            }

            // keep merging until only one list is left
            while (mergedFutures.size() > 1) {
                Future<List<Integer>> mergedFuture1 = mergedFutures.poll();
                Future<List<Integer>> mergedFuture2 = mergedFutures.poll();
                mergeSortedFutureLists(executorService, mergedFutures, mergedFuture1, mergedFuture2);
            }

            // get the final result
            System.out.println(mergedFutures.size());
            result = mergedFutures.poll().get();
            executorService.shutdown();

            System.out.println("total elements: " + result.size());
            System.out.println(result);

            // Step 4: Write the result to output file
            executorService = Executors.newFixedThreadPool(2);
            // maintain separate thread for writing result to output file
            // write the result to file
            FileWriterService fileWriterService = new FileWriterService("files/out.txt", result);
            Future<?> fileWriterFuture = executorService.submit(fileWriterService);
            fileWriterFuture.get();

            // shutdown the executor service
            executorService.shutdown();
        }
    }

    private static void mergeSortedFutureLists(ExecutorService executorService, Queue<Future<List<Integer>>> mergedFutures, Future<List<Integer>> mergedFuture1, Future<List<Integer>> mergedFuture2) throws InterruptedException, ExecutionException {
        List<Integer> mergedList1 = mergedFuture1.get();
        List<Integer> mergedList2 = mergedFuture2.get();

        SortedListMerger<Integer> merger = new SortedListMerger<>(mergedList1, mergedList2);
        Future<List<Integer>> mergedFuture = executorService.submit(merger);

        mergedFutures.add(mergedFuture);
    }
}
