package app.repos;

import app.models.Operation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends CrudRepository<Operation, Integer> {
    Iterable<Operation> findOperationsByBalanceIdOrderByCreateDate(int id);
    Iterable<Operation> findOperationsByArticleIdOrderByCreateDate(int id);
    Iterable<Operation> findOperationsByDebitGreaterThanEqualOrCreditGreaterThanEqual(int val1, int val2);
}
