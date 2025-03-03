package whenyourcar.domain.repository.chat;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import whenyourcar.domain.entity.Room;
import whenyourcar.domain.entity.User;
import whenyourcar.domain.query.chat.SearchRoomInfoQuery;
import whenyourcar.domain.query.chat.SearchRoomUsersQuery;
import whenyourcar.domain.query.chat.SearchRoomsQuery;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<Room, Long> {
    @Query("select new whenyourcar.domain.query.chat.SearchRoomsQuery(" +
            "   r.id, " +
            "   case when r.user1.id = :userId then r.user2.id " +
            "        when r.user2.id = :userId then r.user1.id end, " +
            "   case when r.user1.id = :userId then r.user2.name " +
            "        when r.user2.id = :userId then r.user1.name end," +
            "   case when r.user1.id = :userId then r.user2.picture " +
            "        when r.user2.id = :userId then r.user1.picture end) " +
            "from Room r " +
            "where r.user1.id = :userId or r.user2.id = :userId")
    Page<SearchRoomsQuery> findPageRoomsByUserId(Pageable pageable,
                                                 @Param("userId") Long userId);

    @Query("select new whenyourcar.domain.query.chat.SearchRoomInfoQuery(" +
            "   r.id, " +
            "   case when r.user1.id = :userId then r.user2.id " +
            "        when r.user2.id = :userId then r.user1.id end, " +
            "   case when r.user1.id = :userId then r.user2.name " +
            "        when r.user2.id = :userId then r.user1.name end," +
            "   case when r.user1.id = :userId then r.user2.picture " +
            "        when r.user2.id = :userId then r.user1.picture end," +
            "r.carSale) " +
            "from Room r " +
            "where r.id = :roomId and (r.user1.id = :userId or r.user2.id = :userId)")
    SearchRoomInfoQuery findRoomByUserIdAndCarId(@Param("roomId") Long roomId, @Param("userId") Long userId);

    @Query("select r.id  " +
            "from Room r " +
            "where r.user1.id = :userId or r.user2.id = :userId")
    List<Long> findRoomsByUserId(@Param("userId") Long userId);

    @Query("select new whenyourcar.domain.query.chat.SearchRoomUsersQuery(" +
            "r.user1.id, r.user2.id)" +
            "from Room r " +
            "where r.id = :roomId")
    SearchRoomUsersQuery findUsersByRoomId(@Param("roomId") Long roomId);

    Optional<Room> findRoomById(Long roomId);

    @Query("SELECT COUNT(r) > 0 " +
            "FROM Room r " +
            "WHERE r.id = :roomId AND (r.user1.id = :userId OR r.user2.id = :userId)")
    boolean existsRoomByUserId(@Param("roomId") Long roomId, @Param("userId") Long userId);

    @Query("SELECT COUNT(r) > 0 " +
            "FROM Room r " +
            "WHERE (r.user1.id = :user1Id AND r.user2.id = :user2Id " +
            "OR r.user1.id = :user2Id AND r.user2.id = :user1Id)" +
            "AND r.carSale.carId = :carId" )
    boolean existsRoomByUsersAndCarId(@Param("user1Id") Long user1Id, @Param("user2Id") Long user2Id, @Param("carId") Long carId);

    @Query("select " +
            "case when r.user1.id = :userId then r.user1 " +
            "     when r.user2.id = :userId then r.user2 end " +
            "from Room r " +
            "where r.id = :roomId")
    User findOtherUser(@Param("roomId") Long roomId, @Param("userId") Long userId);
}
