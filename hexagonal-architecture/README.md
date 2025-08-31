#### ✨ Hexagonal Architecture (Altıgen Mimari) Nedir?

Yazılım geliştirmede bağımlılıkları tersine çevirerek 🔄 (dependency inversion) uygulamanın kalbini ❤️ dış dünyadan 🌍 izole etmeyi amaçlayan bir mimari yaklaşımdır 🏗️; nedeni, yazılımın belirli framework’lere ⚙️, veritabanlarına 🗄️ veya arayüz teknolojilerine 🖥️ sıkı sıkıya bağlı kalmasının uzun vadede ⏳ esnekliği 🤸‍♂️ ve sürdürülebilirliği 🌱 yok etmesidir.

Bu mimarinin sonucu olarak 🎯 uygulamanın iş mantığı (domain) merkezi bir çekirdekte 🔒 korunur, dış dünya ile olan etkileşimler 🌐 (veritabanı 🗃️, kullanıcı arayüzü 👩‍💻, üçüncü parti servisler 🔌) “port” ve “adapter” aracılığıyla yönetilir; böylece teknoloji 🛠️ değişse bile iş kuralları 📜 bozulmadan sistemin evrilmesi 🔄 sağlanır.

Ana amacı ✅, test edilebilir 🧪, bağımsız 🕊️, sürdürülebilir 🌿 ve değişime dayanıklı 🛡️ yazılımlar üretmektir; yani bugünün ihtiyaçlarını 📌 karşılarken yarının bilinmezliklerine ❓ karşı geliştiriciyi 👨‍💻👩‍💻 hazırlayan stratejik bir tasarım felsefesidir 🧠.

#### 💡 Hexagonal Architecture’ta:

Port 🎛️ → uygulamanın dış dünya 🌍 ile iletişim kurmak için tanımladığı soyut bir arayüzdür 🪄

Adapter 🔌 → bu soyut arayüzü gerçek dünyadaki 🌐 bir teknolojiye ⚙️, araca 🛠️ veya protokole 📡 uyarlayan somut bir uygulamadır.

Neden böyledir? 🤔 Çünkü iş kurallarının (domain) 📖 dış dünya 🌎 ile doğrudan temas etmesi 🤝 uygulamayı kırılgan 🪫 ve teknolojiye bağımlı 🧷 hale getirir. Port 🌀 bu bağımlılığı keserek ✂️ “ne yapılacağını” 📌 tanımlar, Adapter 🔧 ise “nasıl yapılacağını” 🛠️ teknolojiye göre şekillendirir.

Bunun sonucu ✅ olarak veritabanı 🗄️, kullanıcı arayüzü 🖼️ ya da API 🔗 değiştiğinde yalnızca ilgili adapter 🔧 değiştirilir, fakat iş mantığı 📜na dokunulmaz ❌; böylece yazılım 💻 esnek 🤸‍♂️, modüler 🧩 ve sürdürülebilir 🌍 hale gelir.

#### Hexagonal Architecture Port Nedir?

🚪 Port, Hexagonal Architecture’ta uygulamanın dış dünya 🌎 ile iletişimini tanımlayan, iş mantığını teknoloji bağımlılıklarından 🖥️❌ koruyan soyut bir sözleşmedir 📜; çünkü eğer iş kuralları doğrudan veritabanı 🗄️, API 🔗 veya kullanıcı arayüzü 🖱️ ile temas etseydi, her teknolojik değişimde 🔄 sistem kırılgan 💔 hale gelir ve sürdürülebilirliğini kaybeder 🕳️.

Port bu bağımlılığı keserek “ne yapılacak ❓” sorusunu tanımlar, dış dünyanın karmaşıklığını 🌪️ içeriye sokmadan, iş mantığının saf 🧼 ve temiz kalmasını sağlar.

💡 Bunun sonucu olarak uygulama, teknoloji değişse 🔧⚡ bile bozulmaz 🛡️; yalnızca portu uygulayan adapter 🔌 güncellenir ve sistem esnek 🤸, dayanıklı 🏋️ ve evrimleşebilir 🦋 kalır.

#### Hexagonal Architecture Adapter Nedir?

Hexagonal Architecture’da 🧩 adapter, iş mantığının (domain) 🧠 saf ve bağımsız kalmasını sağlayan 🌱 “çevirmen”dir 🗣️; çünkü domain yalnızca kurallarını 📜 bilir ama dış dünya 🌍 (veritabanı 💾, API 🌐, mesaj kuyruğu 📡, kullanıcı arayüzü 🖥️) farklı diller konuşur, işte adapter bu farkı kapatır 🌉; böylece domain “kaydet” der ✅, adapter bunu SQL’e 🔀, JSON’a 📝 ya da mesaj kuyruğuna 📦 çevirir, sonuçta domain teknolojiye bağımlı olmaz 🕶️, sistem kolay test edilir 🧪, değişime daha dayanıklı olur 💪, yani adapter = bağımlılıkları izole eden 🛡️, iş mantığını özgürleştiren 🕊️ stratejik köprüdür 🚀.

#### Hexagonal Architecture Input ve Output Kavramları Nedir?

Hexagonal Architecture’da 🧩 input = dış dünyanın 🌍 iş mantığını 🧠 tetiklediği giriş kapısı 🚪, output = iş mantığının dış dünyaya gönderdiği sonuç veya isteği 📤 olarak düşünülebilir; yani input tarafında kullanıcı 🧑‍💻, API çağrısı 🌐 ya da mesaj kuyruğu 📡 domain’e “şunu yap” der 🗣️, domain saf kurallarıyla 📜 işini yapar ⚙️, ardından output tarafında veritabanına 💾 kayıt atar, başka bir servise 🌍 istek yollar veya event üretir 🔔; bu ayrım sayesinde domain “kimin çağırdığı” ve “sonucun nereye gittiği” ile ilgilenmez 🙅‍♂️, yalnızca kuralları işletir 🔄, böylece sistem hem daha test edilebilir 🧪 hem de teknolojilere bağımlı olmaktan kurtulur 🕊️, yani input–output kavramı = bağımlılıkların yönünü belirleyen 🧭 ve domain’in özgürlüğünü koruyan 🛡️ temel ilkedir 🚀.

#### 🏗️ Hexagonal Architecture Şematize

```
                       +--------------------+
                       |   User Interface   |
                       +--------------------+
                                |
                                v
                        +---------------+
                        |   UI Adapter  |
                        +---------------+

+------------------+                          +-------------------+
|    Database      |                          | Third-Party Serv. |
+------------------+                          +-------------------+
|                                              |
v                                              v
+------------------+                          +-------------------+
| Database Adapter |                          |   Service Adapter |
+------------------+                          +-------------------+

             \                                    /
              \                                  /
               \                                /
                v                              v
              ====================================
              ||                                ||
              ||        CORE DOMAIN             ||
              ||    (Business Logic / Rules)    ||
              ||                                ||
              ====================================
```

