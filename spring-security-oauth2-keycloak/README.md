#### oauth2-authorization
Keycloak ile kimlik doğrulama entegrasyonunu Spring Boot uygulamamda başarıyla gerçekleştirdim. İlk olarak, Keycloak sunucusunu Docker üzerinde çalıştırdım ve gerekli konfigürasyonları tamamladım. Ardından, Spring Boot projemde keycloak-spring-boot-starter bağımlılığını ekleyerek Keycloak ile entegrasyon sağladım. application.properties dosyasında Keycloak yapılandırmasını yaparak, doğru realm, client ve secret bilgilerini belirledim. Spring Security yapılandırması ile güvenlik ayarlarını yaptım ve başarılı giriş sonrası yönlendirme işlemi için bir success URL belirledim. Son olarak, Keycloak ile kullanıcı doğrulamasının ardından Spring Boot uygulamamda korunan sayfalara erişimi test ederek entegrasyonu tamamladım.
Keycloak kimlik oauth2 doğrulama ve yetkilendirme.
Keycloak ile oauth2 Spring Boot ve Angular entegrasyonu.
Keycloak Access Token, Refresh Token ve GitHub OAuth2.

### OAuth2 ile Kimlik Doğrulama ve Yetkilendirme
![Ekran görüntüsü 2024-12-30 140849](https://github.com/user-attachments/assets/3fc2d891-365c-4a27-ab31-4553578fa8a1)
![Ekran görüntüsü 2024-12-30 141112](https://github.com/user-attachments/assets/0ab591be-2d2c-462b-84ae-4f553528fd2c)
![Ekran görüntüsü 2024-12-30 141202](https://github.com/user-attachments/assets/e0f5c493-d1de-4c15-a6e5-9bfaa4770434)
![Ekran görüntüsü 2024-12-30 143515](https://github.com/user-attachments/assets/b5fd642e-73de-4ae4-afa2-a34abb5670b6)
![Ekran görüntüsü 2024-12-30 143531](https://github.com/user-attachments/assets/3cbd41a4-a449-409e-b50d-119e1942800f)
![Ekran görüntüsü 2024-12-30 143545](https://github.com/user-attachments/assets/e3b6aa6e-3661-4f82-901e-4fd844868e47)
![Ekran görüntüsü 2024-12-30 144131](https://github.com/user-attachments/assets/d22bd5d3-3344-46af-9365-2c7fa8521c34)
![Ekran görüntüsü 2024-12-30 150540](https://github.com/user-attachments/assets/f84f5b35-b6bb-44c6-b61d-b7c862678c94)
![Ekran görüntüsü 2024-12-30 150850](https://github.com/user-attachments/assets/7f148aef-ce8b-4b81-9940-1023ac959be7)


### OAuth2 Angular ve Spring Boot Entegrasyonu

![Ekran görüntüsü 2024-12-23 165244](https://github.com/user-attachments/assets/1eba1730-6930-41bf-bee8-7808c0ab560a)
![Ekran görüntüsü 2024-12-23 165647](https://github.com/user-attachments/assets/43fb58c4-e2a8-47a2-a290-c5b89c970b0a)
![Ekran görüntüsü 2024-12-23 170228](https://github.com/user-attachments/assets/403a73d8-f462-4d3c-99cb-3bb67bf9a92d)
![Ekran görüntüsü 2024-12-23 170435](https://github.com/user-attachments/assets/5ef1676e-60cb-440b-ba4b-d2fa83eeb3a9)
![Ekran görüntüsü 2024-12-23 170540](https://github.com/user-attachments/assets/72ff82fa-33ba-4940-9524-e07c97cfc1c8)
![Ekran görüntüsü 2024-12-23 173006](https://github.com/user-attachments/assets/a7d0d4d1-febd-441a-b9c7-66ff9b63ae55)
![Ekran görüntüsü 2024-12-23 180823](https://github.com/user-attachments/assets/7446a712-4bee-49b4-ab18-f57355b17948)
![Ekran görüntüsü 2024-12-23 180838](https://github.com/user-attachments/assets/6665483d-4e80-46d3-bb02-4da698053739)
![Ekran görüntüsü 2024-12-23 181112](https://github.com/user-attachments/assets/9db20285-7298-4252-91d6-d02677a77b5d)
![Ekran görüntüsü 2024-12-23 181125](https://github.com/user-attachments/assets/2b2261be-b598-4e9e-8bb9-3fbc986d2712)
![Ekran görüntüsü 2024-12-23 181137](https://github.com/user-attachments/assets/ec23499b-cbfc-46a0-973c-4b54264301b4)
![Ekran görüntüsü 2024-12-23 181230](https://github.com/user-attachments/assets/8501ac71-0a9e-4a16-b7c4-93d5ad4812f6)
![Ekran görüntüsü 2024-12-23 181246](https://github.com/user-attachments/assets/046cab3d-f050-4a1e-83a0-d2375e8e2104)
![Ekran görüntüsü 2024-12-23 181301](https://github.com/user-attachments/assets/35eb6ba8-31ee-4463-9e62-64bc8e5d961f)

### Client Credential(istemci kimlik bilgileri), Refresh Token(yenileme jetonu) ve GitHub OAuth2 Sign On
![Ekran görüntüsü 2024-12-23 154949](https://github.com/user-attachments/assets/95e49c34-2913-474f-a85b-634ca2cdb502)
![Ekran görüntüsü 2024-12-23 155134](https://github.com/user-attachments/assets/8e725c6a-7544-4842-ac4b-ee0851a80bf0)
![Ekran görüntüsü 2024-12-23 155213](https://github.com/user-attachments/assets/43946864-380e-4eb4-b350-48716aa66317)
![Ekran görüntüsü 2024-12-23 155230](https://github.com/user-attachments/assets/eee05e4c-922b-467e-a3ee-a1debde118b3)
![Ekran görüntüsü 2024-12-23 155252](https://github.com/user-attachments/assets/0cf3857f-2f94-4494-8946-888641f85c17)
![Ekran görüntüsü 2024-12-23 155312](https://github.com/user-attachments/assets/32db58be-d814-472d-a69c-620a1cd7e36b)
![Ekran görüntüsü 2024-12-23 155335](https://github.com/user-attachments/assets/61cf58d8-8a9c-488f-b36f-ee18ed05c736)
![Ekran görüntüsü 2024-12-23 155344](https://github.com/user-attachments/assets/161527f1-8c25-431c-ac83-7498f9bf3b52)
![Ekran görüntüsü 2024-12-23 155410](https://github.com/user-attachments/assets/cad9a53d-bf47-458e-95bd-ecf9ec9f5ad5)
![Ekran görüntüsü 2024-12-23 155430](https://github.com/user-attachments/assets/eb38b237-e7d4-45f3-9034-8276d5980657)
![Ekran görüntüsü 2024-12-23 155444](https://github.com/user-attachments/assets/445dbb5a-d665-4834-9f8f-288c5c67148c)
![Ekran görüntüsü 2024-12-23 155458](https://github.com/user-attachments/assets/40049cae-faa0-4a95-bdbe-3e04ee285d29)
![Ekran görüntüsü 2024-12-23 155625](https://github.com/user-attachments/assets/9d1d7269-d9f8-46fc-97d0-95f863b31693)
![Ekran görüntüsü 2024-12-23 155646](https://github.com/user-attachments/assets/4b9b133b-0224-4034-a03a-888c8cdf2f89)
![Ekran görüntüsü 2024-12-23 155702](https://github.com/user-attachments/assets/27a64ec6-846a-4943-93bb-35be508b4c79)
![Ekran görüntüsü 2024-12-23 155739](https://github.com/user-attachments/assets/8a82d1b9-3fb2-489d-8f4f-eaa4e5277ee9)
![Ekran görüntüsü 2024-12-23 160109](https://github.com/user-attachments/assets/f2e85dfa-37a9-4bbe-a098-ec5b66894691)

![refresh_token1](https://github.com/user-attachments/assets/48217bbf-5f73-441e-beb0-04563e87c76d)
![refresh_token2](https://github.com/user-attachments/assets/af1d0f68-4bc9-4a8e-bf05-2f908d0644aa)
![refresh_token3](https://github.com/user-attachments/assets/cfee7d53-647a-4ad3-ad7d-ed2555ef4b21)
![refresh_token4](https://github.com/user-attachments/assets/1c485787-2d27-4c8e-a373-e591d363cef1)
![refresh_token5](https://github.com/user-attachments/assets/af60e49b-f685-4241-817a-589b97272ec8)
![refresh_token7](https://github.com/user-attachments/assets/51b823cf-5d98-4300-9f7c-165cc57e1884)

![github-sign-on1](https://github.com/user-attachments/assets/2d7c0d50-f0ae-4665-bbbc-b0f1d105aecc)
![github-sign-on2](https://github.com/user-attachments/assets/21bfd5f0-9fcf-4f01-8c46-01e9c28262de)
![github-sign-on3](https://github.com/user-attachments/assets/47c4b497-7e9b-4f94-bf21-99dbd03c4277)
![github-sign-on4](https://github.com/user-attachments/assets/1d9af2cd-4f4a-4f66-80fa-38ddcfc7582d)
![github-sign-on5](https://github.com/user-attachments/assets/8e62112b-7ebb-4886-b2c7-586f01eabf84)
![github-sign-on6](https://github.com/user-attachments/assets/b9bc85f1-5a40-4a5b-a45a-f05654a6b33e)
![github-sign-on7](https://github.com/user-attachments/assets/c59950a7-16cc-456c-9fff-8fbaf9ad71d2)
![github-sign-on8](https://github.com/user-attachments/assets/ab854df4-193a-4d75-802b-ed6f1b6029f2)
![github-sign-on9](https://github.com/user-attachments/assets/05657850-59a5-4d35-8fb0-af53a4dcdc81)
![github-sign-on10](https://github.com/user-attachments/assets/0895f326-a9cf-403f-80d2-e8be1d910399)
![Ekran görüntüsü 2024-12-23 154923](https://github.com/user-attachments/assets/75e7c9c1-44b9-4cbd-91f9-97fd0a7bbfb5)

