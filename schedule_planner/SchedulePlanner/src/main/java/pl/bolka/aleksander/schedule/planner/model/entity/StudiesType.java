package pl.bolka.aleksander.schedule.planner.model.entity;

public enum StudiesType {

	DZIENNE("Dzienne"), ZAOCZNE("Zaoczne");

	private final String name;

	public String getName() {
		return name;
	}

	private StudiesType(String name) {
		this.name = name;
	}

}
