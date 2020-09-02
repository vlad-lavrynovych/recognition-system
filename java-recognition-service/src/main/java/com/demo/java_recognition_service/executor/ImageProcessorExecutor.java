package com.demo.java_recognition_service.executor;

import com.demo.devkit.JavaImagePerformanceResultDTO;

public interface ImageProcessorExecutor {

    public JavaImagePerformanceResultDTO execute(byte[] data);
}
