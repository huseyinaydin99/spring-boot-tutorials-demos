#### Merhabalar...
Gardaş, bak şimdi, geçenlerde oturdum, bi' gRPC tabanlı bir borsa takip sistemi yazdım, ama öyle basit bi’ şey değil, bildiğin taş gibi bi’ proje oldu aceyip! :D 🚀💰 Hadi sana baştan sona bi’ anlatayım, hem gRPC’yi de güzelce bi’ kavrayalım.

#### Önce gRPC Nedir? Ne İşe Yarar?
Şimdi, gRPC dediğimiz şey aslında Google’un geliştirdiği ve sistemler arası hızlı, verimli iletişim sağlayan bi’ framework. 📡 Normalde sistemler birbirleriyle REST API’ler kullanarak konuşur ya, hani şu HTTP bazlı istekler var ya, işte onun yerine gRPC ile daha hızlı, daha az yükle, daha sağlam bir iletişim sağlıyoruz.

gRPC’nin mantığı Remote Procedure Call (RPC) yani Uzaktan Prosedür Çağrısı yapmak. Yani, kendi kodumuzda bir fonksiyon çağırır gibi başka bir servisteki metodu çağırıyoruz ve hop diye sonuç geliyor! 🚀🔥

#### gRPC'nin Avantajları Neymiş Gardaş?
#### Hızlı ⏩
REST’ten daha hızlıdır çünkü veriyi JSON yerine Protobuf ile iletiyor. JSON büyük ve şişkin bi’ yapı, Protobuf ise daha sıkıştırılmış ve hızlı bir format. Veri dönüşümlerinde JSON'a göre PROTOBUF çok performanslı.

#### İkili (Binary) Protokol 📦
gRPC, HTTP 1.1 yerine HTTP/2 kullanıyor, yani çoklu istekler aynı anda gidip geliyor, bağlantı paylaşımlı oluyor. Bunun anlamı? Daha hızlı, daha verimli! 🚀

#### Streaming Destekliyor 🔄
Normalde REST’te istek atarsın, cevap gelir biter. Ama gRPC’de sunucu akış (server streaming) ve çift yönlü akış (bidirectional streaming) desteği var, yani gerçek zamanlı veri akışı alabiliyorsun!

#### Daha Az Bant Genişliği Kullanır 🏗️
JSON gibi şişkin veri formatları yerine sıkıştırılmış Protobuf kullanıyoruz, böylece daha az veri tüketiyor, interneti yormuyor!

#### Diller Arası Çalışabilir 🏳️‍🌈
Java, Python, Go, C# ne istersen çalıştırabilirsin. Çünkü Protobuf sayesinde aynı API’yi farklı dillerde kolayca kullanabiliyoruz.

#### Ama gRPC’nin De Dezavantajları Yok Mu?
Olmaz mı? Var tabi hemde aceyip hoppidik hoppidik. 😅

#### Tarayıcı Desteği Yok 🚫
REST gibi direkt browser’dan çağrı yapamıyorsun. Çoğu zaman proxy sunucu gibi ekstra katmanlar eklemen gerekiyor.

#### Daha Karmaşık 🤯
gRPC kullanırken Protobuf dosyaları yazıp derlemen lazım. O yüzden başlarken biraz öğrenme eğrisi dik.

#### Teknoloji Uyumu ⚠️
Tüm dillerin ve framework’lerin gRPC’yi desteklemesi gerekiyor, yani her yerde çalıştırmak bazen sıkıntılı olabiliyor.

#### gRPC vs REST: Hangisi Daha İyi Gardaş Bi Bah Bahalım Bi Dene Bi Dene Bahalım Yaw :D?
Şimdi gelelim esas soruya: gRPC mi REST mi? 🤔

Özellik	gRPC 🛰️	REST 🌍
### gRPC vs REST: Hangisi Daha İyi Gardaş?

| Kriter          | gRPC 🏎️ | REST 🚗 |
|----------------|---------|---------|
| **Performans**  | Çok hızlı (Binary formatı kullanır) ⚡ | Daha yavaş (JSON formatı kullanır) 🐌 |
| **Veri Serileştirme** | Protobuf (Kompakt ve hızlı) 📦 | JSON (Daha büyük ve yavaş) 📄 |
| **İletişim Protokolü** | HTTP/2 kullanır (Daha hızlı ve verimli) 🔥 | Genellikle HTTP/1.1 kullanır (Daha yavaş) ⏳ |
| **Akış Desteği** | İki yönlü (Bidirectional Streaming) destekler 🔄 | Tek yönlü istek/cevap modeli ⬆️⬇️ |
| **Dil Desteği** | Çok dilli, otomatik kod üretimi 🏗️ | JSON tabanlı, manuel entegrasyon gerekiyor 🔨 |
| **Kullanım Alanı** | Mikroservisler, düşük gecikme isteyen sistemler 🎯 | Web API’leri, genel amaçlı servisler 🌍 |
| **Öğrenme Eğrisi** | Daha karmaşık, ek yapılandırma gerektirir 📉 | Daha kolay, yaygın kullanılır 📈 |
| **Tarayıcı Desteği** | Doğrudan destek yok 🚫 | Tarayıcılarda doğal olarak çalışır ✅ |

#### 👉 Özetle:
Eğer hız, verimlilik ve çift yönlü iletişim istiyorsan, gRPC süper! Ama eğer browser üzerinden kolayca erişilebilir bir API yazacaksan, REST daha mantıklı.

#### Projemiz Ne Yapıyor? (Borsa Takip Sistemi)
Hadi bakalım, yazdığım kodları da şöyle bir anlatayım! 📜

#### 📌 Veri Tabanı Modeli (Stock.java)
Şimdi borsada hisseler var ya, işte bu hisseleri saklamak için bir Stock modeli yazdım. 🏦

```java
@Entity
@Table(name = "stocks")
public class Stock {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

    @Column(name = "stock_symbol", unique = true, nullable = false)
    private String stockSymbol;

    private double price;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;
}
```
#### 💡 Ne iş yapıyor?

stockSymbol (Hisse Sembolü) - Örneğin: AAPL (Apple), TSLA (Tesla) gibi.

price (Fiyat) - O anki güncel fiyat.

lastUpdated - En son ne zaman güncellenmiş.

#### 📌 Veri Tabanı Sorguları (StockRepository.java)
Bu da Spring Data JPA ile hisse senedi sorgulamak için repository.

```java
public interface StockRepository extends JpaRepository<Stock, Long> {
Stock findByStockSymbol(String stockSymbol);
}
```

#### 📌 Database Schema (Veritabanı Şeması)
Aşağıdaki tablo yapıları, hisse senedi fiyat takibi ve alım-satım işlemleri için tasarlanmıştır.
Her tablo, belirli bir işlemi yönetmek için gerekli alanları içerir.

#### 📊 stocks Tablosu Yok Ancak Oluşturulabilir Sonraya Bıraktım
Bu tablo, takip edilen hisse senetlerinin bilgilerini tutar.

| Alan Adı       | Veri Tipi       | Açıklama                                          |
|---------------|---------------|--------------------------------------------------|
| `id`          | `BIGINT`      | Birincil anahtar, otomatik artan (`AUTO_INCREMENT`) |
| `stock_symbol` | `VARCHAR(255)` | Hisse senedi sembolü, benzersiz (`UNIQUE`), boş olamaz (`NOT NULL`) |
| `price`       | `DOUBLE`      | Güncel hisse senedi fiyatı |
| `last_updated` | `DATETIME`    | Son güncelleme zamanı |

#### 🔗 users Tablosu Yok Ancak Oluşturulabilir Sonraya Bıraktım
Bu tablo, sistemi kullanan kullanıcıları saklar.

| Alan Adı       | Veri Tipi       | Açıklama                                          |
|---------------|---------------|--------------------------------------------------|
| `id`          | `BIGINT`      | Birincil anahtar, otomatik artan (`AUTO_INCREMENT`) |
| `username`    | `VARCHAR(255)` | Kullanıcı adı, benzersiz (`UNIQUE`), boş olamaz (`NOT NULL`) |
| `email`       | `VARCHAR(255)` | Kullanıcı e-posta adresi, benzersiz (`UNIQUE`), boş olamaz (`NOT NULL`) |
| `password`    | `VARCHAR(255)` | Hashlenmiş şifreyi saklar |
| `created_at`  | `DATETIME`    | Kullanıcı oluşturulma tarihi |

#### Tablo Oluşturma

```sql
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE transactions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    stock_symbol VARCHAR(10) NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    transaction_type ENUM('BUY', 'SELL') NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
```

#### Tablo Açıklamaları:
✅ users tablosu: Kullanıcı bilgilerini saklar.
✅ transactions tablosu: Kullanıcıların yaptığı hisse alım-satım işlemlerini saklar.

#### 💡 Özellikler:

users.id → transactions.user_id ile ilişkilendirilmiştir.

transaction_type → BUY veya SELL değerlerini alır.

ON DELETE CASCADE → Kullanıcı silindiğinde, onun işlemleri de silinir.

#### 🔥 transactions Tablosu
Bu tablo, kullanıcıların hisse senedi alım ve satım işlemlerini kayıt altına alır.

| Alan Adı           | Veri Tipi         | Açıklama                                          |
|-------------------|-----------------|--------------------------------------------------|
| `id`              | `BIGINT`        | Birincil anahtar, otomatik artan (`AUTO_INCREMENT`) |
| `user_id`         | `BIGINT`        | Kullanıcı kimliği, `users.id` ile ilişkilidir (`FOREIGN KEY`) |
| `stock_id`        | `BIGINT`        | Hisse senedi kimliği, `stocks.id` ile ilişkilidir (`FOREIGN KEY`) |
| `quantity`        | `INT`           | İşlem miktarı (Adet) |
| `price`           | `DOUBLE`        | İşlem fiyatı |
| `transaction_type` | `ENUM('BUY', 'SELL')` | İşlem türü: Alım (`BUY`) veya Satım (`SELL`) |
| `timestamp`       | `DATETIME`      | İşlem tarihi ve saati |

#### 📌 gRPC Servisi (StockTradingServiceImpl.java)
Burada gRPC servisini yazdım, gelen stock sembolüne göre veritabanından fiyat çekiyor.

```java
@GrpcService
public class StockTradingServiceImpl extends StockTradingServiceGrpc.StockTradingServiceImplBase {

    private final StockRepository stockRepository;

    public StockTradingServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public void getStockPrice(StockRequest request, StreamObserver<StockResponse> responseObserver) {
        String stockSymbol = request.getStockSymbol();
        Stock stockEntity = stockRepository.findByStockSymbol(stockSymbol);

        StockResponse stockResponse = StockResponse.newBuilder()
                .setStockSymbol(stockEntity.getStockSymbol())
                .setPrice(stockEntity.getPrice())
                .setTimestamp(stockEntity.getLastUpdated().toString())
                .build();

        responseObserver.onNext(stockResponse);
        responseObserver.onCompleted();
    }
}
```

#### 📌 Protobuf Dosyası (stock.proto)
Bu da gRPC’nin şablon dosyası, buradan StockRequest ve StockResponse nesnelerini oluşturuyoruz.

```proto
syntax = "proto3";

package tr.com.huseyinaydin;

service StockTradingService{
rpc getStockPrice(StockRequest) returns (StockResponse);
}

message StockRequest{
string stock_symbol=1;
}

message StockResponse{
string stock_symbol=1;
double price=2;
string timestamp=3;
}
```

#### 📌 MySQL Ayarları (application.properties)
Verileri MySQL’de saklıyoruz. 🏦

```properties
spring.datasource.url = jdbc:mysql://localhost:3306/huseyin_aydin_db?createDatabaseIfNotExist=true
spring.datasource.username = root
spring.datasource.password = toor
spring.jpa.hibernate.ddl-auto = update
```

#### 📌 gRPC Port Ayarları (application.yaml)

```yaml
grpc:
server:
port: 9090
enable-reflection: true
```

#### Sonuçs
İşte böyle gardaş, gRPC kullanarak hızlı, güvenilir bir borsa takip sistemi yaptım. 🚀 REST’in yavaşlığına takılmadan, binary formatta, akıcı bir şekilde veri alıp gönderdik. 🎯 gRPC’nin avantajlarını kullanarak düşük bant genişliği ve yüksek performans elde ettik. Gelecekte streaming ekleyip gerçek zamanlı borsa fiyat akışı da ekleyebilirim! 🏆