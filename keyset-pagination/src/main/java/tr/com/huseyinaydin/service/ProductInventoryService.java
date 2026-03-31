package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.dto.CursorPageResponse;
import tr.com.huseyinaydin.entity.Item;
import tr.com.huseyinaydin.repository.ItemInventoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot
 *
 */

//ÖNEMLİ!
/*
Offset pagination, veriyi sayfa sayfa taksim eden kadim bir usûldür;
ben burada derim ki “şu kadarını geç, geriye kalan şu miktarı getir.”
📜 Bu yol zahirde basit, anlaşılır ve her menzile doğrudan ulaşma imkânı verir;
lakin veri kesreti arttıkça nefes daralır, çünkü her seferinde evvelâ sayar, sonra atlar,
ardından murad ettiği satıra vasıl olur. 😮‍💨 Üstelik araya yeni kayıtlar girer yahut bazıları eksilirse,
sayfaların nizamı bozulur, her şey biraz kayar, bu da insana güven vermez ⚖️

Keyset pagination ise daha zarif ve daha seyrüsefer ehli bir yoldur 🧭
Ben burada “en son kaldığım yer neresiyse, oradan devam edeyim” derim;
yani bir nevi iz sürerim. 🔍 Sayfa numarasıyla uğraşmam, doğrudan son kaydın işaretini (cursor) alır ve oradan
ileriye doğru yürürüm. Bu sebeple sürati yüksektir ⚡, yük arttıkça yorulmaz ve verinin akışı daha muntazam olur.
Lakin bu yolun da kendine göre bir çilesi vardır;
istediğin her menzile rastgele sıçrayamazsın, sabırla ilerlemen gerekir.
Yani offset “neredeyim?” diye sorarken, keyset “nerede bıraktım?” diye fısıldar 🌙
*/
//DİKKAT:
/*
Keyset pagination’da araya yeni kayıt girse ya da kayıt silinse de sorun oluşmaz, çünkü sistem sayfa numarasına değil son görülen kaydın (cursor) değerine göre ilerler ve bu da veriyi doğrudan doğru konumdan çekerek kaymaların önüne geçer.
*/

@Service
public class ProductInventoryService {
    private final ItemInventoryRepository repository;

    public ProductInventoryService(ItemInventoryRepository repository) {
        this.repository = repository;
    }

    public Page<Item> getProducts(int offset, int pageSize) {
        return repository
                .findAll(PageRequest.of(offset, pageSize));
    }

    public CursorPageResponse<Item> getProducts(Long cursor, int size) {
        //Varsayılan olarak page değeri 0, size değeri 10’dur ve size 0 ile 9 arasında olabilir.
        Pageable pageable = PageRequest.of(0, size);

        //Sonraki sayfanın kayıtlarını getir.
        List<Item> items = repository.fetchNextPage(cursor, pageable);

        //Daha fazla kayıt olup olmadığını kontrol et.
        boolean hasNext = items.size() == size;

        //Sonraki cursor (işaretçi) değerini belirle.

        Long nextCursor = hasNext
                ? items.get(items.size() - 1).getId()
                : null;

        return new CursorPageResponse<>(
                items,
                size,
                nextCursor,
                hasNext
        );
    }
}