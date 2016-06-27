package pl.bolka.aleksander.schedule.planner.model.mapper;

import org.springframework.stereotype.Service;
import pl.bolka.aleksander.schedule.planner.model.dto.RoomDTO;
import pl.bolka.aleksander.schedule.planner.model.entity.Room;

import java.util.HashSet;

/**
 * Created by Aleksander Bołka on 2016-06-26.
 */
//TODO mapper do Week

@Service
public class RoomDTOMapper extends GenericDTOMapper<Room, RoomDTO>{

    @Override
    protected RoomDTO translateToDTO(Room entity) {
        RoomDTO dto = new RoomDTO(entity.getId());
        dto.setNumber(entity.getNumber());
        dto.setRoomSpace(entity.getRoomSpace());
        dto.setWeek(new HashSet<>());
        return dto;
    }

    @Override
    protected Room translateToEntity(RoomDTO dto) {
        Room room = new Room();
        room.setId(dto.getId());
        room.setRoomSpace(dto.getRoomSpace());
        room.setNumber(dto.getNumber());
        room.setWeek(new HashSet<>());
        return room;
    }
}
