package tr.com.huseyinaydin.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import tr.com.huseyinaydin.CreateAccountRequest;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@Controller
public class PaytmQRCodeController {

	@Autowired
	private ResourceLoader resourceLoader;

	@PostMapping("/createAccount")
	public String createNewAccount(@ModelAttribute("request") CreateAccountRequest request, Model model) throws WriterException, IOException {
		String qrCodePath = writeQR(request);
		model.addAttribute("code", qrCodePath);
		return "QRcode";
	}

	@GetMapping("/readQR")
	public String verifyQR(@RequestParam("qrImage") String qrImage, Model model) throws Exception {
		model.addAttribute("content", readQR(qrImage));
		model.addAttribute("code", qrImage);
		return "QRcode";
	}

	@GetMapping("/home")
	public String home(){
		return "home";
	}

	private String writeQR(CreateAccountRequest request) throws WriterException, IOException {
		String qcodePath = "src/main/resources/static/images/" + request.getName() + "-qr-code.png";
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(request.getName() + "\n" + request.getEmail() + "\n"
				+ request.getMobile() + "\n" + request.getPassword() + "\n" + request.isRemember(), BarcodeFormat.QR_CODE, 350, 350);
		Path path = FileSystems.getDefault().getPath(qcodePath);
		MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
		return "/images/" + request.getName() + "-qr-code.png";
	}

	private String readQR(String qrImage) throws Exception {
		final Resource fileResource = resourceLoader.getResource("classpath:static" + qrImage);
		File QRfile = fileResource.getFile();
		BufferedImage bufferedImg = ImageIO.read(QRfile);
		LuminanceSource source = new BufferedImageLuminanceSource(bufferedImg);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		Result result = new MultiFormatReader().decode(bitmap);
		System.out.println("Barkod Formatı: " + result.getBarcodeFormat());
		System.out.println("İçerik: " + result.getText());
		return result.getText();
	}
}