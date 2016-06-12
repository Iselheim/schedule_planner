package pl.bolka.aleksander.schedule.planner.model.specyfication;

import org.springframework.data.jpa.domain.Specification;

import pl.bolka.aleksander.schedule.planner.model.entity.Semester;

public final class SemesterSpecyfication {
	
		 
	    private SemesterSpecyfication() {}
	 
	    public static Specification<Semester> containsId(Long id) {
	        return (root, query, cb) -> {
	            return cb.equal(root.<Long>get("id"), id);
	        };
	}

	    private static String getContainsLikePattern(String searchTerm) {
	        if (searchTerm == null || searchTerm.isEmpty()) {
	            return "%";
	        }
	        else {
	            return "%" + searchTerm.toLowerCase() + "%";
	        }
	    }
	
}
