# spring-boot-websocket

Bu projeyi geliştirirken, gerçek zamanlı sipariş takibi ve güncelleme işlemleri için WebSocket teknolojisini kullanmaya karar verdim. WebSocket ile, sunucu ve istemci arasında sürekli açık bir bağlantı kurarak, kullanıcıların sipariş durumu güncellemelerini anında almasını sağladım. Bu şekilde, veritabanı sorguları veya sayfa yenilemeleri gerekmeden, sipariş güncellemeleri canlı olarak görüntüleniyor.

Teknolojik Yığın olarak, SockJS ve STOMP kullanarak WebSocket ile haberleşmeyi sağladım. SockJS, WebSocket uyumsuzluklarını aşmak için, istemci tarafında WebSocket benzeri bir bağlantı sağlar. STOMP ise, mesajlaşma protokolü olarak işlev gördü ve sunucudan gelen sipariş durumu güncellemelerini doğru şekilde iletebilmemi sağladı.

Kullanıcı Arayüzü için HTML, CSS, ve Bootstrap gibi modern araçları kullandım. Kullanıcılar sipariş durumu hakkında bilgi almak istediklerinde, anlık olarak siparişin durumunu görüp takip edebiliyorlar. Sipariş durumu güncellemeleri, ilgili ikonlarla ve bir ilerleme çubuğuyla görselleştirildi.

WebSocket bağlantısı kurulduğunda, sunucu, her sipariş durumu güncellemesini /topic/order topiği üzerinden gönderiyor. Bu güncellemeler, her kullanıcıya anında iletiliyor. JavaScript kullanarak, gelen verileri alıp doğru şekilde HTML öğelerine ekledim.

Sunucu tarafında, Spring Boot kullanarak WebSocket’i entegre ettim. Spring Boot'ta WebSocket yapılandırmasını aktifleştirdim ve gerekli endpoint'leri oluşturduk. Sipariş durumu güncellemelerini sunucudan istemcilere göndermek için Spring STOMP kullanarak verimli bir şekilde haberleşme sağladım.

Sonuç olarak, bu proje ile gerçek zamanlı sipariş güncellemeleri ve kullanıcı dostu bir arayüz oluşturdum. Proje kullanıcıların siparişlerini hızlı ve etkin bir şekilde takip etmelerini sağlıyor. Her şey sorunsuz şekilde çalışıyor ve kullanıcılar siparişlerinin hangi aşamada olduğunu anlık olarak takip edebiliyorlar.