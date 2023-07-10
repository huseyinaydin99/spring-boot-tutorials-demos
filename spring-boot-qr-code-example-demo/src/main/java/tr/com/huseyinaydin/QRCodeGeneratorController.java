package tr.com.huseyinaydin;

import com.google.zxing.WriterException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.Base64;

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot Examples
 *
 **/

@Controller
public class QRCodeGeneratorController {

	private final QRCodeGeneratorService qrCodeGeneratorService;

	public QRCodeGeneratorController(QRCodeGeneratorService qrCodeGeneratorService) {
		this.qrCodeGeneratorService = qrCodeGeneratorService;
	}

	@GetMapping("/qrcode/{value}")
	public String generateQRCode(Model model, @PathVariable String value) {
		byte[] qrImg = new byte[0];
		try {
			qrImg = qrCodeGeneratorService.generateQRCode(value, 250, 250);
		} catch (WriterException | IOException e) {
			e.getLocalizedMessage();
			e.printStackTrace();
		}
		var qrCode = Base64.getEncoder().encodeToString(qrImg);
		model.addAttribute("qrcode", qrCode);
		return "qrcode";
	}
}
