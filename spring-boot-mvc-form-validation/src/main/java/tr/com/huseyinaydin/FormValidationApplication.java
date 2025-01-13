package tr.com.huseyinaydin;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

@SpringBootApplication
@Controller
public class FormValidationApplication {

	@GetMapping("/")
	public String showForm(Person person) {
		return "register";
	}

	@PostMapping("/")
	public String register(@Valid Person person, Errors errors, Model model) {
		if (errors.hasErrors()) {
			return "register";
		} else {
			model.addAttribute("message", "Kayıt işlemi başarılı...");
			return "register";
		}

	}

	public static void main(String[] args) {
		SpringApplication.run(FormValidationApplication.class, args);
	}
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Person {

	@NotNull
	@Size(min = 2, max = 10, message = "İsim alanı 2 ila 10 karakter aralığında olmalıdır!")
	private String name;

	@NotEmpty(message = "E-posta adresi alanı boş geçilemez.")
	@Email(regexp = "^(.+)@(.+)$", message = "Geçersiz e-posta adresi formatı. Adam gibi gir!")
	private String email;

	@Pattern(regexp = "[7-9][0-9]{9}", message = "Geçersiz telefon numarası formatı. Adam gibi gir gireceksen adamı dellendirme!")
	@Size(max = 10, message = "Telefon numarası sabit 10 karakter olabilir. Ne az olacak ne fazla!")
	private String mobile;
}