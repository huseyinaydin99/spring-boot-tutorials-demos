package tr.com.huseyinaydin.repository;

import tr.com.huseyinaydin.entity.Employee;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import java.util.List;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE e.salary > :salary")
    @QueryHints({
            @QueryHint(name = "org.hibernate.readOnly", value = "true"),
            @QueryHint(name = "org.hibernate.fetchSize", value = "50"),
            @QueryHint(name = "org.hibernate.cacheable", value = "true"),
            @QueryHint(name = "jakarta.persistence.cache.retrieveMode", value = "USE"),
            @QueryHint(name = "jakarta.persistence.cache.storeMode", value = "USE"),
            @QueryHint(name = "jakarta.persistence.query.timeout", value = "2000")
    })
    List<Employee> findEmployeesWithSalaryGreaterThan(@Param("salary") Double salary);
}
/*
EmployeeRepository adında bir arayüz oluşturdum ve JpaRepository'yi genişlettim, böylece Employee nesneleri için CRUD işlemleri yapabileceğim bir yapı sağladım.

findEmployeesWithSalaryGreaterThan metodunu yazdım. Bu metodun, maaşı belirtilen değerden yüksek olan çalışanları veritabanından sorgulayan bir JPQL sorgusu çalıştırmasını sağladım.

Sorgunun performansını iyileştirmek amacıyla @QueryHints kullanarak, sorgunun sadece okuma amaçlı (org.hibernate.readOnly), belirli bir boyutta (org.hibernate.fetchSize) ve önbellekleme yaparak (org.hibernate.cacheable) çalışmasını sağladım.

jakarta.persistence.cache.retrieveMode ve jakarta.persistence.cache.storeMode parametreleriyle verilerin önbellekten alınmasını ve saklanmasını aktive ettim.

Son olarak, sorgunun zaman aşımı süresini 2000 milisaniye (2 saniye) olarak ayarladım. Böylece sorgu uzun süre çalışmaya devam ederse, belirttiğim süre sonunda zaman aşımına uğrayacak.
 */