# notification-system

Bu projede Spring Boot kullanarak gerçek zamanlı bildirim sistemi geliştirdim. WebSocket protokolü ile istemciler arasında anlık iletişim kurdum. Proje için önce bir WebSocket konfigürasyonu hazırladım ve @EnableWebSocketMessageBroker ile mesaj yönlendirme mekanizmasını etkinleştirdim. Mesajlar "/topic" üzerinden dağıtılırken, istemciler "/app" prefiksini kullanarak mesaj gönderebiliyor.

Sunucu tarafında, mesajların gönderilmesi ve alınması için @MessageMapping ve @SendTo anotasyonlarıyla çalışan bir controller oluşturdum. Ayrıca, kullanıcıların sisteme erişebileceği farklı endpoint'ler tanımladım.

Frontend tarafında ise SockJS ve STOMP.js kullanarak WebSocket bağlantısı kurdum. İstemci, gönderilen mesajları dinleyerek anında ekranda gösteriyor. Mesaj gönderme işlemini bir input alanı ve buton yardımıyla gerçekleştirdim. Gelen mesajları dinlemek için "/topic/notifications" kanalına abone oldum.

Bu yapı sayesinde bir istemciden gönderilen mesajlar, diğer tüm istemcilere gerçek zamanlı olarak ulaşıyor. Bu projeyi, özellikle anlık bildirim sistemleri veya canlı sohbet uygulamaları gibi senaryolar için bir temel oluşturmak amacıyla geliştirdim. Projede elde ettiğim deneyim, WebSocket protokolü ve STOMP entegrasyonu konusundaki bilgimi pekiştirdi.