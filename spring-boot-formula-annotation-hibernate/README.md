# formula-example
Hibernate'de @Formula dipnotu, veritabanı sütunlarına bağlı olmayan, bir SQL ifadesi ile hesaplanan sanal alanlar oluşturmak için kullanılır. Bu dipnot sayesinde, tabloda fiziksel olarak olmayan ama sorgularda yer alabilen değerler tanımlayabilirsin. Örneğin, bir fiyat sütununa indirim uygulayarak "net fiyat" gibi bir değer hesaplayabilirsin. Kodda kolaylık sağlarken, performansı etkileyebileceği için dikkatli kullanmak gerekir.

### Ürün Eklemesyon Canım
```
curl -X 'POST' \
  'http://localhost:9191/products' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '[
	{
		"category" : "Elektronik",
		"discountPercentage" : 5,
		"name" : "Apple iPhone 15",
		"price" : 999.99
	},
	{
		"category" : "Elektronik",
		"discountPercentage" : 5,
		"name" : "Samsung Galaxy S23",
		"price" : 899.99
	},
	{
		"category" : "Elektronik",
		"discountPercentage" : 5,
		"name" : "Sony WH-1000XM5 Kulaklık",
		"price" : 349.99
	},
	{
		"category" : "Bilgisayar",
		"discountPercentage" : 10,
		"name" : "Dell XPS 13 Laptop",
		"price" : 1199.99
	},
	{
		"category" : "Bilgisayar",
		"discountPercentage" : 10,
		"name" : "HP Spectre x360",
		"price" : 1349.99
	},
	{
		"category" : "Kamera",
		"discountPercentage" : 2,
		"name" : "Canon EOS Rebel T7",
		"price" : 549.99
	},
	{
		"category" : "Kamera",
		"discountPercentage" : 3,
		"name" : "Nikon D3500",
		"price" : 499.99
	},
	{
		"category" : "Kamera",
		"discountPercentage" : 0,
		"name" : "GoPro Hero 11",
		"price" : 399.99
	},
	{
		"category" : "Giyilebilir Cihazlar",
		"discountPercentage" : 0,
		"name" : "Fitbit Charge 5",
		"price" : 149.99
	},
	{
		"category" : "Giyilebilir Cihazlar",
		"discountPercentage" : 3,
		"name" : "Apple Saat Serisi 8",
		"price" : 399.0
	}
]
```

### Açıklama
Ürün yönetimi için kapsamlı bir API geliştirdim ve Hibernate’in güçlü özelliklerinden biri olan @Formula dipnotunu etkili bir şekilde kullandım. @Formula dipnotu sayesinde, indirimi sonrası ürün fiyatını SQL düzeyinde dinamik olarak hesapladım. Böylece fiyat hesaplaması kod yazarken ekstra iş yükü oluşturmadan doğrudan veritabanında yapılmış oldu. Bu yöntem, hem performans hem de veri tutarlılığı açısından büyük avantaj sağladı. Örneğin, price - (price * discountPercentage / 100) formülü ile ürünlerin indirim sonrası fiyatları hesaplanıyor ve sonuçlar bir sütun gibi API'ye taşınıyor. Bu özellik, özellikle dinamik veritabanı işlemlerinde kod karmaşıklığını azaltmak ve doğru hesaplamalar yapmak için oldukça kritik bir çözüm sundu.

Spring uygulamasında, kodun standartlara uygun olması ve karışıklığın önlenmesi için spring.jpa.hibernate.naming.physical-strategy özelliğini kullandım. Burada PhysicalNamingStrategyStandardImpl kullanarak veritabanı tablolarının ve sütun adlarının yazım biçimini düzenledim. Hibernate’in varsayılan olarak sağladığı "snake_case" yerine, belirlediğim standartlarla sütun ve tablo adlarının doğrudan sınıf ve alan adlarına paralel olmasını sağladım. Bu yapılandırma, büyük projelerde yazım standartlarını korumak ve kod-veritabanı uyumunu artırmak açısından önemlidir. Özellikle ekip çalışmalarında, herkesin aynı standartlarla çalışmasını kolaylaştırır ve uzun vadede projelerin bakımını daha kolay hale getirir.

Uygulamanın kontrol katmanında ProductController ile /products uç noktalarını tanımladım ve POST ile ürün ekleme, GET ile ürün listeleme işlemlerini yönettim. ProductService iş mantığını barındıran katman olarak tasarlandı ve ProductRepository ile entegre edilerek ürünlerin veritabanına kaydedilmesini ve sorgulanmasını sağladı. DTO olarak ProductResponse kullanarak yalnızca gerektiği kadar veri döndürüldü ve ProductResponseMapper ile dönüşümler daha okunabilir hale getirildi. MySQL bağlantısını application.properties dosyasında yapılandırdım ve Hibernate’in ddl-auto özelliği sayesinde veritabanını otomatik olarak güncel tutarak hızlı geliştirme ortamı sağladım. Bu projede, temiz kod ve esneklik odaklı bir mimari oluşturmaya özen gösterdim.