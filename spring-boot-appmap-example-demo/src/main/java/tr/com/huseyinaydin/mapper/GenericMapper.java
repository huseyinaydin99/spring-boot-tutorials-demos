package tr.com.huseyinaydin.mapper;

import java.util.List;

//بسم الله الرحمن الرحيم

/**
 * 
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 * 
 */

public interface GenericMapper<E, D> {
	E asEntity(D dto);
	D asDTO(E entity);
	List<E> asEntityList(List<D> dtoList);
	List<D> asDTOList(List<E> entityList);
}