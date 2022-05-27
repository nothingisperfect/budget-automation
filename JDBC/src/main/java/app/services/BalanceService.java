package app.services;

import app.models.Balance;
import app.repos.BalanceRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;

@Service
public class BalanceService {
    @Autowired
    private BalanceRepository balanceRepository;

    public void create(Balance balance) {
        balance.setCreateDate(new Timestamp(System.currentTimeMillis()));
        balanceRepository.save(balance);
    }

    public Iterable<Balance> readAll() {
        return balanceRepository.findAll();
    }

    public Balance read(int id) {
        return balanceRepository.findById(id).orElse(null);
    }

    public boolean update(Balance balance, int id) {
        if (balanceRepository.existsById(id)) {
            balance.setId(id);
            balanceRepository.save(balance);
            return true;
        }
        return false;
    }

    public boolean delete(int id) {
        if(balanceRepository.existsById(id)) {
            balanceRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
