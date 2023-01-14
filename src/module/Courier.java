package module;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "courier")
@Data
public class Courier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @Column(name = "phone")
    private String phone;
}
