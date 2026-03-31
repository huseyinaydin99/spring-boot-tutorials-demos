### keyset-pagination vs offset-pagination kim alır?

#### Offset Pagination:
Belirli bir sayfaya gitmek için kaç kayıt atlanacağını (offset) ve kaç kayıt alınacağını (limit/pageSize) söylersin.

##### Mantık:

```sql
SELECT * FROM items
ORDER BY id
LIMIT 10 OFFSET 20;
```

>→ “İlk 20 kaydı atla, sonraki 10 kaydı getir”

---

#### Keyset (Cursor) Pagination:
Sayfa numarası yerine son görülen kaydı referans alarak ilerlersin.

##### Mantık:

SELECT * FROM items
WHERE id > 20
ORDER BY id
LIMIT 10;

>→ “ID’si 20’den büyük olanlardan 10 tane getir”

#### Farklılıkları:

Offset pagination, veriyi sayfa sayfa taksim eden kadim bir usûldür; ben burada derim ki “şu kadarını geç, geriye kalan şu miktarı getir.” 📜 Bu yol zahirde basit, anlaşılır ve her menzile doğrudan ulaşma imkânı verir; lakin veri kesreti arttıkça nefes daralır, çünkü her seferinde evvelâ sayar, sonra atlar, ardından murad ettiği satıra vasıl olur. 😮‍💨 Üstelik araya yeni kayıtlar girer yahut bazıları eksilirse, sayfaların nizamı bozulur, her şey biraz kayar, bu da insana güven vermez ⚖️

Keyset pagination ise daha zarif ve daha seyrüsefer ehli bir yoldur 🧭 Ben burada “en son kaldığım yer neresiyse, oradan devam edeyim” derim; yani bir nevi iz sürerim. 🔍 Sayfa numarasıyla uğraşmam, doğrudan son kaydın işaretini (cursor) alır ve oradan ileriye doğru yürürüm. Bu sebeple sürati yüksektir ⚡, yük arttıkça yorulmaz ve verinin akışı daha muntazam olur. Lakin bu yolun da kendine göre bir çilesi vardır; istediğin her menzile rastgele sıçrayamazsın, sabırla ilerlemen gerekir. Yani offset “neredeyim?” diye sorarken, keyset “nerede bıraktım?” diye fısıldar 🌙

Keyset pagination’da araya yeni kayıt girse ya da kayıt silinse de sorun oluşmaz, çünkü sistem sayfa numarasına değil son görülen kaydın (cursor) değerine göre ilerler ve bu da veriyi doğrudan doğru konumdan çekerek kaymaların önüne geçer.

---

| Kriter          | Offset Pagination 📄 | Keyset Pagination 🧭    |
| --------------- | -------------------- | ----------------------- |
| Mantık          | Satır atla (OFFSET)  | Son kayıttan devam et   |
| Performans      | Büyük veride kötü 🐌 | Büyük veride çok iyi 🚀 |
| Tutarlılık      | Düşük ⚠️             | Yüksek 🔒               |
| Rastgele erişim | Var ✅                | Yok ❌                   |
| Karmaşıklık     | Basit 👍             | Orta ⚙️                 |

---

