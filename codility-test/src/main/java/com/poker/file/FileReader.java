package com.poker.file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class FileReader {

    private final String fileName;

    public FileReader(String fileName) {
        this.fileName = fileName;
    }

    public void readFile(Consumer<String> supplier) throws URISyntaxException, IOException {
        try(Stream<String> lines = Files.lines(Paths.get(getUrl().toURI()))) {
            lines.forEach(supplier);
        }
    }

    private URL getUrl() throws FileNotFoundException {
        final URL resource = getClass().getClassLoader().getResource(fileName);
        if (resource == null) {
            throw new FileNotFoundException("The file "+fileName + " is not found.");
        }
        return resource;
    }

}
