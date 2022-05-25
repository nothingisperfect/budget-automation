package app.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "operations")
public class Operation {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "articleId")
    private Integer articleId;
    @Column(name = "debit", nullable = false)
    private Integer debit;
    @Column(name = "credit", nullable = false)
    private Integer credit;
    @Column(name = "createDate", nullable = false)
    private Timestamp createDate;
    @Column(name = "balanceId")
    private Integer balanceId;

    @ManyToOne
//    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE
//            , org.hibernate.annotations.CascadeType.MERGE
//            , org.hibernate.annotations.CascadeType.PERSIST})
    @JoinColumn(name = "articleId", insertable = false, updatable = false)
    @JsonIgnore
    private Article article;
    @ManyToOne
//    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE
//            , org.hibernate.annotations.CascadeType.MERGE
//            , org.hibernate.annotations.CascadeType.PERSIST})
    @JoinColumn(name = "balanceId", insertable = false, updatable = false)
    @JsonIgnore
    private Balance balance;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Balance getBalance() {
        return balance;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }

    public Integer getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(Integer balanceId) {
        this.balanceId = balanceId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }
}
