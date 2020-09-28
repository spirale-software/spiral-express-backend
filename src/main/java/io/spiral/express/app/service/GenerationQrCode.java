package io.spiral.express.app.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileOutputStream;

public class GenerationQrCode {

    public static void genererQrCode(String data) {
        int size = 200;

        // encode
        BitMatrix bitMatrix = generateMatrix(data, size);

        String imageFormat = "png";
        String outputFileName = "C:\\Users\\MediaMonster\\Desktop\\Projets\\spiral-express-backend\\src\\main\\resources\\qrcode\\qrcode-01." + imageFormat;

        // write in a file
        writeImage(outputFileName, imageFormat, bitMatrix);
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
