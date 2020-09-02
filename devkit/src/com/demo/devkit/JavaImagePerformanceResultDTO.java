package com.demo.devkit;

import lombok.Data;

@Data
public class JavaImagePerformanceResultDTO {
    private long id;
    private int percentage;
    private byte[] data;

    public JavaImagePerformanceResultDTO(int percentage, byte[] data) {
        this.percentage = percentage;
        this.data = data;
    }
}
