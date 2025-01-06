# spring-boot-windows-service

Bu projede uygulama ayağa kalktığında, kullanıcıya bir "status" endpoint'i ile durum bilgisini döndürdüm. @Scheduled anotasyonu ile her 5 saniyede bir çalışan bir görev yazdım, bu görev sistemin o anki tarih ve saatini konsola yazıyor. @EnableScheduling ile zamanlama mekanizmasını aktif ettim, böylece görevler otomatik olarak çalışıyor. @SpringBootApplication ile uygulamayı başlatıp her şeyin düzgün çalışmasını sağladım.

### XML Ayar Dosyası Windows Servisi İçin
<service>
  <id>spring-boot-tr.com.huseyinaydin</id>
  <name>spring-boot-tr.com.huseyinaydin</name>
  <description>Bu servis Hüseyin AYDIN abiniz tarafından oluşturuldu tatlım :-D</description>
  <executable>java</executable>
  <arguments>-jar winservice-application-up.jar</arguments>
</service>

### CMD Yönetici Hakları İle:
winsw-2.3.0-net4.exe install