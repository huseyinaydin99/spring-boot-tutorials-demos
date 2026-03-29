### jpa-trick
💪🔥 JPA ile Sipariş-Detay İlişki Modeli Kuruldu – @Embeddable & @ElementCollection ile Koleksiyon Yönetimi ⚙️📦

Bu projede sipariş ile ürün detaylarını birbirinden ayırıp, fakat aralarındaki bağı en güçlü şekilde kurmayı hedefledim; zira sipariş görünürde bir bütün olsa da, hakikatte onu anlamlı kılan detay tablosundaki ürünlerdir 📦✨

Controller katını yalnızca bir giriş kapısı olarak konumlandırdım; gelen isteği karşılar, işi service’e devrederim, böylece sorumluluklar net ve düzenli kalır ⚖️

Service tarafında ise işin özü vardır; siparişi oluşturur, veriyi işler ve ürün adına göre arama gibi işlemleri burada yönetirim. Böylece kontrol bende olur, sistem ise genişlemeye açık kalır 🧠

Entity yapısında @Embeddable ile tekrar eden veri yapısını tek sınıfta topladım, @ElementCollection ile de bu verileri ayrı bir tabloda tutarak JPA’nin ince mimarisini kullandım; yani tek entity gibi görünen yapı aslında arka planda iki tabloya yayılmış durumdadır 🎯

Repository’de yazdığım JOIN sorgusu ile, ürün adı üzerinden filtreleme yaparak o ürünü içeren siparişleri çektim; yani detaydaki veriye bakarak üstteki siparişi buldum 🧩

Özetle ben bu projede, veriyi doğru parçalayarak modelledim, JPA’nin güçlü özelliklerini kullanarak ilişkiyi kurdum ve sistemi sade ama derin bir mimariyle inşa ettim ⚡