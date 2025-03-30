package howmuchcar.domain.entity;

import howmuchcar.domain.common.DateBaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Chat extends DateBaseEntity {
    @Id
    @Column(name="chatId")
    private String id;

    @ManyToOne
    @JoinColumn(name = "roomId") // user1을 참조하는 외래키 컬럼
    private Room room;

    @ManyToOne
    @JoinColumn(name = "senderId") // user1을 참조하는 외래키 컬럼
    private User user;

    @Column(nullable = false)
    private String message;
}
