package io.spiral.express.app.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileOutputStream;

public class GenerationQrCode {
    private static final Logger log = LoggerFactory.getLogger(GenerationQrCode.class);

    public static void genererQrCode(String data, String outputFileName) {
        log.info("Génération du QR Code, avec comme référence: {}, dans le fichier: {}", data, outputFileName);

        final int SIZE = 200;
        final String IMAGE_FORMAT = "png";

        // encode
        BitMatrix bitMatrix = generateMatrix(data, SIZE);

        outputFileName = new StringBuilder().append(outputFileName).append(".").append(IMAGE_FORMAT).toString();

        // write in a file
        writeImage(outputFileName, IMAGE_FORMAT, bitMatrix);
    }

    private static BitMatrix generateMatrix(String data, int size) {

        BitMatrix bitMatrix = null;
        try {
            bitMatrix = new QRCodeWriter().encode(data, BarcodeFormat.QR_CODE, size, size);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return bitMatrix;
    }

    private static void writeImage(String outputFileName, String imageFormat, BitMatrix bitMatrix)  {
     try {
         FileOutputStream fileOutputStream = new FileOutputStream(new File(outputFileName));
         MatrixToImageWriter.writeToStream(bitMatrix, imageFormat, fileOutputStream);
         fileOutputStream.close();
     } catch (Exception e) {
         System.out.println("ERROR: writeImage" );
         e.printStackTrace();
     }
    }
}
