package com.demo.java_recognition_service.service.impl;

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
    public byte[] processThreshold(byte[] data) {
        return thresholdExecutor.execute(data);
    }

    @Override
    public byte[] processKMeans(byte[] data) {
        return KMeansExecutor.execute(data);
    }
}
