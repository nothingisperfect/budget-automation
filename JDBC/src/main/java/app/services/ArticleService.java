package app.services;

import app.models.Article;
import app.repos.ArticleRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public void create(Article article) {
        articleRepository.save(article);
    }

    public Iterable<Article> readAll() {
        return articleRepository.findAll();
    }

    public Article read(int id) {
        return articleRepository.findById(id).orElse(null);
    }

    public boolean update(Article article, int id) {
        if (articleRepository.existsById(id)) {
            article.setId(id);
            articleRepository.save(article);
            return true;
        }
        return false;
    }

    public boolean delete(int id) {
        if (articleRepository.existsById(id)) {
            articleRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Iterable<Article> searchByName(String name) {
        return articleRepository.findArticleByNameStartsWithIgnoreCase(name);
    }
}
