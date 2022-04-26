package app.controllers;

import app.models.Balance;
import app.services.BalanceService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BalanceController {
    private final BalanceService balanceService;

    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @PostMapping(value = "/balances")
    public ResponseEntity<?> create(@RequestBody Balance balance) {
        balanceService.create(balance);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/balances")
    public ResponseEntity<List<Balance>> read() {
        final List<Balance> balances = balanceService.readAll();

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

    @DeleteMapping(value = "/balances/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = balanceService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "balances/calculated")
    public ResponseEntity<List<Balance>> getOverview() {
        final List<Balance> balances = balanceService.getOverview();

        return balances != null
                ? new ResponseEntity<>(balances, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "balances/calculated/{id}")
    public ResponseEntity<Balance> getOverviewById(@PathVariable(name = "id") int id) {
        final Balance balance = balanceService.getOverviewById(id);

        return balance != null
                ? new ResponseEntity<>(balance, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
