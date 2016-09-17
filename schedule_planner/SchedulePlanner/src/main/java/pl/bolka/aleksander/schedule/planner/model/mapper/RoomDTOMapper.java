package pl.bolka.aleksander.schedule.planner.model.mapper;

import org.springframework.stereotype.Service;
import pl.bolka.aleksander.schedule.planner.model.dto.RoomDTO;
import pl.bolka.aleksander.schedule.planner.model.entity.FreeRoom;

import java.util.HashSet;

/**
 * Created by Aleksander Bołka on 2016-06-26.
 */
//TODO mapper do Week

@Service
public class RoomDTOMapper extends GenericDTOMapper<FreeRoom, RoomDTO>{

    @Override
    protected RoomDTO translateToDTO(FreeRoom entity) {
        RoomDTO dto = new RoomDTO(entity.getId());
        dto.setNumber(entity.getNumber());
        dto.setRoomSpace(entity.getRoomSpace());
        dto.setWeek(new HashSet<>());
        return dto;
    }

    @Override
    protected FreeRoom translateToEntity(RoomDTO dto) {
        FreeRoom freeRoom = new FreeRoom();
        freeRoom.setId(dto.getId());
        freeRoom.setRoomSpace(dto.getRoomSpace());
        freeRoom.setNumber(dto.getNumber());
//        freeRoom.setWeek(new HashSet<>());
        return freeRoom;
    }
}
