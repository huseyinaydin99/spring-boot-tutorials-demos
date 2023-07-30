package tr.com.huseyinaydin.mongodb.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;

//بسم الله الرحمن الرحيم

/**
* 
* @author Huseyin_Aydin
* @since 1994
* @category Java, Spring Boot.
* 
*/

@Document
public class Kullanici {
    @Id
    private String id;
    private String adi;
    private String soyadi;
    private HashMap ozellikleri;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getSoyadi() {
        return soyadi;
    }

    public void setSoyadi(String soyadi) {
        this.soyadi = soyadi;
    }

    public HashMap getOzellikleri() {
        return ozellikleri;
    }

    public void setOzellikleri(HashMap ozellikleri) {
        this.ozellikleri = ozellikleri;
    }
}
