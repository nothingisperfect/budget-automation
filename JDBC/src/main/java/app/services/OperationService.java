package app.services;

import app.models.Article;
import app.models.Operation;
import app.repos.OperationRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class OperationService {
    @Autowired
    private OperationRepository operationRepository;

    public void create(Operation operation) {
        operationRepository.create(operation);
    }

    public List<Operation> readAll() {
        return operationRepository.readAll();
    }

    public Operation read(int id) {
        return operationRepository.readOne(id);
    }

    public boolean update(Operation operation, int id) {
        if (operationRepository.exists(id)) {
            operation.setId(id);
            operationRepository.update(operation);
            return true;
        }
        return false;
    }

    public boolean delete(int id) {
        if (operationRepository.exists(id)) {
            operationRepository.deleteOne(id);
            return true;
        }
        return false;
    }

    public List<Operation> findByArticleName(String name) {
        return operationRepository.findByArticleName(name);
    }
}
