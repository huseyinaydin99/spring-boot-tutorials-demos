# Veritabanı Kilitlenmeleri

Bu proje, film koltuklarını rezerve etmeye odaklanarak İyimser Kilitleme (Optimistic Locking) ve Kötümser Kilitleme (Pessimistic Locking) olmak üzere iki tür veritabanı kilitleme mekanizmasını göstermektedir.
## Seat(koltuk) tablosu kayıt girme.

```sql
INSERT INTO seat (movieName, booked, version) VALUES 
('Tatar Ramazan', false, 0),
('Köyden İndim Şehre', false, 0),
('Destere', false, 0),
('Battal Gazi', false, 0),
('Kutsal Damacana Dracoola', false, 0);
```

---

## İyimser Doğrulama

### cURL Komutu

```bash
curl -X 'GET' 'http://localhost:9191/booking/optimistic/2' -H 'accept: */*'
```

### Adım Adım Açıklamalar:

İyimser Doğrulama (Optimistic Locking), veritabanı yönetim sistemlerinde verilerin çakışmadan güncellenmesini sağlamak için kullanılan bir yöntemdir. Bu yöntemde, birden fazla iş parçacığı (thread) aynı veriyi aynı anda okuyabilir ve güncelleyebilir. Ancak, veri güncellenmeden önce sistem, verinin güncellenmiş olup olmadığını kontrol eder. Eğer bir çakışma varsa, işlemi reddeder ve bir hata fırlatır.

Bir gün, film izlemek isteyen bir grup insan koltuklarını rezerve etmek için sisteme giriş yapar. Bu proje, film koltuklarını rezerve etmeye yönelik iki farklı veritabanı kilitleme mekanizmasını test ediyor: İyimser Kilitleme (Optimistic Locking) ve Kötümser Kilitleme (Pessimistic Locking).

İyimser Kilitleme (Optimistic Locking)
İlk olarak, Thread-1 (iş parçacığı 1) bir koltuğu rezerve etmek için veritabanına bağlanır ve koltuğun mevcut sürümünü (version: 0) okur.

Aynı zamanda, Thread-2 (iş parçacığı 2) da aynı koltuğu rezerve etmek için veritabanına bağlanır ve aynı sürüm numarasını (version: 0) okur.

İyimser Kilitleme burada devreye girer; çünkü iki iş parçacığı aynı koltuğu okur, ancak her ikisi de verinin güncellenmiş olup olmadığını kontrol etmeden işlem yapmaya devam eder.

Thread-1, veritabanında koltuğu rezerve etmek üzere işlem yapmaya başlar. İşlem sırasında veritabanındaki sürüm numarasını kontrol eder ve sürümün version: 0 olduğunu görür. Bu, Thread-1'in yaptığı işlemin doğru olduğunu gösterir. İşlem başarılı olur ve koltuğun sürümü version: 1 olarak güncellenir.

Ancak, Thread-2 işlemini Thread-1'in işlemi bittikten sonra yapmaya çalışır. Bu durumda veritabanı, Thread-2'nin okuduğu sürüm numarasının (version: 0) veritabanındaki güncel sürümle (version: 1) uyuşmadığını fark eder. Yani, Thread-2’nin işlem yapmaya çalıştığı koltuk aslında çoktan rezerve edilmiştir.

Bunun sonucunda, Thread-2 bir hata mesajı alır ve işlem başarısız olur. Yani, İyimser Kilitleme sayesinde, çakışan işlemler engellenmiş olur. Thread-2, bir başka işlem tarafından veritabanı üzerinde değişiklik yapılmış olduğu için başarılı olamaz.

---

## Kötümser Kilitleme (Pessimistic Locking) Çalışması: Koltuk Rezervasyonu Örneği:

### cURL Komutu:

```bash
curl -X 'GET' 'http://localhost:9191/booking/optimistic/2' -H 'accept: */*'
```

### Senaryo:

İki iş parçacığı (Thread-1 ve Thread-2), aynı koltuğu (ID 2) Kötümser Kilitleme (Pessimistic Locking) kullanarak rezerve etmeye çalışır. Bu kilitleme mekanizması, yalnızca bir iş parçacığının aynı anda koltuğa erişip değiştirmesine izin verir.
---
Bir gün Thread-1 ve Thread-2 isimli iki iş parçacığı, aynı koltuğu rezerve etmeye karar verir. Bu koltuk, ID'si 2 olan bir koltuktur. Şimdi, bu iki iş parçacığının aynı anda aynı koltuğu rezerve etmeye çalışması, bazı sorunlara yol açabilir, değil mi? İşte burada devreye Kötümser Kilitleme (Pessimistic Locking) giriyor.

Thread-1 ilk olarak bu koltuğu rezerve etmeye başlar. Koltuğu alır ama işlemi bitirmeden önce başka bir iş parçacığının aynı koltuğu rezerve etmesini engellemek için kilitler. Yani, bu koltuğa sadece Thread-1 erişebilir. Diğer iş parçacıkları bu noktada beklemek zorundadır.

Thread-2 de aynı koltuğu almak ister ama Thread-1 henüz işlemi bitirmediği için Thread-2 bekler. Kötümser Kilitleme, sadece bir iş parçacığının koltuğa erişmesine izin verdiği için, Thread-2 bu koltuğu alıp güncelleme yapamaz.

Thread-1, işlemi tamamladıktan sonra, koltuğu başarıyla rezerve eder ve kilit serbest bırakılır. Artık Thread-2 koltuğu rezerve edebilir. Ancak Thread-2’nin beklemesi gerekeceği için zaman kaybı olabilir.

Özetle, Kötümser Kilitleme sayesinde iki iş parçacığı aynı anda aynı koltuğu rezerve edemez. Bir iş parçacığı işlemi bitirmeden diğerine izin verilmez, böylece sistemin tutarlılığı korunur.

---

### Önemli Noktalar:

#### Özel Erişim:
Kötümser Kilitleme, sadece bir iş parçacığının koltuğu aynı anda değiştirmesine izin vererek, işlemler sırasında veritabanında kilitleme yapar.

#### Sıralı İşlem:
Aynı varlığa erişmeye çalışan iş parçacıkları, birbiri ardına işlenir, böylece çatışmalar engellenir ve veri tutarlılığı sağlanır.

#### Hata Yönetimi:
Bir iş parçacığı, varlık geçersiz bir durumda (örneğin, zaten rezerve edilmişse) bulursa, işlemin devam edemeyeceğini belirten bir istisna fırlatır.
---