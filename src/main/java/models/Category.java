package models;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "tbl_category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 255, nullable = false)
    private String title;

    @OneToMany(mappedBy = "category")
    private Set<Commodity> commodity;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category() {}

    public Category(int id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public String toString() {
        return " Id: " + this.getId() + " | Title: " + this.getTitle();
    }
}
