package tr.com.huseyinaydin;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@Service
public class QRCodeGeneratorService {

    public byte [] generateQRCode(String value, int width, int height) throws IOException, WriterException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(value, BarcodeFormat.QR_CODE, width, height);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(0xFF000002, 0xFFFFC041);

        MatrixToImageWriter.writeToStream(bitMatrix, "png", byteArrayOutputStream, matrixToImageConfig);
        return byteArrayOutputStream.toByteArray();
    }

}
