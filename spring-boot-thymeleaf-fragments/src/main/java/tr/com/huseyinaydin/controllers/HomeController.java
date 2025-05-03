package tr.com.huseyinaydin.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import tr.com.huseyinaydin.dtos.HeaderData;
import tr.com.huseyinaydin.services.MenuService;

@Controller
public class HomeController {
	
	@Autowired 
	private MenuService menuService;
    
	@GetMapping("/")
    public String index(Model model) {
        // Header verisi
        model.addAttribute("headerData", 
            new HeaderData("My App", "Welcome User!"));

        // Menu verisi
        model.addAttribute("menuItems", menuService.getMenuItems());

        return "index";
    }
	
    @GetMapping("/anasayfa")
    public String anasayfa() {
        return "anasayfa"; // anasayfa.html şablonunu kullandım
    }
    
    @GetMapping("/hakkimda")
    public String hakkimda() {
        return "hakkimda"; // hakkimda.html şablonunu kullandım
    }
}