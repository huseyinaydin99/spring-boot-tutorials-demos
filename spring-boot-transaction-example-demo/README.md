# spring-transaction-demo
# Transaction / Transaksiyon Nedir?
# Türkçe:&nbsp;
##### SQL'de DML diye bir kavram var.&nbsp;
##### DML(Data Manupulation Language[Veri Manipülasyonu Dili]) demektir.&nbsp;
##### Transaction DML(Insert, Update, Delete) komutlarının sonuçları birer transaction'dır.&nbsp;
##### Transaction veritabanında yapılan her bir değişikliğe / işleme verilen addır. &nbsp;
##### Veritabanında değişiklik yapılabilmesi için DML(Insert, Update, Delete) komutlarının çalıştırılması lazımdır.!&nbsp;
##### Transaction kelimesinin Türkçe manası işlem yahut hareket demektir.&nbsp;
##### TCL(Transaction Control Language[Hareket / İşlem Kontrol Dili]) 3 SQL komutundan oluşur.&nbsp;
##### 1. - COMMIT = Veritabanında DML(Insert, Update, Delete) komutlarının sonuçlarını aktarır.&nbsp;
##### 2. - ROLLBACK = Veritabanında DML(Insert, Update, Delete) komutları olduğu gibi geri alır.&nbsp;
##### 3. - SAVEPOINT = Yapılan kayıt noktasına geri döner.&nbsp;

### Spring ise Transaction işlemlerini otomatik yönetir. sen arkana yaslanabilirsin.&nbsp;
### Spring'de Transaction işlemlerini kullanmak için bu projeyi incelemen gerekiyor.
