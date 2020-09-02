package com.demo.java_recognition_service.service;

import com.demo.devkit.JavaImagePerformanceResultDTO;

public interface RecognitionService {

    public JavaImagePerformanceResultDTO processThreshold(byte[] data);

    public JavaImagePerformanceResultDTO processKMeans(byte[] data);
}
