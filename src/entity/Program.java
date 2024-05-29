package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Program {
    @Id
    private long progCode;
    private String progName;
    private int credit;
    /*-------------------*/
    @ManyToMany(mappedBy = "programs")
    private Set<Student> students = new HashSet<>();
    /*-------------------*/

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
