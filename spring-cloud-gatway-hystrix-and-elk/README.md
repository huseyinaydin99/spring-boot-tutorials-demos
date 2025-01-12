# spring-cloud-gateway-hystrix-and-elk


API-Gateway
-----------
```bash
URL : http://localhost:8989/order/bookOrder
HTTP Method : POST
```
JSON isteği:
```json
{
	"order":{
		"id": 103,
		"name": "Bilgisayar",
		"qty": 1,
		"price": 8000
		
	},
	"payment":{}
}
```
JSON cevabı:
```json
{
    "order": {
        "id": 26,
        "name": "Lenovo Y510P 750M SLI",
        "qty": 5,
        "price": 4000
    },
    "amount": 4000,
    "transactionId": "9a021fa6-2061-4332-bdb7-b1358b3430c2",
    "message": "Ödeme işlemi başarılı siparişiniz alındı."
}

```
```bash
URL : http://localhost:8989/payment/26
HTTP Method : GET
```
JSON cevabı:
```json
{
    "paymentId": 1,
    "transactionId": "d86cfeca-0b26-455e-a1a2-ac3e53707829",
    "orderId": 103,
    "paymentStatus": "SUCCESS",
    "amount":4000
}
```

#### Hystrix nedir?
Hystrix, Netflix tarafından geliştirilen bir fault-tolerance (hata toleransı) kütüphanesidir ve mikroservis mimarilerinde hizmet kesintilerini yönetmek, yayılmasını engellemek için circuit breaker (devre kesici) desenini uygular. Bu sayede, bir servisin başarısızlığı durumunda diğer servislerin çalışmaya devam etmesini sağlayarak sistemin dayanıklılığını artırır.

#### Elasticsearch nedir?
Elasticsearch, dağıtık ve ölçeklenebilir bir arama ve analitik motorudur; yapılandırılmış ve yapılandırılmamış büyük veri üzerinde gerçek zamanlı arama, analiz ve depolama imkanı sunar. Hızlı ve güçlü arama yetenekleri sayesinde log analizi, veri görselleştirme ve full-text search gibi kullanım alanlarında öne çıkar.

#### Kibana nedir?
Kibana, Elasticsearch ile entegre çalışan bir veri görselleştirme ve analiz aracıdır; kullanıcıların verilerini grafikler, tablolar ve haritalar gibi etkileşimli görsellerle anlamlandırmasını sağlar. Log analizi, metrik izleme ve sistem performansı gibi konularda güçlü bir arayüz sunarak veriyle içgörü elde etmeyi kolaylaştırır.

#### Logstash nedir?
Logstash, farklı kaynaklardan gelen verileri toplama, işleme ve Elasticsearch’e yönlendirme görevini üstlenen güçlü bir veri işleme ardışık düzen aracıdır. Esnek yapısıyla log, metrik ve olay verilerini filtreleyip dönüştürerek merkezi bir sistemde kolayca analiz edilmesini sağlar.

#### Elasticsearch, Kibana ve Logstash'in birbiri ile olan ilişkisi nedir?
Elasticsearch, veri depolama ve hızlı arama sunarken, Logstash bu verileri farklı kaynaklardan toplayıp işleyerek Elasticsearch’e aktarır. Kibana ise Elasticsearch’te depolanan verileri görselleştirerek kullanıcıların analiz yapmasını ve içgörü elde etmesini sağlar; birlikte ELK Stack olarak güçlü bir veri analitik ekosistemi oluştururlar.

#### Elasticseach, Kibana, Logstash 7.6.2 indirme linkleri.
```
https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-7.6.2-windows-x86_64.zip
https://artifacts.elastic.co/downloads/kibana/kibana-7.6.2-windows-x86_64.zip
https://artifacts.elastic.co/downloads/logstash/logstash-7.6.2.zip
```

#### ÖNEMLİ NOT: 
İŞLETİM SİSTEMİ DİLİ İNGİLİZCE DEĞİLSE ONU İNGİLİZCEYE ÇEK YOKSA LOGSTASH UP OLMUYOR KARAKTER SORUNU ÇIKARIYOR.
EĞER İŞLETİM SİSTEMİ SINGLE LANGUAGE(TEK DİL) İSE SANAL MAKINAYA INGILIZCE DILI OLAN İŞLETİM SİSTEMİ KUR!
```
elasticsearch/bin>elasticsearch.bat // --localhost:9200 --localhost:9200/_cat/indices <<<--- kibana ayaktayken çalışır.
kibana/bin>kibana.bat //--localhost:5601/app/kibana --localhost:5601/app/kibana#/dev_tools/console
logstash/bin>chcp 65001 //Türkçe karakter sorununu gidermeye çalıştım ama pek yemedi.
logstash/bin>logstash.bat -f logstash.conf
```

#### logstash.conf Dosyası
```
input {
    file {
        path => "C:\\Users\\Huseyin_Aydin\\Desktop\\logs\\oms.log"
        start_position => "beginning"
		codec => plain {
		  charset => "UTF-8"
		}
    }
}

output {
    stdout {
        codec => rubydebug
    }
    elasticsearch {
        hosts => ["http://localhost:9200"]
        index => "huseyinaydin-%{+yyyy.MM.dd}"
		codec => plain {
			charset => "UTF-8"
		}
    }
}
```

#### Kibana:
Dev Tools kısmına gel. 
```
PUT huseyinaydin
{
  "settings": {
    "index": {
      "number_of_shards": 3,
      "number_of_replicas": 2
    }
  }
} --<<index oluştur.
```
Kibana'nın Dev Tools kısmında yazılan bu komut, Elasticsearch'te bir indeks oluşturmak için kullanılır. Bu işlem, Elasticsearch'ün RESTful API'sini Kibana üzerinden çalıştırarak gerçekleştirilir.

##### Açıklama:
PUT huseyinaydin: huseyinaydin adında yeni bir indeks oluşturur. Bu indeks, Elasticsearch'te veri saklamak için bir kapsayıcı görevi görür.
"settings": Bu kısım, oluşturulan indeksin yapılandırma ayarlarını tanımlar.
"number_of_shards": 3: İndeksin 3 parça (shard) halinde depolanacağını belirtir. Her bir shard, indeksin bir parçasıdır ve Elasticsearch, büyük veri kümelerini shard'lar halinde dağıtarak yönetir.
"number_of_replicas": 2: Her shard'ın 2 kopyasının (replika) oluşturulacağını belirtir. Replikalar, yüksek erişilebilirlik ve veri yedekliliği sağlar.
Bu İşlem Nerede Yapılıyor?
Bu işlem, Kibana'nın Dev Tools bölümünden Elasticsearch'e bir HTTP isteği göndererek gerçekleştirilir. Dev Tools, Elasticsearch'e REST API çağrıları yapmanın hızlı ve kolay bir yoludur.
```
POST /huseyinaydin/default/
{
	"name": "event processing",
	"instructor": {
		"firstName": "Hüseyin",
		"lastName": "AYDIN"
	}
}
```

POST: Yeni bir doküman eklemek veya veri göndermek için kullanılan HTTP yöntemidir.
/huseyinaydin/default/:
huseyinaydin: Daha önce oluşturulmuş olan indeksin adıdır.
default: Elasticsearch'te opsiyonel bir tür (type) belirtir. Elasticsearch 7.x sürümünden itibaren type kavramı kaldırılmıştır. Ancak eski kodlar için hâlâ kullanılabilir.
Bu endpoint, huseyinaydin adlı indekse veri ekleyeceğinizi belirtir.

2. Veri Gövdesi (Body):
```
{
	"name": "event processing",
	"instructor": {
		"firstName": "Hüseyin",
		"lastName": "AYDIN"
	}
}
```
name: Doküman için bir alan (field). Bu alanın değeri "event processing" olarak tanımlanmış.
instructor: Bu, bir JSON nesnesi (object).
firstName: "Hüseyin"
lastName: "AYDIN"
Bu veri Elasticsearch'e eklenerek indeks içinde bir doküman olarak saklanır.

Bu Kod Ne Yapar?
Yeni Bir Doküman Ekler:
Bu kod, huseyinaydin adlı indekse yeni bir veri kaydı (doküman) ekler.
Oluşturulan Dokümanın ID'si Elasticsearch Tarafından Atanır:
Eğer bir ID belirtilmezse (örneğin /huseyinaydin/default/1 şeklinde belirtilmezse), Elasticsearch otomatik olarak benzersiz bir ID atar.
Arama ve Filtreleme İçin Kullanılabilir:
Bu doküman, daha sonra Elasticsearch sorguları ile (örneğin, name alanına göre veya instructor.firstName alanına göre) aranabilir ve filtrelenebilir.
Örnek Cevap:
Başarılı bir şekilde çalıştırıldığında Elasticsearch aşağıdaki gibi bir yanıt döner:

```
{
  "_index": "huseyinaydin",
  "_type": "default",
  "_id": "generated_id",
  "_version": 1,
  "result": "created",
  "_shards": {
    "total": 2,
    "successful": 1,
    "failed": 0
  },
  "_seq_no": 0,
  "_primary_term": 1
}
```
Gelen cevap:

Dokümanın hangi indekse kaydedildiğini,
Hangi ID ile kaydedildiğini,
İşlemin başarılı olup olmadığını gösterir.