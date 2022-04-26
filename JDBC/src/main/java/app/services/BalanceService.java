package app.services;

import app.models.Balance;
import app.repos.BalanceRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.List;

@Service
public class BalanceService {
    @Autowired
    private BalanceRepository balanceRepository;

    public void create(Balance balance) {
        balance.setCreateDate(new Timestamp(System.currentTimeMillis()));
        balanceRepository.create(balance);
    }

    public List<Balance> readAll() {
        return balanceRepository.readAll();
    }

    public Balance read(int id) {
        return balanceRepository.readOne(id);
    }

    public boolean update(Balance balance, int id) {
        if (balanceRepository.exists(id)) {
            balance.setId(id);
            balanceRepository.update(balance);
            return true;
        }
        return false;
    }

    public boolean delete(int id) {
        if(balanceRepository.exists(id)) {
            balanceRepository.deleteOne(id);
            return true;
        }
        return false;
    }

    public List<Balance> getOverview() {
        return balanceRepository.getOverview();
    }

    public Balance getOverviewById(int id) {
        return balanceRepository.getOverviewById(id);
    }
}
