package whenyourcar.domain.repository.chat;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import whenyourcar.domain.entity.Chat;
import whenyourcar.domain.query.chat.SearchChatsQuery;

public interface ChatRepository extends JpaRepository<Chat, String> {
    @Query("select new whenyourcar.domain.query.chat.SearchChatsQuery(" +
            "c.id, c.message, c.user.id, c.createdAt)" +
            "from Chat c " +
            "where c.room.id = :roomId")
    Page<SearchChatsQuery> findChatByRoomId(Pageable pageable, @Param("roomId") Long roomId);
}
