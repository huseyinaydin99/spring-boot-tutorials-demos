# cqrs-design-pattern

### CreateProduct Event / Ürün Ekleme Eventi

```
curl --location 'http://localhost:9191/products' \
--header 'Content-Type: application/json' \
--data '{
    "type": "CreateProduct",
    "product": {
        "name": "Kitap",
        "description": "Önder Teker Spring Boot",
        "price": 999
    }
}'
```
### UpdateProduct Event / Ürün Güncelleme Eventi

```
curl --location --request PUT 'http://localhost:9191/products/1' \
--header 'Content-Type: application/json' \
--data '{
    "type": "UpdateProduct",
    "product": {
        "id": 1,
        "name": "TV",
        "description": "Samsung son model",
        "price": 58000.0
    }
}'
```
