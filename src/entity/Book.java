package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Book {
    @Id
    private long bookId;
    private String title;
    /*-----------------*/
    @ManyToOne
    private Student student;
    /*-----------------*/

    public Book() {
    }

    public Book(long bookId, String title) {
        this.bookId = bookId;
        this.title = title;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
