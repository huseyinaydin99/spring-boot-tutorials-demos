package tr.com.huseyinaydin.dto;

import java.util.List;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

public record UserProfile(
        String name,
        int age,
        String country,
        List<String> skills,
        Address address
) {
    public record Address(String street, String city, String zip) {}
}