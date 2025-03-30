package howmuchcar.domain.entity;

import howmuchcar.domain.common.DateBaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Room extends DateBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="roomId")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user1Id")
    private User user1;

    @ManyToOne
    @JoinColumn(name = "user2Id")
    private User user2;

    @ManyToOne
    @JoinColumn(name = "carId")
    private CarSale carSale;
}
