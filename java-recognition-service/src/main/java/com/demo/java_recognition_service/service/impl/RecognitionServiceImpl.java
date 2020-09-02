package com.demo.java_recognition_service.service.impl;

import com.demo.devkit.JavaImagePerformanceResultDTO;
import com.demo.java_recognition_service.executor.ImageProcessorExecutor;
import com.demo.java_recognition_service.service.RecognitionService;
import org.springframework.stereotype.Service;

@Service
public class RecognitionServiceImpl implements RecognitionService {

    private final ImageProcessorExecutor thresholdExecutor;

    private final ImageProcessorExecutor KMeansExecutor;

    public RecognitionServiceImpl(ImageProcessorExecutor thresholdExecutor, ImageProcessorExecutor KMeansExecutor) {
        this.thresholdExecutor = thresholdExecutor;
        this.KMeansExecutor = KMeansExecutor;
    }


    @Override
    public JavaImagePerformanceResultDTO processThreshold(byte[] data) {
        return thresholdExecutor.execute(data);
    }

    @Override
    public JavaImagePerformanceResultDTO processKMeans(byte[] data) {
        return KMeansExecutor.execute(data);
    }

    @Override
    public byte[] processThresholdTest(byte[] data) {
        return thresholdExecutor.executeTest(data);
    }

    @Override
    public byte[] processKMeansTest(byte[] data) {
        return KMeansExecutor.executeTest(data);
    }
}
