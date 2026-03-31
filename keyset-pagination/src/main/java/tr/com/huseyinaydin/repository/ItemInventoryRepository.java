package tr.com.huseyinaydin.repository;

import tr.com.huseyinaydin.entity.Item;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot
 *
 */

public interface ItemInventoryRepository extends JpaRepository<Item, Long> {
    @Query("""
        SELECT p FROM Item p
        WHERE (:cursor IS NULL OR p.id > :cursor)
        ORDER BY p.id ASC
    """)

//    BABADAN KALMA SQL KARŞILIĞI
//    SELECT *
//    FROM product
//    WHERE id > 5
//    ORDER BY id ASC
//    LIMIT 5;

    List<Item> fetchNextPage(@Param("cursor") Long cursor, Pageable pageable);
}