package app.models;

public class ArticleOverview {
    private Integer id;
    private String name;
    private Integer OpCount;
    private Integer TotalDebit;
    private Integer TotalCredit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOpCount() { return OpCount; }

    public void setOpCount(Integer opCount) {
        OpCount = opCount;
    }

    public Integer getTotalDebit() {
        return TotalDebit;
    }

    public void setTotalCredit(Integer totalCredit) {
        TotalCredit = totalCredit;
    }

    public Integer getTotalCredit() {
        return TotalCredit;
    }

    public void setTotalDebit(Integer totalDebit) {
        TotalDebit = totalDebit;
    }
}
