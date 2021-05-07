package com.solutions.vasilieva;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainIONIO {
    private static final Logger LOG = LogManager.getLogger(MainIONIO.class);
    public static void main(String[] args) {
        IOHome io = new IOHome();
        NIOHome nio = new NIOHome();
        Path path = Paths.get("D:\\Study\\NIX");
        File file = new File("D:\\Study\\NIX");
        LOG.info("gzip" + io.gzip("D:\\Study\\NIX\\test1", "D:\\Study\\NIX"));
        LOG.info("gzip" + io.searchText("D:\\Study\\NIX\\test1.txt", "123"));
        LOG.info("Res: " + io.search(file, ".txt"));
        LOG.info("Res: " + nio.search(path, ".txt"));
    }
}
