package com.demo.java_recognition_service.executor;

import com.demo.devkit.JavaImagePerformanceResultDTO;

public interface ImageProcessorExecutor {

    JavaImagePerformanceResultDTO execute(byte[] data);

    byte[] executeTest(byte[] data);
}
