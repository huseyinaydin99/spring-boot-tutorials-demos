# batch-processing-multi-thread (çok iş parçacıklı toplu işlemler)

Projemi Tanıtıyorum:

Projede amacım, milyonlarca ürün kaydını verimli bir şekilde işleyip, bu kayıtlarla ilgili çeşitli işlemleri hızlı bir biçimde gerçekleştirebilmekti. Bu işlemleri veritabanında güncellemek, gerekli indirim hesaplamalarını yapmak ve ardından bu güncellenen ürün bilgilerini Kafka kuyruğuna göndermek gibi önemli görevleri başarıyla yerine getirdim. Ayrıca, bu işlemlerin hızlı bir şekilde ve verimli bir biçimde yapılabilmesi için paralel işlem yapısını kullandım ve verileri batch (toplu) işlemlerle işledim.

Kafka Configuration:

Kafka ile iletişim kurmak için KafkaConfig sınıfını oluşturdum. Burada, ürün bilgilerini güncellediğimizde, bu bilgileri Kafka kuyruğuna göndermek için bir yapı hazırladım. Kafka, verilerin kuyruğa yerleştirilmesi ve farklı servisler arasında mesajlaşmayı sağlamak için harika bir araç. Buradaki yapılandırmamda, @Value dipnotu ile Kafka kuyruğunun adını application.properties dosyasından alıyorum ve NewTopic bean’i aracılığıyla bu kuyruğu oluşturuyorum. Bu kuyruğu, 3 partition ve 1 replication factor ile yapılandırdım, böylece daha yüksek bir veri işleme kapasitesine sahip oldu.

```Java
@Configuration
public class KafkaConfig {
@Value("${product.discount.update.topic}")
private String topicName;

    @Bean
    public NewTopic createTopic() {
        return new NewTopic(topicName, 3, (short) 1);  // 3 partition, 1 replication
    }
}
```

Paralel İşlemler ve splitIntoBatches Metodu:
Bu projede milyonlarca kaydı hızlı bir şekilde işleyebilmek için paralel işlem kullanmam gerekiyordu. Bunun için ExecutorService sınıfını kullanarak bir thread pool (iş parçacığı havuzu) oluşturdum. Her bir batch (toplu işlem grubu) için belirli sayıda ürün kaydını işleyen thread'ler (iş parçacıkları) çalıştırdım. Bunu sağlamak için splitIntoBatches metodunu geliştirdim. Bu metod, ürün ID'lerini belirli büyüklükteki gruplara ayırarak her bir gruptaki kayıtları paralel bir şekilde işleyecek şekilde yapılandırdım.
splitIntoBatches metodu, bir List<Long> ürün ID'lerini alıp, onları belirtilen batch boyutlarına bölüyor. Örneğin, 50 ürünlük gruplar halinde işlemi başlatabiliriz. Bu işlem daha sonra paralel olarak yürütülüyor ve her bir batch farklı iş parçacıklarında işleniyor.

```Java
private List<List<Long>> splitIntoBatches(List<Long> productIds, int batchSize) {
int totalSize = productIds.size();
int batchNums = (totalSize + batchSize - 1) / batchSize;
List<List<Long>> batches = new ArrayList<>();

    for (int i = 0; i < batchNums; i++) {
        int start = i * batchSize;
        int end = Math.min(totalSize, (i + 1) * batchSize);
        batches.add(productIds.subList(start, end));
    }
    return batches;
}
```

executeProductIds ve processProductIds Metodları:
Ürün ID'lerini işlemek için executeProductIds metodunu oluşturdum. Bu metod, ürün ID'lerini küçük parçalara böler ve her bir batch'i asenkron olarak işler. CompletableFuture.runAsync() metoduyla her bir batch paralel olarak işleniyor ve işlem sonunda tüm thread’lerin tamamlanmasını bekliyorum.

```Java
public void executeProductIds(List<Long> productIds) {
List<List<Long>> batches = splitIntoBatches(productIds, 50);  // Her batch için 50 ürün

    List<CompletableFuture<Void>> futures = batches
            .stream()
            .map(batch -> CompletableFuture.runAsync(() -> processProductIds(batch), executorService))
            .collect(Collectors.toList());

    // Tüm thread'lerin tamamlanmasını bekliyoruz
    CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
}
```
Burada processProductIds metodu her bir batch içindeki ürünleri tek tek işler. Her bir ürün için veritabanından çekme, indirim hesaplama ve güncelleme işlemleri yapılır. Ayrıca, her güncellenen ürün bilgisi Kafka kuyruğuna gönderilir.

```Java
private void processProductIds(List<Long> batch) {
System.out.println("Toplu işlem " + batch + " ilgili thread " + Thread.currentThread().getName());
batch.forEach(this::fetchUpdateAndPublish);
}
```

Bu yapı sayesinde, milyonlarca ürünü veritabanına kaydetmek ve Kafka’ya göndermek gibi işlemler çok daha hızlı ve verimli bir şekilde yapılabiliyor.

ProductService'de geleneksel olarak tek thread üzerinden işlem yapıldı.
ProductServiceV2'de ise çok iş parçacıklı, çoklu thread kullanılarak daha gelişmiş performans sağlandı. Çünkü 6 iş parcacığı birden çalışıyordu. 1 elin nesi var 6 elin gümbürtüsü var derler atalarımız.

<hr />

### Setup Kafka

1. Zookepeer sunucusunu başlatm.
    ```bin/zookeeper-server-start.bat config/zookeeper.properties```

2. Kafka sunucusunu başlatalım / Broker(instance veya node)
    ```bin/kafka-server-start.bat config/server.properties```

### Verileri resetlemek (Hızlı test)
```
curl --location --request POST 'http://localhost:9191/api/products/reset' \
--data ''
```

### Kayıtları işlemek (geleneksel senkron yapı(tek thread, tek senkman, tek piston))

```
curl --location 'http://localhost:9191/api/products/process' \
--header 'Content-Type: application/json' \
--data '[1001,1002,1003,1004,1005,1006,1007,1008,1009,1010,1011,1012,1013,1014,1015,1016,1017,1018,1019,1020,1021,1022,1023,1024,1025,1026,1027,1028,1029,1030,1031,1032,1033,1034,1035,1036,1037,1038,1039,1040,1041,1042,1043,1044,1045,1046,1047,1048,1049,1050,1051,1052,1053,1054,1055,1056,1057,1058,1059,1060,1061,1062,1063,1064,1065,1066,1067,1068,1069,1070,1071,1072,1073,1074,1075,1076,1077,1078,1079,1080,1081,1082,1083,1084,1085,1086,1087,1088,1089,1090,1091,1092,1093,1094,1095,1096,1097,1098,1099,1100,1101,1102,1103,1104,1105,1106,1107,1108,1109,1110,1111,1112,1113,1114,1115,1116,1117,1118,1119,1120,1121,1122,1123,1124,1125,1126,1127,1128,1129,1130,1131,1132,1133,1134,1135,1136,1137,1138,1139,1140,1141,1142,1143,1144,1145,1146,1147,1148,1149,1150,1151,1152,1153,1154,1155,1156,1157,1158,1159,1160,1161,1162,1163,1164,1165,1166,1167,1168,1169,1170,1171,1172,1173,1174,1175,1176,1177,1178,1179,1180,1181,1182,1183,1184,1185,1186,1187,1188,1189,1190,1191,1192,1193,1194,1195,1196,1197,1198,1199,1200,1201,1202,1203,1204,1205,1206,1207,1208,1209,1210,1211,1212,1213,1214,1215,1216,1217,1218,1219,1220,1221,1222,1223,1224,1225,1226,1227,1228,1229,1230,1231,1232,1233,1234,1235,1236,1237,1238,1239,1240,1241,1242,1243,1244,1245,1246,1247,1248,1249,1250,1251,1252,1253,1254,1255,1256,1257,1258,1259,1260,1261,1262,1263,1264,1265,1266,1267,1268,1269,1270,1271,1272,1273,1274,1275,1276,1277,1278,1279,1280,1281,1282,1283,1284,1285,1286,1287,1288,1289,1290,1291,1292,1293,1294,1295,1296,1297,1298,1299,1300]'
```
### Kayıtları işlemek (çok iş parçacıklı, çoklu thread, çoklu senkman, çoklu piston) :D şaka

```
curl --location 'http://localhost:9191/api/products/process/v2' \
--header 'Content-Type: application/json' \
--data '[1001,1002,1003,1004,1005,1006,1007,1008,1009,1010,1011,1012,1013,1014,1015,1016,1017,1018,1019,1020,1021,1022,1023,1024,1025,1026,1027,1028,1029,1030,1031,1032,1033,1034,1035,1036,1037,1038,1039,1040,1041,1042,1043,1044,1045,1046,1047,1048,1049,1050,1051,1052,1053,1054,1055,1056,1057,1058,1059,1060,1061,1062,1063,1064,1065,1066,1067,1068,1069,1070,1071,1072,1073,1074,1075,1076,1077,1078,1079,1080,1081,1082,1083,1084,1085,1086,1087,1088,1089,1090,1091,1092,1093,1094,1095,1096,1097,1098,1099,1100,1101,1102,1103,1104,1105,1106,1107,1108,1109,1110,1111,1112,1113,1114,1115,1116,1117,1118,1119,1120,1121,1122,1123,1124,1125,1126,1127,1128,1129,1130,1131,1132,1133,1134,1135,1136,1137,1138,1139,1140,1141,1142,1143,1144,1145,1146,1147,1148,1149,1150,1151,1152,1153,1154,1155,1156,1157,1158,1159,1160,1161,1162,1163,1164,1165,1166,1167,1168,1169,1170,1171,1172,1173,1174,1175,1176,1177,1178,1179,1180,1181,1182,1183,1184,1185,1186,1187,1188,1189,1190,1191,1192,1193,1194,1195,1196,1197,1198,1199,1200,1201,1202,1203,1204,1205,1206,1207,1208,1209,1210,1211,1212,1213,1214,1215,1216,1217,1218,1219,1220,1221,1222,1223,1224,1225,1226,1227,1228,1229,1230,1231,1232,1233,1234,1235,1236,1237,1238,1239,1240,1241,1242,1243,1244,1245,1246,1247,1248,1249,1250,1251,1252,1253,1254,1255,1256,1257,1258,1259,1260,1261,1262,1263,1264,1265,1266,1267,1268,1269,1270,1271,1272,1273,1274,1275,1276,1277,1278,1279,1280,1281,1282,1283,1284,1285,1286,1287,1288,1289,1290,1291,1292,1293,1294,1295,1296,1297,1298,1299,1300]'
```
### INSERT (kayıtları elle giriver hacım)
```sql
INSERT INTO Product (id, name, category, price, is_offer_applied, discount_percentage, price_after_discount) VALUES 
(1001, 'Apple iPhone 15', 'Electronics', 999.99, false, 0, 999.99),
(1002, 'Samsung Galaxy S23', 'Electronics', 899.99, false, 0, 899.99),
(1003, 'Sony WH-1000XM5 Headphones', 'Electronics', 349.99, false, 0, 349.99),
(1004, 'Dell XPS 13 Laptop', 'Computers', 1199.99, false, 0, 1199.99),
(1005, 'HP Spectre x360', 'Computers', 1349.99, 0, 0, 1349.99),
(1006, 'Canon EOS Rebel T7', 'Cameras', 549.99, 0, 0, 549.99),
(1007, 'Nikon D3500', 'Cameras', 499.99, 0, 0, 499.99),
(1008, 'GoPro Hero 11', 'Cameras', 399.99, 0, 0, 399.99),
(1009, 'Fitbit Charge 5', 'Wearables', 149.99, 0, 0, 149.99),
(1010, 'Apple Watch Series 8', 'Wearables', 399.00, 0, 0, 399.00),
(1011, 'Samsung Galaxy Watch 5', 'Wearables', 279.99, 0, 0, 279.99),
(1012, 'Bose QuietComfort 45', 'Electronics', 329.99, 0, 0, 329.99),
(1013, 'LG OLED65CXPUA 65" Smart OLED TV', 'Electronics', 1799.99, 0, 0, 1799.99),
(1014, 'Samsung 55" 4K UHD Smart TV', 'Electronics', 499.99, 0, 0, 499.99),
(1015, 'Sony 65" 4K Smart LED TV', 'Electronics', 799.99, 0, 0, 799.99),
(1016, 'Nintendo Switch OLED', 'Gaming', 349.99, 0, 0, 349.99),
(1017, 'PlayStation 5', 'Gaming', 499.99, 0, 0, 499.99),
(1018, 'Xbox Series X', 'Gaming', 499.99, 0, 0, 499.99),
(1019, 'Razer DeathAdder V2', 'Gaming', 49.99, 0, 0, 49.99),
(1020, 'Logitech G Pro X', 'Gaming', 129.99, 0, 0, 129.99),
(1021, 'Corsair K95 RGB Platinum', 'Gaming', 199.99, 0, 0, 199.99),
(1022, 'ASUS TUF Gaming Laptop', 'Computers', 899.99, 0, 0, 899.99),
(1023, 'Microsoft Surface Pro 7', 'Computers', 749.99, 0, 0, 749.99),
(1024, 'Lenovo ThinkPad X1 Carbon', 'Computers', 1349.99, 0, 0, 1349.99),
(1025, 'Apple MacBook Pro 14"', 'Computers', 1999.99, 0, 0, 1999.99),
(1026, 'Acer Predator Helios 300', 'Computers', 1199.99, 0, 0, 1199.99),
(1027, 'JBL Flip 6 Bluetooth Speaker', 'Electronics', 129.99, 0, 0, 129.99),
(1028, 'Sonos One SL Speaker', 'Electronics', 199.99, 0, 0, 199.99),
(1029, 'Anker Soundcore Motion+', 'Electronics', 99.99, 0, 0, 99.99),
(1030, 'Ecovacs Deebot X1', 'Home Appliances', 799.99, 0, 0, 799.99),
(1031, 'iRobot Roomba 980', 'Home Appliances', 499.99, 0, 0, 499.99),
(1032, 'Dyson V15 Detect Cordless Vacuum', 'Home Appliances', 699.99, 0, 0, 699.99),
(1033, 'Shark IQ Robot Vacuum', 'Home Appliances', 349.99, 0, 0, 349.99),
(1034, 'Whirlpool 25 Cu. Ft. French Door Refrigerator', 'Home Appliances', 2499.99, 0, 0, 2499.99),
(1035, 'GE 18.1 Cu. Ft. Top-Freezer Refrigerator', 'Home Appliances', 799.99, 0, 0, 799.99),
(1036, 'KitchenAid KSM150PSER Stand Mixer', 'Home Appliances', 379.99, 0, 0, 379.99),
(1037, 'Breville BES870XL Barista Express Espresso Machine', 'Home Appliances', 699.99, 0, 0, 699.99),
(1038, 'Cuisinart DCC-3200 14-Cup Coffee Maker', 'Home Appliances', 89.99, 0, 0, 89.99),
(1039, 'Ninja BN701 Professional Plus Blender', 'Home Appliances', 119.99, 0, 0, 119.99),
(1040, 'Instant Pot Duo 7-in-1 Electric Pressure Cooker', 'Home Appliances', 89.99, 0, 0, 89.99),
(1041, 'Hamilton Beach FlexBrew Single Serve Coffee Maker', 'Home Appliances', 59.99, 0, 0, 59.99),
(1042, 'Sony PlayStation VR2', 'Gaming', 549.99, 0, 0, 549.99),
(1043, 'Oculus Quest 2', 'Gaming', 299.99, 0, 0, 299.99),
(1044, 'Razer Kraken V3 Pro', 'Gaming', 129.99, 0, 0, 129.99),
(1045, 'HyperX Cloud II Gaming Headset', 'Gaming', 99.99, 0, 0, 99.99),
(1046, 'Corsair HS70 Pro Wireless Gaming Headset', 'Gaming', 99.99, 0, 0, 99.99),
(1047, 'Corsair K65 RGB Mini Mechanical Keyboard', 'Gaming', 109.99, 0, 0, 109.99),
(1048, 'SteelSeries Rival 600 Gaming Mouse', 'Gaming', 79.99, 0, 0, 79.99),
(1049, 'ASUS ROG Strix Z590-E Gaming Motherboard', 'Computers', 299.99, 0, 0, 299.99),
(1050, 'MSI MPG Z490 Gaming Edge WiFi', 'Computers', 189.99, 0, 0, 189.99),
(1051, 'Dell XPS 13', 'Computers', 999.99, 0, 0, 999.99),
(1052, 'Asus ROG Zephyrus G14', 'Computers', 1449.99, 0, 0, 1449.99),
(1053, 'HP Envy 15', 'Computers', 1349.99, 0, 0, 1349.99),
(1054, 'Apple iPad Pro 12.9"', 'Tablets', 1099.99, 0, 0, 1099.99),
(1055, 'Samsung Galaxy Tab S8', 'Tablets', 799.99, 0, 0, 799.99),
(1056, 'Amazon Fire HD 10', 'Tablets', 149.99, 0, 0, 149.99),
(1057, 'Apple AirPods Pro', 'Electronics', 249.99, 0, 0, 249.99),
(1058, 'Sony WF-1000XM4', 'Electronics', 279.99, 0, 0, 279.99),
(1059, 'Bose SoundSport Free', 'Electronics', 199.99, 0, 0, 199.99),
(1060, 'Samsung Galaxy Buds Pro', 'Electronics', 199.99, 0, 0, 199.99),
(1061, 'Apple HomePod Mini', 'Home Appliances', 99.99, 0, 0, 99.99),
(1062, 'Google Nest Hub', 'Home Appliances', 89.99, 0, 0, 89.99),
(1063, 'Amazon Echo Show 8', 'Home Appliances', 129.99, 0, 0, 129.99),
(1064, 'Ring Video Doorbell 4', 'Home Security', 199.99, 0, 0, 199.99),
(1065, 'Arlo Pro 4 Spotlight Camera', 'Home Security', 199.99, 0, 0, 199.99),
(1066, 'SimpliSafe 8-Piece Wireless Home Security System', 'Home Security', 229.99, 0, 0, 229.99),
(1067, 'Furbo Dog Camera', 'Pet Supplies', 199.99, 0, 0, 199.99),
(1068, 'Litter-Robot 3 Connect', 'Pet Supplies', 499.99, 0, 0, 499.99),
(1069, 'Whistle Go Explore Pet Tracker', 'Pet Supplies', 129.99, 0, 0, 129.99),
(1070, 'PetSafe Smart Feed Automatic Dog and Cat Feeder', 'Pet Supplies', 189.99, 0, 0, 189.99),
(1071, 'Garmin Forerunner 945', 'Wearables', 599.99, 0, 0, 599.99),
(1072, 'Apple iPhone 14 Pro', 'Phones', 999.99, 0, 0, 999.99),
(1073, 'Samsung Galaxy S22 Ultra', 'Phones', 1199.99, 0, 0, 1199.99),
(1074, 'Google Pixel 6 Pro', 'Phones', 899.99, 0, 0, 899.99),
(1075, 'OnePlus 10 Pro', 'Phones', 799.99, 0, 0, 799.99),
(1076, 'Xiaomi Mi 11 Ultra', 'Phones', 1199.99, 0, 0, 1199.99),
(1077, 'Sony A7 III Mirrorless Camera', 'Cameras', 1999.99, 0, 0, 1999.99),
(1078, 'Canon EOS R6', 'Cameras', 2499.99, 0, 0, 2499.99),
(1079, 'Nikon Z6 II', 'Cameras', 1996.95, 0, 0, 1996.95),
(1080, 'Fujifilm X-T4', 'Cameras', 1699.99, 0, 0, 1699.99),
(1081, 'Sony Xperia 1 III', 'Phones', 1299.99, 0, 0, 1299.99),
(1082, 'Roku Streaming Stick 4K', 'Electronics', 49.99, 0, 0, 49.99),
(1083, 'Amazon Fire TV Stick 4K', 'Electronics', 39.99, 0, 0, 39.99),
(1084, 'NVIDIA Shield TV Pro', 'Electronics', 199.99, 0, 0, 199.99),
(1085, 'Apple TV 4K', 'Electronics', 179.99, 0, 0, 179.99),
(1086, 'Logitech Harmony Elite Remote', 'Electronics', 349.99, 0, 0, 349.99),
(1087, 'Samsung Galaxy Tab A7', 'Tablets', 229.99, 0, 0, 229.99),
(1088, 'Microsoft Surface Laptop Go', 'Computers', 549.99, 0, 0, 549.99),
(1089, 'Acer Chromebook Spin 311', 'Computers', 279.99, 0, 0, 279.99),
(1090, 'Razer Blade 15 Advanced', 'Computers', 2499.99, 0, 0, 2499.99),
(1091, 'HyperX Alloy FPS Pro Mechanical Keyboard', 'Gaming', 69.99, 0, 0, 69.99),
(1092, 'Logitech MX Master 3', 'Electronics', 99.99, 0, 0, 99.99),
(1093, 'HP Pavilion x360 Convertible', 'Computers', 649.99, 0, 0, 649.99),
(1094, 'Dell Inspiron 15', 'Computers', 549.99, 0, 0, 549.99),
(1095, 'LG Gram 17"', 'Computers', 1649.99, 0, 0, 1649.99),
(1096, 'Lenovo IdeaPad 3', 'Computers', 399.99, 0, 0, 399.99),
(1097, 'Asus VivoBook Flip 14', 'Computers', 599.99, 0, 0, 599.99),
(1098, 'Samsung Galaxy Chromebook 2', 'Computers', 699.99, 0, 0, 699.99),
(1099, 'Alienware m15 R6', 'Computers', 1599.99, 0, 0, 1599.99),
(1100, 'HP Omen 15', 'Computers', 1249.99, 0, 0, 1249.99),
(1101, 'Canon PowerShot G7 X Mark III', 'Cameras', 749.99, 0, 0, 749.99),
(1102, 'Nikon COOLPIX P1000', 'Cameras', 999.99, 0, 0, 999.99),
(1103, 'DJI Mavic Air 2', 'Drones', 799.99, 0, 0, 799.99),
(1104, 'DJI Phantom 4 Pro V2.0', 'Drones', 1599.99, 0, 0, 1599.99),
(1105, 'DJI Mini 2', 'Drones', 449.99, 0, 0, 449.99),
(1106, 'Ring Indoor Cam', 'Home Security', 59.99, 0, 0, 59.99),
(1107, 'Nest Thermostat', 'Home Appliances', 129.99, 0, 0, 129.99),
(1108, 'Samsung SmartThings Hub', 'Home Automation', 69.99, 0, 0, 69.99),
(1109, 'Philips Hue White and Color Ambiance', 'Home Automation', 199.99, 0, 0, 199.99),
(1110, 'Apple Watch Series 7', 'Wearables', 399.99, 0, 0, 399.99),
(1111, 'Fitbit Versa 3', 'Wearables', 229.99, 0, 0, 229.99),
(1112, 'Garmin Venu 2', 'Wearables', 399.99, 0, 0, 399.99),
(1113, 'Withings ScanWatch', 'Wearables', 279.99, 0, 0, 279.99),
(1114, 'Sony PlayStation 5', 'Gaming', 499.99, 0, 0, 499.99),
(1115, 'Xbox Series X', 'Gaming', 499.99, 0, 0, 499.99),
(1116, 'Nintendo Switch OLED', 'Gaming', 349.99, 0, 0, 349.99),
(1117, 'Logitech G Pro X Gaming Headset', 'Gaming', 129.99, 0, 0, 129.99),
(1118, 'Corsair K95 RGB Platinum Mechanical Keyboard', 'Gaming', 199.99, 0, 0, 199.99),
(1119, 'Razer DeathAdder V2', 'Gaming', 69.99, 0, 0, 69.99),
(1120, 'Asus TUF Gaming A15', 'Computers', 1049.99, 0, 0, 1049.99),
(1121, 'MSI GF65 Thin', 'Computers', 1099.99, 0, 0, 1099.99),
(1122, 'HP Spectre x360 14', 'Computers', 1249.99, 0, 0, 1249.99),
(1123, 'Roku Ultra', 'Electronics', 99.99, 0, 0, 99.99),
(1124, 'Sony WH-1000XM4', 'Electronics', 348.99, 0, 0, 348.99),
(1125, 'Bose QuietComfort 35 II', 'Electronics', 299.99, 0, 0, 299.99),
(1126, 'LG OLED C1 55"', 'Televisions', 1499.99, 0, 0, 1499.99),
(1127, 'Samsung QN90A Neo QLED 65"', 'Televisions', 1799.99, 0, 0, 1799.99),
(1128, 'Sony X90J 65"', 'Televisions', 1499.99, 0, 0, 1499.99),
(1129, 'TCL 6-Series 55"', 'Televisions', 649.99, 0, 0, 649.99),
(1130, 'Hisense U8G 55"', 'Televisions', 749.99, 0, 0, 749.99),
(1131, 'Vizio M-Series Quantum 50"', 'Televisions', 529.99, 0, 0, 529.99),
(1132, 'Beats Studio3 Wireless', 'Electronics', 349.99, 0, 0, 349.99),
(1133, 'JBL Charge 5', 'Electronics', 179.99, 0, 0, 179.99),
(1134, 'Anker Soundcore 2', 'Electronics', 39.99, 0, 0, 39.99),
(1135, 'Sony SRS-XB33', 'Electronics', 129.99, 0, 0, 129.99),
(1136, 'Marshall Emberton', 'Electronics', 149.99, 0, 0, 149.99),
(1137, 'Google Pixel 5a', 'Phones', 449.99, 0, 0, 449.99),
(1138, 'OnePlus Nord 2', 'Phones', 399.99, 0, 0, 399.99),
(1139, 'Xiaomi Redmi Note 10 Pro', 'Phones', 279.99, 0, 0, 279.99),
(1140, 'Realme GT Master Edition', 'Phones', 349.99, 0, 0, 349.99),
(1141, 'Apple iPhone SE (2022)', 'Phones', 429.99, 0, 0, 429.99),
(1142, 'Canon EOS Rebel T7', 'Cameras', 449.99, 0, 0, 449.99),
(1143, 'Sony A6400 Mirrorless Camera', 'Cameras', 898.99, 0, 0, 898.99),
(1144, 'Panasonic Lumix GH5', 'Cameras', 1299.99, 0, 0, 1299.99),
(1145, 'Fujifilm Instax Mini 11', 'Cameras', 69.99, 0, 0, 69.99),
(1146, 'GoPro HERO10 Black', 'Cameras', 499.99, 0, 0, 499.99),
(1147, 'Eufy RoboVac 11S', 'Home Appliances', 219.99, 0, 0, 219.99),
(1148, 'iRobot Roomba i3+', 'Home Appliances', 549.99, 0, 0, 549.99),
(1149, 'Shark IQ Robot Vacuum', 'Home Appliances', 399.99, 0, 0, 399.99),
(1150, 'Dyson V11 Torque Drive', 'Home Appliances', 699.99, 0, 0, 699.99),
(1151, 'Bissell CrossWave Cordless Max', 'Home Appliances', 399.99, 0, 0, 399.99),
(1152, 'KitchenAid Artisan Stand Mixer', 'Kitchen', 429.99, 0, 0, 429.99),
(1153, 'Instant Pot Duo Plus', 'Kitchen', 119.99, 0, 0, 119.99),
(1154, 'Ninja Foodi Digital Air Fry Oven', 'Kitchen', 249.99, 0, 0, 249.99),
(1155, 'Anova Precision Cooker', 'Kitchen', 199.99, 0, 0, 199.99),
(1156, 'Breville Bambino Plus', 'Kitchen', 499.99, 0, 0, 499.99),
(1157, 'NordicTrack Commercial 1750', 'Fitness', 1899.99, 0, 0, 1899.99),
(1158, 'Bowflex SelectTech 552 Dumbbells', 'Fitness', 429.99, 0, 0, 429.99),
(1159, 'ProForm Pro 2000 Treadmill', 'Fitness', 1299.99, 0, 0, 1299.99),
(1160, 'Hydrow Rower', 'Fitness', 2295.00, 0, 0, 2295.00),
(1161, 'Saris M2 Smart Trainer', 'Fitness', 499.99, 0, 0, 499.99),
(1162, 'HP OfficeJet Pro 9015e', 'Office', 229.99, 0, 0, 229.99),
(1163, 'Brother MFC-J995DW', 'Office', 199.99, 0, 0, 199.99),
(1164, 'Canon PIXMA G5020', 'Office', 249.99, 0, 0, 249.99),
(1165, 'Epson EcoTank ET-4760', 'Office', 499.99, 0, 0, 499.99),
(1166, 'Fujitsu ScanSnap iX1500', 'Office', 449.99, 0, 0, 449.99),
(1167, 'Lenovo ThinkPad X1 Carbon Gen 9', 'Computers', 1399.99, 0, 0, 1399.99),
(1168, 'Razer BlackWidow V3', 'Gaming', 139.99, 0, 0, 139.99),
(1169, 'HyperX Cloud II Wireless', 'Gaming', 149.99, 0, 0, 149.99),
(1170, 'SteelSeries Rival 600', 'Gaming', 79.99, 0, 0, 79.99),
(1171, 'Sony A8H 55"', 'Televisions', 1499.99, 0, 0, 1499.99),
(1172, 'Samsung Frame TV 50"', 'Televisions', 1299.99, 0, 0, 1299.99),
(1173, 'LG NanoCell 90 Series 65"', 'Televisions', 1199.99, 0, 0, 1199.99),
(1174, 'Xiaomi Mi TV 4S 55"', 'Televisions', 499.99, 0, 0, 499.99),
(1175, 'Acer Nitro XV272U', 'Monitors', 449.99, 0, 0, 449.99),
(1176, 'Samsung Odyssey G7 32"', 'Monitors', 799.99, 0, 0, 799.99),
(1177, 'LG UltraGear 27GL850-B', 'Monitors', 499.99, 0, 0, 499.99),
(1178, 'Dell UltraSharp U2720Q', 'Monitors', 599.99, 0, 0, 599.99),
(1179, 'ViewSonic VX2758', 'Monitors', 279.99, 0, 0, 279.99),
(1180, 'Apple iMac 24"', 'Computers', 1299.99, 0, 0, 1299.99),
(1181, 'Samsung Galaxy Watch 4', 'Wearables', 249.99, 0, 0, 249.99),
(1182, 'Amazfit GTR 3', 'Wearables', 179.99, 0, 0, 179.99),
(1183, 'Fossil Gen 6', 'Wearables', 299.99, 0, 0, 299.99),
(1184, 'Microsoft Surface Go 3', 'Tablets', 549.99, 0, 0, 549.99),
(1185, 'Lenovo Tab P11 Pro', 'Tablets', 499.99, 0, 0, 499.99),
(1186, 'Google Pixel Slate', 'Tablets', 799.99, 0, 0, 799.99),
(1187, 'Asus ZenBook 13 OLED', 'Computers', 899.99, 0, 0, 899.99),
(1188, 'Dell G15 Ryzen Edition', 'Computers', 949.99, 0, 0, 949.99),
(1189, 'MSI Creator Z16', 'Computers', 2599.99, 0, 0, 2599.99),
(1190, 'Apple MacBook Pro 16"', 'Computers', 2499.99, 0, 0, 2499.99),
(1191, 'Alienware 34 Curved Gaming Monitor', 'Monitors', 1499.99, 0, 0, 1499.99),
(1192, 'HP EliteDisplay E243', 'Monitors', 219.99, 0, 0, 219.99),
(1193, 'Philips Brilliance 499P9H', 'Monitors', 999.99, 0, 0, 999.99),
(1194, 'AOC CQ32G1', 'Monitors', 319.99, 0, 0, 319.99),
(1195, 'Sony SRS-RA5000', 'Speakers', 699.99, 0, 0, 699.99),
(1196, 'Yamaha YAS-209 Soundbar', 'Speakers', 349.99, 0, 0, 349.99),
(1197, 'Klipsch R-41PM', 'Speakers', 399.99, 0, 0, 399.99),
(1198, 'Polk Audio T15', 'Speakers', 99.99, 0, 0, 99.99),
(1199, 'JBL Flip 5', 'Speakers', 119.99, 0, 0, 119.99),
(1200, 'Ultimate Ears BOOM 3', 'Speakers', 149.99, 0, 0, 149.99),
(1201, 'GoPro HERO9 Black', 'Cameras', 399.99, 0, 0, 399.99),
(1202, 'Sony Cyber-shot DSC-RX100 VII', 'Cameras', 1198.99, 0, 0, 1198.99),
(1203, 'Canon EOS M50 Mark II', 'Cameras', 699.99, 0, 0, 699.99),
(1204, 'Fujifilm X-T4', 'Cameras', 1699.99, 0, 0, 1699.99),
(1205, 'Olympus OM-D E-M10 Mark IV', 'Cameras', 699.99, 0, 0, 699.99),
(1206, 'DJI Mini SE', 'Drones', 299.99, 0, 0, 299.99),
(1207, 'Autel Robotics EVO Lite+', 'Drones', 1249.99, 0, 0, 1249.99),
(1208, 'Eufy Security 2K Indoor Cam', 'Home Security', 39.99, 0, 0, 39.99),
(1209, 'Arlo Essential Spotlight Camera', 'Home Security', 129.99, 0, 0, 129.99),
(1210, 'Apple iPad Air 5', 'Tablets', 599.99, 0, 0, 599.99),
(1211, 'Samsung Galaxy Tab S7 FE', 'Tablets', 529.99, 0, 0, 529.99),
(1212, 'Amazon Kindle Paperwhite', 'Tablets', 129.99, 0, 0, 129.99),
(1213, 'Garmin Forerunner 245', 'Wearables', 299.99, 0, 0, 299.99),
(1214, 'Samsung Galaxy Buds Pro', 'Wearables', 199.99, 0, 0, 199.99),
(1215, 'Sony WF-1000XM4', 'Wearables', 279.99, 0, 0, 279.99),
(1216, 'Razer Blade 14', 'Computers', 1799.99, 0, 0, 1799.99),
(1217, 'Asus ROG Zephyrus G14', 'Computers', 1649.99, 0, 0, 1649.99),
(1218, 'Microsoft Surface Pro 8', 'Computers', 1099.99, 0, 0, 1099.99),
(1219, 'Logitech MX Master 3', 'Computers', 99.99, 0, 0, 99.99),
(1220, 'Anker PowerCore 20100', 'Electronics', 49.99, 0, 0, 49.99),
(1221, 'Beats Flex Wireless Earbuds', 'Electronics', 49.99, 0, 0, 49.99),
(1222, 'Nest Audio', 'Electronics', 99.99, 0, 0, 99.99),
(1223, 'Amazon Echo Show 10', 'Electronics', 249.99, 0, 0, 249.99),
(1224, 'Sony X90K 55"', 'Televisions', 1099.99, 0, 0, 1099.99),
(1225, 'Samsung QLED 8K QN800A 65"', 'Televisions', 2999.99, 0, 0, 2999.99),
(1226, 'Vizio V-Series 50"', 'Televisions', 379.99, 0, 0, 379.99),
(1227, 'Samsung HW-Q950A Soundbar', 'Speakers', 1699.99, 0, 0, 1699.99),
(1228, 'Sony HT-G700 Soundbar', 'Speakers', 599.99, 0, 0, 599.99),
(1229, 'Bose Smart Soundbar 700', 'Speakers', 799.99, 0, 0, 799.99),
(1230, 'JBL Bar 5.1 Surround', 'Speakers', 499.99, 0, 0, 499.99),
(1231, 'Canon EOS R6', 'Cameras', 2499.99, 0, 0, 2499.99),
(1232, 'Sony Alpha 7C', 'Cameras', 1799.99, 0, 0, 1799.99),
(1233, 'Leica Q2', 'Cameras', 5299.99, 0, 0, 5299.99),
(1234, 'Panasonic Lumix G100', 'Cameras', 699.99, 0, 0, 699.99),
(1235, 'DJI Inspire 2', 'Drones', 3299.99, 0, 0, 3299.99),
(1236, 'Holy Stone HS720E', 'Drones', 299.99, 0, 0, 299.99),
(1237, 'Ring Floodlight Cam', 'Home Security', 249.99, 0, 0, 249.99),
(1238, 'Wyze Cam v3', 'Home Security', 29.99, 0, 0, 29.99),
(1239, 'Tile Mate', 'Electronics', 24.99, 0, 0, 24.99),
(1240, 'Oculus Quest 2', 'Electronics', 399.99, 0, 0, 399.99),
(1241, 'Anker Soundcore Liberty Air 2 Pro', 'Wearables', 129.99, 0, 0, 129.99),
(1242, 'Sennheiser Momentum True Wireless 2', 'Wearables', 299.99, 0, 0, 299.99),
(1243, 'Microsoft Surface Laptop 4', 'Computers', 1299.99, 0, 0, 1299.99),
(1244, 'Dell XPS 13', 'Computers', 1149.99, 0, 0, 1149.99),
(1245, 'Asus ZenBook Duo', 'Computers', 1499.99, 0, 0, 1499.99),
(1246, 'Apple Mac Mini M1', 'Computers', 699.99, 0, 0, 699.99),
(1247, 'Google Nest Hub Max', 'Electronics', 229.99, 0, 0, 229.99),
(1248, 'Lenovo Smart Clock', 'Electronics', 49.99, 0, 0, 49.99),
(1249, 'Insignia 24" Fire TV Edition', 'Televisions', 139.99, 0, 0, 139.99),
(1250, 'TCL 4-Series 43"', 'Televisions', 299.99, 0, 0, 299.99),
(1251, 'BenQ EW3280U 32"', 'Monitors', 699.99, 0, 0, 699.99),
(1252, 'LG UltraFine 5K', 'Monitors', 1299.99, 0, 0, 1299.99),
(1253, 'HP Pavilion 27"', 'Monitors', 289.99, 0, 0, 289.99),
(1254, 'Alienware AW2720HF 27"', 'Monitors', 449.99, 0, 0, 449.99),
(1255, 'Dell S2721DGF 27"', 'Monitors', 499.99, 0, 0, 499.99),
(1256, 'Sonos Beam', 'Speakers', 399.99, 0, 0, 399.99),
(1257, 'JBL Xtreme 3', 'Speakers', 349.99, 0, 0, 349.99),
(1258, 'Polk Audio Signa S2', 'Speakers', 199.99, 0, 0, 199.99),
(1259, 'Vizio SB36512-F6', 'Speakers', 249.99, 0, 0, 249.99),
(1260, 'Sony STR-DH790 Receiver', 'Speakers', 449.99, 0, 0, 449.99),
(1261, 'Onkyo TX-RZ50', 'Speakers', 1099.99, 0, 0, 1099.99),
(1262, 'Yamaha RX-V6A', 'Speakers', 749.99, 0, 0, 749.99),
(1263, 'Bose Home Speaker 500', 'Speakers', 399.99, 0, 0, 399.99),
(1264, 'Samsung HW-A450 Soundbar', 'Speakers', 199.99, 0, 0, 199.99),
(1265, 'Epson Expression Home XP-4100', 'Office', 99.99, 0, 0, 99.99),
(1266, 'Brother HL-L2350DW', 'Office', 119.99, 0, 0, 119.99),
(1267, 'Canon imageCLASS MF644Cdw', 'Office', 399.99, 0, 0, 399.99),
(1268, 'HP OfficeJet Pro 8025e', 'Office', 169.99, 0, 0, 169.99),
(1269, 'Samsung Galaxy S21 FE', 'Mobile Phones', 699.99, 0, 0, 699.99),
(1270, 'OnePlus 9 Pro', 'Mobile Phones', 969.99, 0, 0, 969.99),
(1271, 'Google Pixel 6a', 'Mobile Phones', 499.99, 0, 0, 499.99),
(1272, 'Xiaomi Mi 11 Lite', 'Mobile Phones', 349.99, 0, 0, 349.99),
(1273, 'Sony Xperia 1 III', 'Mobile Phones', 1299.99, 0, 0, 1299.99),
(1274, 'Razer Kiyo Pro Webcam', 'Electronics', 199.99, 0, 0, 199.99),
(1275, 'Razer Kiyo game', 'Electronics', 199.99, 0, 0, 199.99)
(1276, 'Google Nest Learning Thermostat', 'Smart Home', 249.99, 0, 0, 249.99),
(1277, 'Apple HomePod Mini', 'Smart Home', 99.99, 0, 0, 99.99),
(1278, 'Amazon Echo Dot (5th Gen)', 'Smart Home', 49.99, 0, 0, 49.99),
(1279, 'Philips Hue White and Color Ambiance', 'Smart Home', 199.99, 0, 0, 199.99),
(1280, 'Ring Video Doorbell 4', 'Smart Home', 199.99, 0, 0, 199.99),
(1281, 'Wyze Smart Lock', 'Smart Home', 119.99, 0, 0, 119.99),
(1282, 'Arlo Pro 4 Spotlight Camera', 'Home Security', 199.99, 0, 0, 199.99),
(1283, 'Eufy Security Video Doorbell 2K', 'Home Security', 159.99, 0, 0, 159.99),
(1284, 'Logitech Brio 4K Webcam', 'Electronics', 199.99, 0, 0, 199.99),
(1285, 'Canon PIXMA TR150 Wireless Printer', 'Office', 249.99, 0, 0, 249.99),
(1286, 'Apple Watch Series 7', 'Wearables', 399.99, 0, 0, 399.99),
(1287, 'Fitbit Charge 5', 'Wearables', 179.99, 0, 0, 179.99),
(1288, 'Samsung Galaxy Watch4', 'Wearables', 249.99, 0, 0, 249.99),
(1289, 'Garmin Venu 2', 'Wearables', 399.99, 0, 0, 399.99),
(1290, 'Microsoft Surface Duo 2', 'Mobile Phones', 1499.99, 0, 0, 1499.99),
(1291, 'Huawei P50 Pro', 'Mobile Phones', 1099.99, 0, 0, 1099.99),
(1292, 'Sony WH-1000XM4', 'Wearables', 349.99, 0, 0, 349.99),
(1293, 'Bose QuietComfort Earbuds', 'Wearables', 279.99, 0, 0, 279.99),
(1294, 'Dell UltraSharp U2720Q', 'Monitors', 719.99, 0, 0, 719.99),
(1295, 'LG 34WN80C-B UltraWide Monitor', 'Monitors', 649.99, 0, 0, 649.99),
(1296, 'BenQ PD2700U 27" 4K Monitor', 'Monitors', 499.99, 0, 0, 499.99),
(1297, 'Samsung Odyssey G9', 'Monitors', 1399.99, 0, 0, 1399.99),
(1298, 'Apple AirPods Max', 'Wearables', 549.99, 0, 0, 549.99),
(1299, 'JBL Flip 5 Bluetooth Speaker', 'Speakers', 119.99, 0, 0, 119.99),
(1300, 'Sony SRS-XB43 Bluetooth Speaker', 'Speakers', 249.99, 0, 0, 249.99);
```