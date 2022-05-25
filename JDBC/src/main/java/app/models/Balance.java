package app.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "balance")
public class Balance {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "createDate", nullable = false)
    private Timestamp createDate;
    @Column(name = "debit", nullable = false)
    private Integer debit;
    @Column(name = "credit", nullable = false)
    private Integer credit;
    @Column(name = "amount", nullable = false)
    private Integer amount;

    @OneToMany (mappedBy = "balance")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JsonIgnore
    private List<Operation> operations;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Integer getDebit() {
        return debit;
    }

    public void setDebit(Integer debit) {
        this.debit = debit;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

//    public List<Operation> getOperation() {
//        return operations;
//    }
//
//    public void setOperation(List<Operation> operation) {
//        this.operations = operation;
//    }
}
