# spring-boot-micrometer

### Prometheus
localhost:9090
Prometheus, açık kaynaklı bir izleme ve uyarı sistemidir. Özellikle mikro hizmet mimarilerinde, uygulama ve altyapı metriklerini toplar ve analiz eder. Verileri zaman serisi olarak saklar ve sorgulamak için güçlü bir dil olan PromQL kullanır. Prometheus, özellikle Kubernetes gibi ortamlarda popülerdir ve Grafana gibi araçlarla görselleştirilebilir.
go_memstats_heap_alloc_bytes:

Bu metrik, Go'nun heap (yığın) bellek kullanımını izler. Go çalışma zamanı, heap üzerinde tahsis edilen bellek miktarını izler ve Prometheus’a bu veriyi sağlar.
prometheus_sd_file_scan_duration_seconds_count:

Bu metrik, Prometheus servis keşfi (service discovery) ile ilgilidir ve Go diliyle doğrudan bir ilişkisi yoktur. Ancak Go uygulamanız bir servisi temsil ediyorsa, Prometheus bu metrikle servisi izler.
go_gc_duration_seconds_sum:

Bu metrik, Go'nun çöp toplama (garbage collection) işlemlerinin süresini izler. Go çalışma zamanı çöp toplama süresini toplar ve Prometheus’a iletir. Bu, bellek yönetiminin ne kadar sürdüğünü gösterir.
go_memstats_heap_sys_bytes:

Bu metrik, Go uygulamasının heap belleği için tahsis edilen toplam bellek miktarını gösterir. Go'nun bellek yönetimi bu metrikle izlenebilir.

Prometheus'taki bazı metrikler Go diline özgü, ancak Spring Boot ile kullanıyorsam, bu metriklerin çoğu Spring Boot Actuator ve Prometheus Exporter kullanılarak izlenir sıkıntı olmaz. Spring Boot uygulamamda Prometheus Exporter'ı kullandığımda, Prometheus, Spring Boot'un çalışma durumu, bellek kullanımı, HTTP istekleri ve diğer performans verilerini toplar sıkıntı olmaz.

Bazı metrik isimleri, özellikle Go ile ilişkili olanlar, Spring Boot Actuator ile görülebilir. Bu durum, özellikle Spring Boot'un arka planda Spring Boot Actuator ve Micrometer ile Prometheus'tan veri almasıyla ilgilidir.

### Grafana
localhost:3000
username: admin
password: admin
repassword: [istediğin bir şifre işte]
repassword: [istediğin bir şifre işte]

### Özet
Prometheus, Grafana ve Spring Boot'u Docker Compose ile aynı ağ üzerinde başarıyla entegre ettim ve veri izleme sürecimi kurdum. İlk olarak, Spring Boot uygulamamı Docker container içinde çalıştırmak için gerekli Dockerfile'ı ve docker-compose.yml dosyasını yapılandırdım. Spring Boot uygulamamın /actuator/prometheus endpoint'ini açarak Prometheus’un veri çekebilmesini sağladım. Prometheus servisini doğru bir şekilde Docker Compose ile ayağa kaldırdım ve konfigürasyon dosyasını, Spring Boot uygulamasını 5 saniyede bir izleyip verileri çekebilecek şekilde ayarladım.

Grafana'yı Docker container olarak kurarak, Prometheus veri kaynağını doğru şekilde yapılandırdım ve her şeyin düzgün çalışıp çalışmadığını kontrol ettim. Grafana’da Prometheus’a bağlanarak dashboardlarımı oluşturmak için gerekli URL'yi http://prometheus:9090 olarak belirledim. Son olarak, verilerin doğru şekilde alındığını ve dashboard’larımın düzgün çalıştığını görerek süreci başarıyla tamamladım. Monitoring yapılandırmamın her aşamasını dikkatle inşa ettim ve her servisin doğru şekilde birbirleriyle iletişim kurduğundan emin oldum.

Bu yapılandırma sayesinde Spring Boot uygulamamın performansını, metriklerini ve sağlığını her an takip edebilmek için kapsamlı bir sistem kurmuş oldum. Artık Prometheus'un topladığı metriklerle Grafana üzerinden görselleştirme yaparak gerçek zamanlı analizler yapabiliyorum.

### Docker Komutları
docker pull prom/prometheus
docker image ls
docker run -d --name=grafanareis -p 3000:3000 grafana/grafana
docker run -d -p 9090:9090 -v C:/prometheus.yml:/etc/prometheus/prometheus.yml --name prometheus prom/prometheus
docker build -t spring-boot-app . --Dockerfile ile projemizi dockerize eder.
docker run -d -p 8080:8080 spring-boot-app --Oluşan image'dan bir konteynır yaratarak o konteynırı çalıştırır.
docker-compose up --build --Prometheus, Grafana ve Spring Boot'un birbirleri ile iletişim kurabilmesi için aynı ağ üzerinde çalışması gerekir. Bunun için docker compose ile aynı sanal ağ üzerinde çalıştırdım.

### Görseller
![Ekran görüntüsü 2025-01-08 152357](https://github.com/user-attachments/assets/c3cb4935-2fbf-4a0e-b05d-57550a7a94e0)
![Ekran görüntüsü 2025-01-08 152053](https://github.com/user-attachments/assets/218808a8-9ff9-479f-bfd8-465c65e18d73)
![Ekran görüntüsü 2025-01-08 141509](https://github.com/user-attachments/assets/f3235b14-15bd-4fb1-a96c-55a0ab4319be)
![Ekran görüntüsü 2025-01-08 141453](https://github.com/user-attachments/assets/10f32095-1a5c-4503-ba19-679fd19382b4)
![Ekran görüntüsü 2025-01-07 153710](https://github.com/user-attachments/assets/2747c02f-0670-4a8d-9c81-edd22808bd6e)
![Ekran görüntüsü 2025-01-08 141436](https://github.com/user-attachments/assets/294a6f2d-27db-4893-af97-2ede79db14e3)
![Ekran görüntüsü 2025-01-08 141421](https://github.com/user-attachments/assets/d9d3c0f1-0788-4b9f-9a1a-ce3ebae2cc9c)
![Ekran görüntüsü 2025-01-08 141234](https://github.com/user-attachments/assets/e33b3967-89e5-4102-be0a-2f96e32bccd1)
![Ekran görüntüsü 2025-01-08 140908](https://github.com/user-attachments/assets/d1e1c85e-16ed-499a-9258-35f737f832cb)
![Ekran görüntüsü 2025-01-08 140607](https://github.com/user-attachments/assets/b1e01713-eace-437e-81e0-d878bfb924ae)
![Ekran görüntüsü 2025-01-07 153534](https://github.com/user-attachments/assets/7901091f-825c-4216-aebf-500a74447cf8)
![Ekran görüntüsü 2025-01-08 152608](https://github.com/user-attachments/assets/61d8512b-403b-4d70-96db-e644de61f60c)
