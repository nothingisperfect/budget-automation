package app.controllers;

import app.models.Article;
import app.models.Operation;
import app.services.OperationService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OperationController {
    private final OperationService operationService;

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @PostMapping(value = "/operations")
    public ResponseEntity<?> create(@RequestBody Operation operation) {
        operationService.create(operation);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/operations")
    public ResponseEntity<List<Operation>> read() {
        final List<Operation> operations = operationService.readAll();

        return operations != null
                ? new ResponseEntity<>(operations, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/operations/{id}")
    public ResponseEntity<Operation> read(@PathVariable(name = "id") int id) {
        final Operation operation = operationService.read(id);

        return  operation != null
                ? new ResponseEntity<>(operation, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/operations/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Operation operation) {
        final boolean updated = operationService.update(operation, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/operations/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = operationService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //TODO: "/operations/find?q={name}"
    @GetMapping(value = "/operations/find/{name}")
    public ResponseEntity<List<Operation>> findByArticleName(@PathVariable(name = "name") String name) {
        final List<Operation> operation = operationService.findByArticleName(name);

        return  operation != null
                ? new ResponseEntity<>(operation, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
