# spring-boot-shedlock

### 1. Adım: Bağımlılıkların Eklenmesi:
pom.xml dosyamıza bağımlılıkları ekledik:
```
<!-- ShedLock Core -->
<dependency>
    <groupId>net.javacrumbs.shedlock</groupId>
    <artifactId>shedlock-spring</artifactId>
    <version>5.8.0</version>
</dependency>

<!-- ShedLock for JDBC (PostgreSQL, MySQL, vesaire.) -->
<dependency>
    <groupId>net.javacrumbs.shedlock</groupId>
    <artifactId>shedlock-provider-jdbc-template</artifactId>
    <version>5.8.0</version>
</dependency>

<!-- Spring Boot JDBC Starter (Önceden dahil edilmemişse) -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>
```

### 2. Adım: ShedLock Tablosu:
ShedLock'un kilit kayıtlarını depolamak için bir veritabanına ihtiyacı olduğundan, aşağıdaki tabloyu oluşturalım:
```
CREATE TABLE shedlock (
    name VARCHAR(64) PRIMARY KEY,
    lock_until TIMESTAMP NOT NULL,
    locked_at TIMESTAMP NOT NULL DEFAULT NOW(),
    locked_by VARCHAR(255) NOT NULL
);
```
### Komut Satırı Kullanarak Birden Fazla Örnek Çalıştıralım:
Use the -Dspring-boot.run.arguments her örnek/instance için farklı yapılandırmalar uygulayalım.

```
mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8081"
```
```
mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8082"
```
