package com.demo.java_recognition_service.executor.impl;

import com.demo.java_recognition_service.executor.ImageProcessorExecutor;
import org.springframework.stereotype.Component;

@Component
public class KMeansExecutor implements ImageProcessorExecutor {

    @Override
    public byte[] execute(byte[] data) {
        return data;
    }
}
