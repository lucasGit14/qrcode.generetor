package com.lucasNeris.qrcode.generetor.service;

import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.lucasNeris.qrcode.generetor.dto.QrcodeGenereteResponse;
import com.lucasNeris.qrcode.generetor.ports.storagePort;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class QrcodeGeneretorService {
    private final storagePort storagePort;

    public QrcodeGeneretorService(storagePort storagePort) {
        this.storagePort = storagePort;
    }

    public QrcodeGenereteResponse generateAndUploadQrcode(String text) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, com.google.zxing.BarcodeFormat.QR_CODE, 200, 200);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "png", pngOutputStream);
        byte[] pngQrcodeData = pngOutputStream.toByteArray();

        String url = storagePort.uploadFile(pngQrcodeData, UUID.randomUUID().toString(),  "imagem/png");

        return new QrcodeGenereteResponse(url);


    }
}