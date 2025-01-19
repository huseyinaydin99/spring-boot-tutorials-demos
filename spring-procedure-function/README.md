# spring-boot-procedure-function

update_stock:
--------------
```
CREATE PROCEDURE huseyinaydin.update_stock(
	IN productId INT,
    IN quantity INT
    )
BEGIN
	UPDATE products
    SET stockQuantity = stockQuantity - quantity
    WHERE id = productId;
END
```
get_total_price:
----------------
```
CREATE FUNCTION huseyinaydin.get_total_price(productId INT)
RETURNS DECIMAL(10, 2)
DETERMINISTIC
READS SQL DATA
BEGIN
    DECLARE total DECIMAL(10, 2);
    SELECT SUM(price * stockQuantity) INTO total
    FROM products
    WHERE id = productId;

    RETURN total;
END
```
### Procedur'leri ve Function'ları Neden Kullanmalıyız Hacım?

Complex Logic(Karmaşık Mantık): Çoklu birleştirme veya koşullu mantık gibi karmaşık SQL işlemleriniz varsa, saklı prosedürler kodunuzu düzenlemenize yardımcı olabilir.

Performance(Performans): Stored procedure'ler çok hızlıdır çünkü önceden derlenip optimize edilip hazır hale getirildikten sonra EXECUTE komutu ile direkt çalıştırılacağından dolayı çatur çutur çalışıverir. Gereksiz yere derleme, optimize vs. işlemleri yapılmayacağı için gereksiz üş yükü olmaz. Gereksiz iş yükü olmayan adamın performansı daha iyidir anlatabildim mi?

Security(Güvenlik): Bir depolanan prosedürün çalıştırılmasına izin verebilirsiniz, ancak alt tablolara doğrudan erişime izin vermezsiniz.

Reusability(Yeniden Kullanılabilirlik): Eğer birçok uygulama veya kodunuzun parçaları aynı SQL mantığını gerektiriyorsa, bunu bir prosedür veya fonksiyon olarak saklayabilir ve istediğiniz zaman çağırabilirsiniz. Böylelikle modüler bir programlama mantığı oluşur kod tekrarı önlenir ve hiyerarşik bir kod yapısı oluşur.