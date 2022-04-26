package app.models;

import app.repos.OperationRepository;

public class OperationSummary {
    private Integer debit;
    private Integer credit;

    public OperationSummary() {
        debit = 0;
        credit = 0;
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
}
