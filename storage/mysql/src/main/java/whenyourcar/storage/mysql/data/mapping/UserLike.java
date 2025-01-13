package whenyourcar.storage.mysql.data.mapping;

import jakarta.persistence.*;
import lombok.*;
import whenyourcar.storage.mysql.data.common.DateBaseEntity;
import whenyourcar.storage.mysql.data.entity.Car;
import whenyourcar.storage.mysql.data.entity.User;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserLike extends DateBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userLikeId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

}
