package whenyourcar.application.converter.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;
import whenyourcar.application.converter.car.CarSaleConverter;
import whenyourcar.application.dto.chat.common.ChatCommonResponse;
import whenyourcar.domain.entity.CarSale;
import whenyourcar.domain.entity.Room;
import whenyourcar.domain.entity.User;
import whenyourcar.domain.query.chat.SearchRoomInfoQuery;
import whenyourcar.domain.query.chat.SearchRoomsQuery;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ChatCommonConverter {
    private final CarSaleConverter carSaleConverter;
    public ChatCommonResponse.ChatRoomInfoResponseDto toChatRoomResponse(SearchRoomInfoQuery searchRoomInfoQuery) {
        return ChatCommonResponse.ChatRoomInfoResponseDto.builder()
                .roomId(searchRoomInfoQuery.getRoomId())
                .user2Id(searchRoomInfoQuery.getUser2Id())
                .picture(searchRoomInfoQuery.getPicture())
                .name(searchRoomInfoQuery.getName())
                .carSaleInfoDto(carSaleConverter.toCarSaleInfoDto(searchRoomInfoQuery.getCarSale()))
                .build();
    }
}
