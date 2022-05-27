package app.controllers;

import app.models.Balance;
import app.models.Operation;
import app.services.BalanceService;

import app.services.OperationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BalanceController {
    private final BalanceService balanceService;
    private final OperationService operationService;

    public BalanceController(BalanceService balanceService, OperationService operationService) {
        this.balanceService = balanceService;
        this.operationService = operationService;
    }

    @PostMapping(value = "/balances")
    public ResponseEntity<?> create(@RequestBody Balance balance) {
        balanceService.create(balance);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/balances")
    public ResponseEntity<Iterable<Balance>> read() {
        final Iterable<Balance> balances = balanceService.readAll();

        return balances != null
                ? new ResponseEntity<>(balances, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/balances/{id}")
    public ResponseEntity<Balance> read(@PathVariable(name = "id") int id) {
        final Balance balance = balanceService.read(id);

        return  balance != null
                ? new ResponseEntity<>(balance, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/balances/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Balance balance) {
        final boolean updated = balanceService.update(balance, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/balances/{id}/operations")
    public ResponseEntity<Iterable<Operation>> getOperations(@PathVariable(name = "id") int id) {
        final Iterable<Operation> operations = operationService.findByBalanceId(id);

        return operations != null
                ? new ResponseEntity<>(operations, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
