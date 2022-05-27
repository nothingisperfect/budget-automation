package app.controllers;

import app.models.Article;
import app.models.Operation;
import app.services.ArticleService;
import app.services.OperationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArticleController {
    private final ArticleService articleService;
    private final OperationService operationService;

    public ArticleController(ArticleService articleService, OperationService operationService) {
        this.articleService = articleService;
        this.operationService = operationService;
    }

    @PostMapping(value = "/articles")
    public ResponseEntity<?> create(@RequestBody Article article) {
        articleService.create(article);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/articles")
    public ResponseEntity<Iterable<Article>> read() {
        final Iterable<Article> articles = articleService.readAll();

        return articles != null
                ? new ResponseEntity<>(articles, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/articles/{id}")
    public ResponseEntity<Article> read(@PathVariable(name = "id") int id) {
        final Article article = articleService.read(id);

        return article != null
                ? new ResponseEntity<>(article, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/articles/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Article article) {
        final boolean updated = articleService.update(article, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/articles/{id}/operations")
        public ResponseEntity<Iterable<Operation>> getOperations(@PathVariable(name = "id") int id) {
            final Iterable<Operation> operations = operationService.findByArtcileId(id);

            return operations != null
                    ? new ResponseEntity<>(operations, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/articles/search")
    public ResponseEntity<Iterable<Article>> search(@RequestParam String name) {
        final Iterable<Article> articles = articleService.searchByName(name);

        return articles != null
                ? new ResponseEntity<>(articles, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
