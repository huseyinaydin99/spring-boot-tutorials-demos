#!/bin/sh
# keycloak-setup.sh
# Keycloak otomatik yapılandırma betiği

echo "========================================="
echo "Keycloak Setup Basliyor"
echo "========================================="

# Keycloak'in hazır olmasını bekle
sleep 5

# Admin token al
echo "Admin token aliniyor..."
TOKEN_RESPONSE=$(curl -s -X POST "http://keycloak:8080/realms/master/protocol/openid-connect/token" \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "client_id=admin-cli&username=admin&password=admin&grant_type=password")

ACCESS_TOKEN=$(echo $TOKEN_RESPONSE | grep -o '"access_token":"[^"]*' | cut -d'"' -f4)

if [ -z "$ACCESS_TOKEN" ]; then
  echo "HATA: Token alinamadi!"
  exit 1
fi
echo "Token basariyla alindi"

# Realm kontrol et ve oluştur
echo "Realm kontrol ediliyor..."
REALM_CHECK=$(curl -s -o /dev/null -w "%{http_code}" -X GET "http://keycloak:8080/admin/realms/huseyinaydin99" \
  -H "Authorization: Bearer $ACCESS_TOKEN")

if [ "$REALM_CHECK" = "404" ]; then
  echo "Realm olusturuluyor..."
  curl -s -X POST "http://keycloak:8080/admin/realms" \
    -H "Authorization: Bearer $ACCESS_TOKEN" \
    -H "Content-Type: application/json" \
    -d '{
      "id": "huseyinaydin99",
      "realm": "huseyinaydin99",
      "enabled": true
    }'
  echo "Realm olusturuldu"
else
  echo "Realm zaten mevcut"
fi

# Kullanıcı kontrol et ve oluştur
echo "Kullanici kontrol ediliyor..."
USER_CHECK=$(curl -s -X GET "http://keycloak:8080/admin/realms/huseyinaydin99/users?username=admin" \
  -H "Authorization: Bearer $ACCESS_TOKEN")

if [ "$USER_CHECK" = "[]" ]; then
  echo "Kullanici olusturuluyor..."
  curl -s -X POST "http://keycloak:8080/admin/realms/huseyinaydin99/users" \
    -H "Authorization: Bearer $ACCESS_TOKEN" \
    -H "Content-Type: application/json" \
    -d '{
      "username": "admin",
      "enabled": true,
      "emailVerified": true,
      "email": "admin@test.com",
      "firstName": "Admin",
      "lastName": "User",
      "credentials": [{
        "type": "password",
        "value": "admin",
        "temporary": false
      }],
      "requiredActions": []
    }'
  echo "Kullanici olusturuldu"
else
  echo "Kullanici zaten mevcut, guncelleniyor..."
  USER_ID=$(echo $USER_CHECK | grep -o '"id":"[^"]*' | cut -d'"' -f4)

  # Kullaniciyi guncelle
  curl -s -X PUT "http://keycloak:8080/admin/realms/huseyinaydin99/users/$USER_ID" \
    -H "Authorization: Bearer $ACCESS_TOKEN" \
    -H "Content-Type: application/json" \
    -d '{
      "emailVerified": true,
      "requiredActions": [],
      "enabled": true
    }'

  # Sifreyi yeniden set et
  curl -s -X PUT "http://keycloak:8080/admin/realms/huseyinaydin99/users/$USER_ID/reset-password" \
    -H "Authorization: Bearer $ACCESS_TOKEN" \
    -H "Content-Type: application/json" \
    -d '{
      "type": "password",
      "value": "admin",
      "temporary": false
    }'
  echo "Kullanici guncellendi"
fi

# Client kontrol et ve oluştur
echo "Client kontrol ediliyor..."
CLIENT_CHECK=$(curl -s -X GET "http://keycloak:8080/admin/realms/huseyinaydin99/clients?clientId=spring-boot-keycloak" \
  -H "Authorization: Bearer $ACCESS_TOKEN")

if [ "$CLIENT_CHECK" = "[]" ]; then
  echo "Client olusturuluyor..."
  curl -s -X POST "http://keycloak:8080/admin/realms/huseyinaydin99/clients" \
    -H "Authorization: Bearer $ACCESS_TOKEN" \
    -H "Content-Type: application/json" \
    -d '{
      "clientId": "spring-boot-keycloak",
      "name": "Spring Boot Keycloak Client",
      "enabled": true,
      "publicClient": true,
      "directAccessGrantsEnabled": true,
      "standardFlowEnabled": true,
      "protocol": "openid-connect",
      "redirectUris": ["http://localhost:9090/*", "http://localhost:8080/*"],
      "webOrigins": ["*"]
    }'
  echo "Client olusturuldu"
else
  echo "Client zaten mevcut"
fi

# Test et
echo ""
echo "========================================="
echo "Token test ediliyor..."
echo "========================================="

TOKEN_TEST=$(curl -s -X POST "http://keycloak:8080/realms/huseyinaydin99/protocol/openid-connect/token" \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "client_id=spring-boot-keycloak&username=admin&password=admin&grant_type=password")

if echo "$TOKEN_TEST" | grep -q "access_token"; then
  echo "✅ BASARILI! Token alindi."
  echo "$TOKEN_TEST" | grep -o '"access_token":"[^"]*' | cut -d'"' -f4 | cut -c1-50
  echo "..."
else
  echo "❌ HATA: Token alinamadi!"
  echo "$TOKEN_TEST"
  exit 1
fi

echo ""
echo "========================================="
echo "Keycloak kurulumu tamamlandi!"
echo "========================================="
echo "Admin Console: http://localhost:8180/admin"
echo "Kullanici: admin / admin"
echo "Realm: huseyinaydin99"
echo "Client: spring-boot-keycloak"