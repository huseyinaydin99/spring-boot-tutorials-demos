#### 📈 gRPC İstemciyi Yazdım, Sunucuya İstek Gönderdim! 🎉
İlk olarak, gRPC istemcisi yazdım. 📱🖥️ Yani, sunucumla iletişime geçebilmek için bir istemci servisi oluşturdum.

İstemcim, hisse senedi fiyatını almak için sunucuya istek gönderiyor. 🔍💰

"AAPL" gibi bir hisse senedi sembolü verdim ve gRPC istemcisi sunucudan hisse fiyatını ve zaman damgasını alıp geri döndürüyor. 📊🕒

Bu işlemi gRPC istemci servisi ile gerçekleştirdim. İstemci, sunucudan veri alırken StockTradingServiceBlockingStub sınıfını kullanıyor. 🚀

#### 📝 Kodlarımda Neler Yaptım? 💻
StockClientService sınıfını yazdım.

Burada, gRPC istemcisini tanımlayıp stockService ile bağlantı kurarak sunucuya istek gönderdim.

getStockPrice metodu ile sunucudan hisse senedi fiyatını aldım. 🏦

StockTradingClientApplication sınıfını oluşturdum.

Burada Spring Boot uygulaması başlatıyorum ve gRPC istemcisini çalıştırarak sonucu ekrana yazdırıyorum. 👨‍💻

Proto dosyasıyla iletişim şeklimi tanımladım.

Hisse senedi sembolünü alacak bir StockRequest mesajı gönderdim ve StockResponse mesajı ile fiyat ve zaman damgasını aldım. 💹

Sunucu ve istemci arasında UNARY RPC tipiyle tek yönlü bir istek/yanıt modeli kullandım. Bu, gRPC'nin en hızlı ve basit yöntemlerinden biri! 🔄

#### 🧹 Clean Install Yaptım, Proje Temizlendi ve Yeniden Derlendi! 🧼
Öncelikle Clean Install işlemini yaptım. Yani, projemi baştan aşağı temizledim ve bağımlılıkları yeniden yükledim.

Maven'e clean install komutunu verdim ki, proje içinde biriken gereksiz dosyalar gitsin ve her şey sıfırdan, tertemiz bir şekilde başlasın. ✨

Bu sayede, derleme hataları ya da eksik bağımlılık gibi problemler ortadan kalktı ve proje sıfırdan, sağlıklı bir şekilde kuruldu. 🔄

```bash
mvn clean install
```

Hani bazen bazı dosyalar eksik olur ya, importlar yapılmaz, işte o zaman "Mark Directory As > Resources Root" işini yapman gerekiyor. Bu, aslında şu demek:

target/generated-sources/protobuf diye bir klasör var ya, oraya protobuf dosyalarından ve gRPC stub sınıflarından oluşan şeyler konuluyor. Ama IntelliJ, bu klasörü otomatik olarak proje kaynağı gibi kabul etmiyor. Yani, oradaki dosyaları tanımıyor ve import hatası veriyor.

#### Peki Nöğrecez?
"Mark Directory As > Resources Root" dediğimizde, IntelliJ'ye diyoruz ki: "Burası da proje dosyası, buradaki her şey önemli, bunu dikkate al!" Ve IntelliJ o dosyaları tanımaya başlıyor. 🙌

#### Ne İşe Yarayacak?
Bu işlem sayesinde, protobuf'dan üretilen sınıflar ve gRPC stub'ları artık projene düzgün bir şekilde dahil oluyor.

Import hataları ortadan kalkıyor, çünkü artık IntelliJ bu dosyaları doğru şekilde tanıyor ve import edebiliyorsun.

#### Fotiler:

![1](https://github.com/user-attachments/assets/1b44b518-3168-49f7-8f0c-e4e8c77bdebc)
![2](https://github.com/user-attachments/assets/2272e78e-b884-4541-8ade-93b51f363268)
![3](https://github.com/user-attachments/assets/02ecdb04-99f5-4dcf-8b1c-85ba34eb1dde)
