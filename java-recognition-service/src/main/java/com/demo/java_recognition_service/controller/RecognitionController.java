package com.demo.java_recognition_service.controller;

import com.demo.devkit.JavaImagePerformanceResultDTO;
import com.demo.java_recognition_service.exception.CouldNotParseFileException;
import com.demo.java_recognition_service.service.RecognitionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@Slf4j
public class RecognitionController {

    private final RecognitionService recognitionService;

    public RecognitionController(RecognitionService recognitionService) {
        this.recognitionService = recognitionService;
    }

    @RequestMapping(method = RequestMethod.POST, headers = "Content-Type= multipart/form-data", path = "/perform-thresholding")
    public ResponseEntity<JavaImagePerformanceResultDTO> performThresholding(@RequestParam(value = "file") @Valid MultipartFile file) throws CouldNotParseFileException {

        log.info("Received file: \n" +
                "filename=\"" + file.getName() + "\"\n" +
                "contentType=\"" + file.getContentType() + "\"\n" +
                "size=\"" + file.getSize() + "\"\n");

        try {
            return new ResponseEntity<>(recognitionService.processThreshold(file.getBytes()), HttpStatus.OK);
        } catch (IOException ex) {
            throw new CouldNotParseFileException("Could not encode/decode file", ex);
        }
    }

    @RequestMapping(method = RequestMethod.POST, headers = "Content-Type= multipart/form-data", path = "/perform-kmeans")
    public ResponseEntity<JavaImagePerformanceResultDTO> performKMeans(@RequestParam(value = "file") @Valid MultipartFile file) throws CouldNotParseFileException {

        log.info("Received file: \n" +
                "filename=\"" + file.getName() + "\"\n" +
                "contentType=\"" + file.getContentType() + "\"\n" +
                "size=\"" + file.getSize() + "\"\n");

        try {
            return new ResponseEntity<>(recognitionService.processKMeans(file.getBytes()), HttpStatus.OK);
        } catch (IOException ex) {
            throw new CouldNotParseFileException("Could not encode/decode file", ex);
        }
    }
}
