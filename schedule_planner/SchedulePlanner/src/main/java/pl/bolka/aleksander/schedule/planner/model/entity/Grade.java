package pl.bolka.aleksander.schedule.planner.model.entity;

public enum Grade {

	INZYNIER("Inżynierskie"), MAGISTER("Magisterskie"), LICENCJAT("Licencjackie"), DOKTOR("Dokotoranckie");

	private final String name;

	public String getName() {
		return name;
	}

	Grade(String name) {
		this.name = name;
	}

}
