# qr-code-generator
Google Zxing API ile QR kodu oluşturma.

# Gerekli Bağımlılıklar

```
<dependency>
    <groupId>com.google.zxing</groupId>
    <artifactId>core</artifactId>
    <version>3.3.2</version>
</dependency>

<dependency>
    <groupId>com.google.zxing</groupId>
    <artifactId>javase</artifactId>
    <version>3.3.2</version>
</dependency>
```

Ben bir QR kod uygulaması yaptım, hem QR kod oluşturuyor hem de bunları okuyabiliyor. İlk olarak, bir hesap oluşturma özelliği ekledim. Kullanıcı adı, e-posta, telefon numarası ve şifre gibi bilgileri girince, bu bilgileri bir QR koda dönüştürüp sunucuda bir PNG dosyası olarak saklıyorum. QR kodu oluşturmak için "QRCodeWriter" ve "MatrixToImageWriter" kullandım. Daha sonra kullanıcı bu QR kodu kolayca indirebiliyor veya kullanabiliyor. İşin güzel tarafı, QR kodu oluşturduğumda dosya yolu gibi şeyleri de modelle bağlantılı olarak ayarlayıp ön tarafa gönderiyorum, böylece kullanıcı direkt görebiliyor.

Bir de QR kod okuma özelliğim var! Kullanıcı daha önce oluşturduğu veya başka bir yerden bulduğu QR kodu seçip gönderiyor. Ben de o QR kodunu okuyup içindeki bilgileri kullanıcıya geri döndürüyorum. Bunun için önce QR kod resmini alıyorum, sonra "LuminanceSource" ve "BinaryBitmap" kullanarak içindeki metni çözüyorum. İçeriği modelle ekrana yansıtıyorum. Böylece hem QR kod yazma hem de okuma işlemini kolayca yapabiliyom. Eğlenceli bir deneyim oldu ölmektende güldüm! :D