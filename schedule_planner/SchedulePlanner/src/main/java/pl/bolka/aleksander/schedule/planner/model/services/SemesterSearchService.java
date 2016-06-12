package pl.bolka.aleksander.schedule.planner.model.services;

import java.util.List;

import org.springframework.stereotype.Repository;

import pl.bolka.aleksander.schedule.planner.model.dto.SemesterDTO;

public interface SemesterSearchService {
	List<SemesterDTO> findBySearchTerm(Long id);
}
