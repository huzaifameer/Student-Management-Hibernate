package entity;

import javax.persistence.Entity;

@Entity
public class Program {
    private long progCode;
    private String progName;
    private int credit;

    public Program() {
    }

    public Program(long progCode, String progName, int credit) {
        this.progCode = progCode;
        this.progName = progName;
        this.credit = credit;
    }

    public long getProgCode() {
        return progCode;
    }

    public void setProgCode(long progCode) {
        this.progCode = progCode;
    }

    public String getProgName() {
        return progName;
    }

    public void setProgName(String progName) {
        this.progName = progName;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}
