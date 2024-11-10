# api-performance-multi-thread

Projenin amacı, aynı anda birden fazla API’ye farklı thread’ler üzerinden istek yaparak yüksek performans elde etmek. Yani bir ürünün bilgisi, fiyatı ve stok durumu gibi verileri ayrı ayrı API’lerden çağırıyorum, ancak bu çağrıları tek bir thread’de sırayla yapmak yerine, her bir çağrıyı farklı thread’lerde paralel olarak yapıyorum. Bu şekilde, tüm veriler aynı anda çekildiği için işlem süresini minimuma indirip API’nin performansını artırıyorum. En sonunda tüm veriler geldiğinde, bunları bir araya getirip tek bir ürün detay nesnesi olarak geri döndürüyorum.
ProductService, InventoryService ve PriceService gibi bileşenleri @Autowired ile sınıf içine aldım, böylece bu servislerin sağladığı verilere kolayca ulaşabiliyorum. getProductById, getPriceByProductById ve getInventoryByProductId gibi metotlarla ürün bilgilerini, fiyatı ve stok durumunu her biri farklı thread'lerde paralel bir şekilde çekiyorum. CompletableFuture.allOf() ile tüm asenkron işlemleri aynı anda devreye sokarak daha hızlı veri çekmeyi sağlıyorum. Sonuçlar hazır olduğunda, join() ile her bir geleceği bekleyip verileri topluyorum ve bunları kullanarak ProductDetailDTO nesnesini oluşturuyorum. Bu nesne, ürünle ilgili tüm detayları barındıran bir DTO olarak döndürülüyor ve böylece kullanıcıya tek seferde tüm bilgilere ulaşabileceği kapsamlı bir yapı sağlanıyor.
ProductSyncFacade sınıfında işlemler tek bir thread üzerinde, sırayla ilerliyor. İlk olarak, ürün bilgilerini productService aracılığıyla alıyorum, ardından priceService ile fiyat bilgisini, son olarak da inventoryService ile stok bilgisini çekiyorum. Her bir işlem önceki tamamlandıktan sonra başlıyor, böylece tüm işlemler tek tek, sıra halinde ilerliyor. En sonunda ise tüm verileri bir araya getirerek ProductDetailDTO nesnesi halinde geri döndürüyorum. Bu yöntem, işlemlerin sırayla yapıldığı ve tek bir thread kullanıldığı için daha basit ama çoklu isteğe bağlı paralel yapı kadar hızlı değil.

```sql
Veritabanı şeması:

CREATE TABLE `category` (
  `id` bigint PRIMARY KEY,
  `name` varchar(255),
  `type` varchar(255),
  `status` varchar(255)
);

CREATE TABLE `products` (
  `id` bigint PRIMARY KEY,
  `category_id` bigint,
  `name` varchar(255),
  `description` text,
  `status` varchar(255)
);

CREATE TABLE `price` (
  `id` bigint PRIMARY KEY,
  `product_id` bigint,
  `price` double,
  `valid_from` timestamp,
  `valid_to` timestamp,
  `status` varchar(255)
);

CREATE TABLE `inventory` (
  `id` bigint PRIMARY KEY,
  `product_id` bigint,
  `warehouse_id` bigint,
  `available_quantity` integer,
  `reserved_quantity` integer,
  `status` varchar(255)
);

ALTER TABLE `inventory` ADD FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

ALTER TABLE `products` ADD FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

ALTER TABLE `price` ADD FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);


-- Kategoriler tablosu kayıt
INSERT INTO `category` (`id`, `name`, `type`, `status`) VALUES
(1, 'Elektronik', 'Mal', 'aktif'),
(2, 'Giyim', 'Mal', 'aktif'),
(3, 'Bağ Bahçe', 'Mal', 'aktif');

-- Ürünler tablosu kayıt
INSERT INTO `products` (`id`, `category_id`, `name`, `description`, `status`) VALUES
(1, 1, 'Akıllı telefon', 'Son model yüksek hızlı telefon', 'aktif'),
(2, 1, 'Laptop', '15-inç laptop ile 8GB RAM, 256GB SSD', 'aktif'),
(3, 2, 'Hırka', 'Yaşlı dedeler ve nineler', 'aktif'),
(4, 3, 'Tablet', 'Eşek Sıpalarına özel', 'aktif');

-- Fiyat tablosu kayıt
INSERT INTO `price` (`id`, `product_id`, `price`, `valid_from`, `valid_to`, `status`) VALUES
(1, 1, 699.99, NOW(), DATE_ADD(NOW(), INTERVAL 1 YEAR), 'aktif'),
(2, 2, 1299.99, NOW(), DATE_ADD(NOW(), INTERVAL 1 YEAR), 'aktif'),
(3, 3, 59.99, NOW(), DATE_ADD(NOW(), INTERVAL 1 YEAR), 'aktif'),
(4, 4, 299.99, NOW(), DATE_ADD(NOW(), INTERVAL 1 YEAR), 'aktif');

-- Envanter tablosu kayıt
INSERT INTO `inventory` (`id`, `product_id`, `warehouse_id`, `available_quantity`, `reserved_quantity`, `status`) VALUES
(1, 1, 1, 100, 10, 'kullanılabilir'),
(2, 2, 1, 50, 5, 'kullanılabilir'),
(3, 3, 2, 200, 20, 'kullanılabilir'),
(4, 4, 3, 30, 2, 'kullanılabilir');


-- tüm tablolardan veri çekmesyon
SELECT * from products p ;

SELECT * FROM category c ;

SELECT * FROM inventory i ;

SELECT * FROM price p ;
```
