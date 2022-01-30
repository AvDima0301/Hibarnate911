package models;

import lombok.Data;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.util.Set;


@Data
@Entity
@Table(name = "tbl_customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column(length = 255, nullable = false)
    private String fullName;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable (
            name = "Customer_Goods",
            joinColumns = { @JoinColumn(name = "customer_id") },
            inverseJoinColumns = { @JoinColumn(name = "goods_id") }
    )
    private Set<Goods> goods;
}
