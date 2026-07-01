package com.lucasNeris.qrcode.generetor.ports;

public interface storagePort {
    String uploadFile(byte[] fileData, String fileName, String contentType);
}
