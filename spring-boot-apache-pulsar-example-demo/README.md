# spring-boot-apache-pulsar


### Apache Pulsar Admin kullanıcısı oluşturmak için :

```
CSRF_TOKEN=$(curl http://localhost:7750/pulsar-manager/csrf-token)

curl \
  -H "X-XSRF-TOKEN: $CSRF_TOKEN" \
  -H "Cookie: XSRF-TOKEN=$CSRF_TOKEN;" \
  -H "Content-Type: application/json" \
  -X PUT http://localhost:7750/pulsar-manager/users/superuser \
  -d '{"name": "admin", "password": "huseyin11", "description": "açıklama1", "email": "huseyin99@test.com.tr"}'
```
