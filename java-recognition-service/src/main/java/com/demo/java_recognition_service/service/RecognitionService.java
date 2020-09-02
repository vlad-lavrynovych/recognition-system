package com.demo.java_recognition_service.service;

import com.demo.devkit.JavaImagePerformanceResultDTO;

public interface RecognitionService {

    JavaImagePerformanceResultDTO processThreshold(byte[] data,
                                                   boolean erosion,
                                                   boolean blurSelected,
                                                   int value);

    JavaImagePerformanceResultDTO processKMeans(byte[] data,
                                                boolean erosion,
                                                boolean blurSelected);

    byte[] processThresholdTest(byte[] data,
                                boolean erosion,
                                boolean blurSelected,
                                int value);

    byte[] processKMeansTest(byte[] data,
                             boolean erosion,
                             boolean blurSelected);
}
