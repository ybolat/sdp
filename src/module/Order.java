package module;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "order")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @Column(name = "total_price")
    private int total_price;

    @ManyToOne
    @JoinColumn(name = "courier_id")
    Courier courier;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
}
