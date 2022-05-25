package app.repos;

import app.models.OperationSummary;
import app.models.Operation;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface OperationRepository extends CrudRepository<Operation, Integer> {
////    List<Operation> readAll();
//    Operation readOne(int id);
////    void deleteOne(int id);
//    OperationSummary getSummary(Timestamp date1, Timestamp date2);
//    List<Operation> findByArticleName(String name);
}
