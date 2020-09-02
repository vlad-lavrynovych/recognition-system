package com.demo.java_recognition_service.service;

import com.demo.devkit.JavaImagePerformanceResultDTO;

public interface RecognitionService {

    JavaImagePerformanceResultDTO processThreshold(byte[] data);

    JavaImagePerformanceResultDTO processKMeans(byte[] data);

    byte[] processThresholdTest(byte[] data);

    byte[] processKMeansTest(byte[] data);
}
