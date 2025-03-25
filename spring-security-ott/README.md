OTT(One Time Token) Nedir?
OTT (One-Time Token) genellikle Tek Kullanımlık Token anlamına gelir. Tek kullanımlık tokenlar, güvenli doğrulama süreçlerinde kullanılır ve genellikle belirli bir süre içinde geçerlidir. Kullanıldıktan veya süresi dolduktan sonra tekrar kullanılamazlar.
Ancak, OTT bazen One-Time Password (OTP) Token ile karıştırılabilir. OTP, belirli bir kod veya şifre anlamına gelirken, OTT daha çok bir kimlik doğrulama tokenı olarak kullanılır.
Bağlama göre değişebilse de, Spring Security'deki kullanımında OTT, tek kullanımlık kimlik doğrulama tokenı anlamına gelir.

#### 🔹 Spring Security OTT'yi Nasıl Doğrular?
Spring Security, Tek Kullanımlık Token (OTT) doğrulaması için yerleşik bir filtre ve kimlik doğrulama sağlayıcısına sahiptir. Bu sayede sistem, kullanıcıların tek kullanımlık doğrulama bağlantıları aracılığıyla güvenli bir şekilde giriş yapmasını sağlar.

#### 🔗 /login/ott?token=XYZ Adresine Yapılan İstek
Kullanıcı, kendisine gönderilen sihirli bağlantıya tıklayarak veya token'ı manuel olarak giriş ekranına girerek kimlik doğrulama sürecini başlatır.

Bu işlem sonucunda, token bir sorgu parametresi olarak /login/ott adresine yönlendirilir ve istemci tarafından bir HTTP isteği gönderilir.

#### 🛡️ Spring Security Gelen İsteği Yakalayıp İnceler
Spring Security'nin bir bileşeni olan OneTimeTokenAuthenticationFilter, isteği otomatik olarak yakalar ve işler.

Bu filtre, gelen isteğin içeriğini inceler ve token değerini (token=XYZ) sorgu parametresinden çıkararak doğrulama süreci için hazırlar.

#### 🔄 Token, Kimlik Doğrulama Yöneticisine İletilir
Spring Security, çıkarılan token'ı doğrulamak için OneTimeTokenAuthenticationProvider bileşenine yönlendirir.

Bu aşamada, token'ın geçerliliği ve kullanıcıyla olan ilişkisi detaylı bir şekilde değerlendirilir.

#### ✅ Token Doğrulama Süreci
✔ Token’ın sistemde kayıtlı olup olmadığı kontrol edilir ve ilgili veritabanı ya da token deposu sorgulanır.
✔ Token’ın süresinin dolup dolmadığı incelenerek belirlenen zaman dilimi içinde kullanılıp kullanılmadığı doğrulanır.
✔ Token’ın doğru kullanıcı hesabıyla ilişkili olup olmadığı kontrol edilerek, başka biri tarafından kullanılıp kullanılmadığı anlaşılır.

#### 🔓 Kimlik Doğrulama Başarılı mı, Başarısız mı?
#### ✅ Eğer token geçerli ve süresi dolmamışsa → Spring Security, kullanıcıyı başarılı bir şekilde doğrular ve kullanıcı için yeni bir oturum başlatır.
#### ❌ Eğer token geçersiz, süresi dolmuş veya başka bir hesaba aitse → Sistem, 401 Yetkisiz Erişim (Unauthorized) hatası döndürerek kimlik doğrulamanın başarısız olduğunu bildirir.