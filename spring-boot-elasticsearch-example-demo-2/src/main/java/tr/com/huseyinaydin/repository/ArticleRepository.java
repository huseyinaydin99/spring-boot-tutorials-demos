package tr.com.huseyinaydin.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import tr.com.huseyinaydin.model.Article;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

@Repository
public interface ArticleRepository extends ElasticsearchRepository<Article, Long> {
}