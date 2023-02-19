package tr.com.huseyinaydin.exepction;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@ControllerAdvice
public class UploadException {

    @ExceptionHandler(MultipartException.class)
    public String handleError1(MultipartException e, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("message", e.getCause().getMessage());
        return "redirect:/uploadStatus";
    }

    /*@ExceptionHandler(MultipartException.class)
    public String handleError2(MultipartException e) {
        return "redirect:/errorPage";
    }*/
}