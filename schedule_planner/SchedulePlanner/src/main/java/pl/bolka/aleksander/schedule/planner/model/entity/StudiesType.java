package pl.bolka.aleksander.schedule.planner.model.entity;

import java.util.ArrayList;
import java.util.List;

public enum StudiesType {

	DZIENNE("Dzienne"), ZAOCZNE("Zaoczne");

	private final String name;

    public static List<String> getAllAsList() {
		List<String> stringList = new ArrayList<>();
		StudiesType[] values = StudiesType.values();
		for(StudiesType type :values){
			stringList.add(type.getName());
		}
		return stringList;
    }

    public String getName() {
		return name;
	}

	StudiesType(String name) {
		this.name = name;
	}

}
