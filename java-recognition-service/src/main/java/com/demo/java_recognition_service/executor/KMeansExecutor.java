package com.demo.java_recognition_service.executor;

import com.demo.devkit.JavaImagePerformanceResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class KMeansExecutor {

    public JavaImagePerformanceResultDTO execute(
            byte[] data,
            boolean erosion,
            boolean blurSelected) {

        Mat img = performPreProcessing(data, erosion, blurSelected);

        Mat clusters = cluster(img, 2);

        byte[] resultBytes = serializeMatToImageBytes(clusters);

        int percentage = countLoss(clusters, img);

        return new JavaImagePerformanceResultDTO(percentage, resultBytes);
    }

    public byte[] executeTest(
            byte[] data,
            boolean erosion,
            boolean blurSelected) {

        Mat img = performPreProcessing(data, erosion, blurSelected);

        Mat clusters = cluster(img, 2);

        return serializeMatToImageBytes(clusters);
    }

    private Mat performPreProcessing(byte[] data, boolean erosion, boolean blurSelected) {
        Mat img1 = Imgcodecs.imdecode(new MatOfByte(data), Imgcodecs.IMREAD_UNCHANGED);
        Mat img = new Mat();
        Imgproc.cvtColor(img1, img, Imgproc.COLOR_BGR2GRAY);
        if (erosion) {
            Imgproc.erode(img, img, Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3)));
        }
        if (blurSelected) {
            Imgproc.GaussianBlur(img, img, new Size(5, 5), 0);
        }
        return img;
    }

    private int countLoss(Mat clusters, Mat img) {
        int counter = 0;
        for (int i = 0; i < clusters.rows(); i++) {
            for (int j = 0; j < clusters.cols(); j++) {
                if (clusters.get(i, j)[0] == 0) {
                    counter++;
                }
            }
        }
        counter = (int) (((double) counter) / (img.rows() * img.cols()) * 100);
        counter = Math.min(counter, 100 - counter);
        return counter;
    }

    private Mat cluster(Mat cutout, int k) {
        Mat samples = cutout.reshape(1, cutout.cols() * cutout.rows());
        Mat samples32f = new Mat();
        samples.convertTo(samples32f, CvType.CV_32F, 1.0 / 255.0);

        Mat labels = new Mat();
        TermCriteria criteria = new TermCriteria(TermCriteria.COUNT, 100, 1);
        Mat centers = new Mat();
        Core.kmeans(samples32f, k, labels, criteria, 15, Core.KMEANS_PP_CENTERS, centers);
        return showClusters(cutout, labels, centers).get(0);
    }

    private List<Mat> showClusters(Mat cutout, Mat labels, Mat centers) {
        centers.convertTo(centers, CvType.CV_8UC1, 255.0);
        centers.reshape(2);

        List<Mat> clusters = new ArrayList<>();
        for (int i = 0; i < centers.rows(); i++) {
            clusters.add(Mat.zeros(cutout.size(), cutout.type()));
        }

        Map<Integer, Integer> counts = new HashMap<>();
        for (int i = 0; i < centers.rows(); i++) {
            counts.put(i, 0);
        }
        int rows = 0;
        for (int y = 0; y < cutout.rows(); y++) {
            for (int x = 0; x < cutout.cols(); x++) {
                int label = (int) labels.get(rows, 0)[0];
                counts.put(label, counts.get(label) + 1);
                clusters.get(label).put(y, x, 255);
                rows++;
            }
        }
        log.info("Finished clustering, Counts :: " + counts);
        return clusters;
    }

    private byte[] serializeMatToImageBytes(Mat img) {
        MatOfByte matOfByte = new MatOfByte();
        Imgcodecs.imencode(".jpeg", img, matOfByte);
        return matOfByte.toArray();
    }
}
