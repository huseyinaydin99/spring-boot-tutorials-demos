package tr.com.huseyinaydin.controller;

import tr.com.huseyinaydin.dto.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
public class CompressionController {

    @GetMapping("/employees")
    public List<Employee> getLargeResponse() {
        var random = new Random();
        List<String> genders = Arrays.asList("Erkek", "Kadın");
        List<String> departments = Arrays.asList("İK", "Finans", "Mühendislik", "Satış", "Pazarlama(sahtekarlık, şerefsizlik)");
        List<String> skills = Arrays.asList("Java", "Spring Boot", "SQL", "Kafka", "MongoDB");

        return IntStream.rangeClosed(1, 100000)
                .mapToObj(i -> new Employee(
                        i,
                        "çalışan " + i,
                        String.valueOf(new Random().nextLong(1000000000L)),
                        "adres " + i,
                        genders.get(random.nextInt(genders.size())),
                        departments.get(random.nextInt(departments.size())),
                        skills.get(random.nextInt(skills.size()))
                ))
                .collect(Collectors.toList());
    }
}