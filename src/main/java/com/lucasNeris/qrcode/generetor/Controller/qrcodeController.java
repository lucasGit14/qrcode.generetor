package com.lucasNeris.qrcode.generetor.Controller;

import com.lucasNeris.qrcode.generetor.service.QrcodeGeneretorService;
import com.lucasNeris.qrcode.generetor.dto.QrcodeGenerateRequest;
import com.lucasNeris.qrcode.generetor.dto.QrcodeGenereteResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/qrcode")
public class qrcodeController {

    private final QrcodeGeneretorService qrCodeGeneratorService;

    public qrcodeController(QrcodeGeneretorService qrCodeGeneratorService) {
        this.qrCodeGeneratorService = qrCodeGeneratorService;
    }

    @PostMapping
    public ResponseEntity<QrcodeGenereteResponse> generate(@RequestBody QrcodeGenerateRequest request){
        try {
            QrcodeGenereteResponse response = this.qrCodeGeneratorService.generateAndUploadQrcode(request.text());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
