package com.example.spring_scheduled;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import services.ProgramStartSchedule;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableScheduling
public class SpringScheduledApplication {

    private static final Path FILE_PATH = Path.of("data.txt");
    private int counter = 0;

    public static void main(String[] args) {
        SpringApplication.run(SpringScheduledApplication.class, args);
        ProgramStartSchedule.run();
    }


    @Scheduled(fixedRate = 1000)
    public void updateFile() {
        counter += 5;
        //System.out.println(counter);
        try {
            Files.writeString(FILE_PATH, "Counter: " + counter + System.lineSeparator(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Failed to write to file: " + e.getMessage());
        }
    }

}
