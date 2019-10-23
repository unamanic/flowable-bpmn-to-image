package com.example.flowablebpmntoimage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.flowablebpmntoimage.service.ModelParserService;

@SpringBootApplication
public class FlowableBpmnToImageApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowableBpmnToImageApplication.class, args);
    }
}
