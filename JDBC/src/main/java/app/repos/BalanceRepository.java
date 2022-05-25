package app.repos;

import app.models.Balance;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalanceRepository extends CrudRepository<Balance,Integer> {
//    Balance readOne(int id);
////    List<Balance> readAll();
////    void deleteOne(int id);
//    List<Balance> getOverview();
//    Balance getOverviewById(int id);
}
