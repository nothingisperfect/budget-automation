package app.services;

import app.models.Operation;
import app.repos.OperationRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class OperationService {
    @Autowired
    private OperationRepository operationRepository;

    public void create(Operation operation) {
        operationRepository.save(operation);
    }

    public Iterable<Operation> readAll() {
        return operationRepository.findAll();
    }

    public Operation read(int id) {
        return operationRepository.findById(id).orElse(null);
    }

    public boolean update(Operation operation, int id) {
        if (operationRepository.existsById(id)) {
            operation.setId(id);
            operationRepository.save(operation);
            return true;
        }
        return false;
    }

    public boolean delete(int id) {
        if (operationRepository.existsById(id)) {
            operationRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Iterable<Operation> findByBalanceId(int id) {
        return operationRepository.findOperationsByBalanceIdOrderByCreateDate(id);
    }

    public Iterable<Operation> findByArtcileId(int id) {
        return operationRepository.findOperationsByArticleIdOrderByCreateDate(id);
    }

    public Iterable<Operation> findMoreThan(int val) {
        return operationRepository.findOperationsByDebitGreaterThanEqualOrCreditGreaterThanEqual(val, val);
    }
}
