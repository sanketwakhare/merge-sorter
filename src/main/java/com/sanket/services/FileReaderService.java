package com.sanket.services;

import java.util.List;
import java.util.concurrent.Callable;

public class FileReaderService implements Callable<List<Integer>> {

    private final String fileName;

    public FileReaderService(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Integer> call() {
        return FileService.getListFromFile(this.fileName);
    }
}
