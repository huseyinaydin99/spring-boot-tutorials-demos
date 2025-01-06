# spring-boot-stored-procedure-call

Bilet verilerini almak için stored procedure'lar kullandım. İki farklı prosedür var: biri tüm biletleri, diğeri ise belirli bir kategorideki biletleri getiriyor. Spring Data JPA ile entegre ederek, prosedürlerden sonuçları kolayca çekebiliyorum. RestController ile de kullanıcıların bu verilere API üzerinden ulaşmasını sağladım.

Bu prosedür, tüm bilet verilerini döndürür.
DELIMITER $$

CREATE PROCEDURE getTickets()
BEGIN
    SELECT id, amount, catagory 
    FROM Ticket;
END$$

DELIMITER ;

Bu prosedür, verilen bir kategoriye göre bilet verilerini döndürür.
DELIMITER $$

CREATE PROCEDURE getTicketsByCatagory(IN tcatagory VARCHAR(255))
BEGIN
    SELECT id, amount, catagory 
    FROM Ticket 
    WHERE catagory = tcatagory;
END$$

DELIMITER ;

DELIMITER $$ komutu, MySQL Workbench veya CLI'da birden fazla satırlı prosedür tanımlarken kullanılır. İşlem tamamlandığında DELIMITER ; ile eski duruma döndürülür.
IN tcatagory parametresi, prosedüre dışarıdan bir kategori değeri göndermek için kullanılır.
Bu prosedürler, Spring uygulamasındaki Hibernate üzerinden entegre edilerek sorunsuz bir şekilde kullanılabilir.

getTickets Procedure Çıktısı:

CALL getTickets();
+----+--------+------------+
| id | amount | catagory   |
+----+--------+------------+
| 1  | 500    | Music      |
| 2  | 300    | Sports     |
| 3  | 200    | Cinema     |
+----+--------+------------+

getTicketsByCatagory Procedure Çıktısı:
CALL getTicketsByCatagory('Music');
+----+--------+------------+
| id | amount | catagory   |
+----+--------+------------+
| 1  | 500    | Music      |
+----+--------+------------+

Ticket verilerini almak için getTickets ve kategoriye göre verileri çekmek için getTicketsByCatagory prosedürleri kullanıldı. @RestController ile API endpointleri tanımlandı. Stored procedure'lar için @NamedStoredProcedureQuery anotasyonu ile Ticket entitesinde gerekli yapılandırma yapıldı. Hibernate ve MySQL entegrasyonu başarıyla sağlandı.
