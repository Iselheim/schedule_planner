package pl.bolka.aleksander.schedule.planner.model.services;

import org.springframework.stereotype.Service;
import pl.bolka.aleksander.schedule.planner.model.entity.FreeRoom;
import pl.bolka.aleksander.schedule.planner.model.filter.RoomFilter;
import pl.bolka.aleksander.schedule.planner.model.specyfication.RoomSpecification;

import java.util.List;

/**
 * Created by Aleksander Bołka on 2016-07-01.
 */
@Service
public class RoomRepositoryService extends AbstractRepositoryService<FreeRoom, RoomFilter> {

    @Override
    public List<FreeRoom> findAll(RoomFilter filter) {
        RoomSpecification roomSpecification = new RoomSpecification(filter);
        return commonCustomRepository.findAll(roomSpecification);
    }

    @Override
    public FreeRoom findOne(RoomFilter filter){
        RoomSpecification spec = new RoomSpecification(filter);
        return commonCustomRepository.findOne(spec);
    }
}
