package tr.com.huseyinaydin.services;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MenuService {
    public List<MenuItem> getMenuItems() {
        return List.of(
        	new MenuItem("Baba Sayfa", "/"),
            new MenuItem("Ana Sayfa", "/anasayfa"),
            new MenuItem("Hakkımda", "/hakkimda")
        );
    }
}

record MenuItem(String name, String url) {}