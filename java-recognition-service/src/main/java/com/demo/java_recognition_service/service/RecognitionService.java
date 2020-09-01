package com.demo.java_recognition_service.service;

public interface RecognitionService {

    public byte[] processThreshold(byte[] data);

    public byte[] processKMeans(byte[] data);
}
