package app.repos;

import app.models.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Integer> {
    Iterable<Article> findArticleByNameStartsWithIgnoreCase(String name);
}
