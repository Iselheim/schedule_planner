package pl.bolka.aleksander.schedule.planner.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.bolka.aleksander.schedule.planner.model.dto.SemesterDTO;
import pl.bolka.aleksander.schedule.planner.model.dto.mapper.SemesterMapper;
import pl.bolka.aleksander.schedule.planner.model.entity.Semester;
import pl.bolka.aleksander.schedule.planner.model.repository.SemesterRepository;
import pl.bolka.aleksander.schedule.planner.model.specyfication.SemesterSpecyfication;

@Service
public class RepositorySemesterSearchService implements SemesterSearchService {

	@Autowired
	@Qualifier("semesterRepositoryImpl")
	private SemesterRepository semesterRepository;
	 
//    @Autowired
//    public RepositorySemesterSearchService(SemesterRepository semesterRepository) {
//        this.semesterRepository = semesterRepository;
//    }
	
	@Transactional("jpaTransactionManager")
	@Override
	public List<SemesterDTO> findBySearchTerm(Long id) {
    	Specification<Semester> searchSpec = SemesterSpecyfication.containsId(id);
    	List<Semester> searchresult = semesterRepository.findAll(searchSpec);
		return SemesterMapper.mapEntitiesIntoDTOs(searchresult);
	}

	public SemesterRepository getSemesterRepository() {
		return semesterRepository;
	}

	public void setSemesterRepository(SemesterRepository semesterRepository) {
		this.semesterRepository = semesterRepository;
	}
    
    

}
