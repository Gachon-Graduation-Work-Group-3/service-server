package howmuchcar.infra.persistence.chat

import howmuchcar.domain.entity.Room
import howmuchcar.domain.query.chat.SearchRoomInfoQuery
import howmuchcar.domain.query.chat.SearchRoomUsersQuery
import howmuchcar.domain.query.chat.SearchRoomsQuery
import howmuchcar.domain.query.user.SearchOtherUserQuery
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface ChatRoomJpaRepository : JpaRepository<Room, Long> {

    @Query("select new howmuchcar.domain.query.chat.SearchRoomsQuery(" +
            "   r.id, " +
            "   case when r.user1.id = :userId then r.user2.id " +
            "        when r.user2.id = :userId then r.user1.id end, " +
            "   case when r.user1.id = :userId then r.user2.name " +
            "        when r.user2.id = :userId then r.user1.name end," +
            "   case when r.user1.id = :userId then r.user2.picture " +
            "        when r.user2.id = :userId then r.user1.picture end) " +
            "from Room r " +
            "where r.user1.id = :userId or r.user2.id = :userId")
    fun findPageRoomsByUserId(pageable: Pageable,
                              @Param("userId") userId: Long): Page<SearchRoomsQuery>

    @Query("select new howmuchcar.domain.query.chat.SearchRoomInfoQuery(" +
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
    fun findRoomCarInfoByUserIdAndRoomId(@Param("roomId") roomId: Long, 
                                          @Param("userId") userId: Long): Optional<SearchRoomInfoQuery>

    @Query("select r.id " +
            "from Room r " +
            "where r.user1.id = :userId or r.user2.id = :userId")
    fun findRoomsByUserId(@Param("userId") userId: Long): List<Long>

    @Query("select new howmuchcar.domain.query.chat.SearchRoomUsersQuery(" +
            "r.user1.id, r.user2.id) " +
            "from Room r " +
            "where r.id = :roomId")
    fun findUsersByRoomId(@Param("roomId") roomId: Long): SearchRoomUsersQuery

    fun findRoomById(roomId: Long): Optional<Room>

    @Query("SELECT COUNT(r) > 0 " +
            "FROM Room r " +
            "WHERE r.id = :roomId AND (r.user1.id = :userId OR r.user2.id = :userId)")
    fun existsRoomByUserId(@Param("roomId") roomId: Long, @Param("userId") userId: Long): Boolean

    @Query("SELECT COUNT(r) > 0 " +
            "FROM Room r " +
            "WHERE (r.user1.id = :user1Id AND r.user2.id = :user2Id " +
            "OR r.user1.id = :user2Id AND r.user2.id = :user1Id) " +
            "AND r.carSale.carId = :carId")
    fun existsRoomByUsersAndCarId(@Param("user1Id") user1Id: Long, 
                                  @Param("user2Id") user2Id: Long, 
                                  @Param("carId") carId: Long): Boolean

    @Query("select new howmuchcar.domain.query.user.SearchOtherUserQuery(" +
            "   case when r.user1.id = :userId then r.user2.name " +
            "        when r.user2.id = :userId then r.user1.name end, " +
            "   case when r.user1.id = :userId then r.user2.picture " +
            "        when r.user2.id = :userId then r.user1.picture end) " +
            "from Room r " +
            "where r.id = :roomId")
    fun findOtherUser(@Param("roomId") roomId: Long, @Param("userId") userId: Long): Optional<SearchOtherUserQuery>
}
