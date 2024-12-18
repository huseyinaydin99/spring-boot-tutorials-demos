package tr.com.huseyinaydin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@SpringBootApplication
public class JpaPerformanceApplication {

//    @Autowired
//    private EmployeeRepository repository;
//
//    @PostConstruct
//    public void initDB()  {
//        List<Employee> employees = IntStream.rangeClosed(3001, 100000)
//                .mapToObj(i -> new Employee("emp" + i, "dept" + i, "employee" + i + "@gmail.com", getGender(), generateSalary(50000, 100000))).collect(Collectors.toList());
//        repository.saveAll(employees);
//    }
//
//    private String getGender(){
//        return new Random().nextBoolean()?"Erkek":"Kadın";
//    }
//
//    public static double generateSalary(double min, double max) {
//        // Rastgele ondalıklı sayı üretimi 0.0 ila 1.0
//        Random random = new Random();
//        double randomValue = random.nextDouble();
//
//        return min + (randomValue * (max - min));
//    }

    public static void main(String[] args) {
        SpringApplication.run(JpaPerformanceApplication.class, args);
    }
}