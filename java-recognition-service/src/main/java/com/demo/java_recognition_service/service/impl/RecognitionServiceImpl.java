package com.demo.java_recognition_service.service.impl;

import com.demo.devkit.JavaImagePerformanceResultDTO;
import com.demo.java_recognition_service.executor.KMeansExecutor;
import com.demo.java_recognition_service.executor.ThresholdExecutor;
import com.demo.java_recognition_service.service.RecognitionService;
import org.springframework.stereotype.Service;

@Service
public class RecognitionServiceImpl implements RecognitionService {

    private final ThresholdExecutor thresholdExecutor;

    private final KMeansExecutor kMeansExecutor;

    public RecognitionServiceImpl(ThresholdExecutor thresholdExecutor, KMeansExecutor kMeansExecutor) {
        this.thresholdExecutor = thresholdExecutor;
        this.kMeansExecutor = kMeansExecutor;
    }


    @Override
    public JavaImagePerformanceResultDTO processThreshold(byte[] data,
                                                          boolean erosion,
                                                          boolean blurSelected,
                                                          int value) {
        return thresholdExecutor.execute(data, erosion, blurSelected, value);
    }

    @Override
    public JavaImagePerformanceResultDTO processKMeans(byte[] data,
                                                       boolean erosion,
                                                       boolean blurSelected) {
        return kMeansExecutor.execute(data, erosion, blurSelected);
    }

    @Override
    public byte[] processThresholdTest(byte[] data,
                                       boolean erosion,
                                       boolean blurSelected,
                                       int value) {
        return thresholdExecutor.executeTest(data, erosion, blurSelected, value);
    }

    @Override
    public byte[] processKMeansTest(byte[] data,
                                    boolean erosion,
                                    boolean blurSelected) {
        return kMeansExecutor.executeTest(data, erosion, blurSelected);
    }
}
