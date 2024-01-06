package com.mainservice.extern.impl;

import com.mainservice.extern.MapReduceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class SparkMapReduceService implements MapReduceService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private ProcessBuilder processBuilder;
    private Process process = null;

    SparkMapReduceService() {
        String script = "/Users/yanjingsheng/Downloads/spark-3.5.0-bin-hadoop3/bin/spark-submit --class \"SparkApplication\" --master spark://yanjingshengdeMacBook-Pro.local:7077 /Users/yanjingsheng/IdeaProjects/spark/build/libs/spark-0.0.1-SNAPSHOT.jar";
        log.info(script);
        processBuilder = new ProcessBuilder(script);
    }

    @Override
    public String run() {
        String script = "/Users/yanjingsheng/Downloads/spark-3.5.0-bin-hadoop3/bin/spark-submit --class \"SparkApplication\" --master spark://yanjingshengdeMacBook-Pro.local:7077 /Users/yanjingsheng/IdeaProjects/spark/build/libs/spark-0.0.1-SNAPSHOT.jar";
        log.info(script);
        processBuilder = new ProcessBuilder("zsh", "-c", script);
        try {
            if (process == null) {
                process = processBuilder.start();
                new ProcessBuilder("zsh", "-c", "rm -r /Users/yanjingsheng/IdeaProjects/spark/output").start().waitFor();
            }
            // 等待进程执行完成
            int exitCode = process.waitFor();
            log.info(String.valueOf(exitCode));
            process = null;
            if (exitCode != 0) {
                return "";
            }
            final String outputPath = "/Users/yanjingsheng/IdeaProjects/spark/output/part-00000";
            return Files.readString(Path.of(outputPath));

        } catch (IOException | InterruptedException e) {
            log.error(e.getMessage());
        }
        return "";
    }
}
