package com.poker.file;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.contains;

public class FileReaderTest {

    @Test(expected = FileNotFoundException.class)
    public void shouldThrowWhenFileNotFound() throws Exception {
        new FileReader("nonExisting").readFile(System.out::println);
    }

    @Test
    public void shouldCallSupplierLineByLine() throws Exception {
        List<String> lines = new ArrayList<>();
        new FileReader("test.txt").readFile(lines::add);
        Assert.assertThat(lines, contains("line1", "line2"));
    }
}