package pl.bolka.aleksander.schedule.planner.model.entity;

public enum SubjectType {

    LABOLATORIUM("Labolatorium", "lab"), PROJEKT("Projekt", "proj"), CWICZENIA("Ćwiczenia", "cwicz"), WYKLAD("Wykład",
            "wyk"), SEMINARIUM("Seminarium", "sem");

    private String name;

    private String shortcut;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortcut() {
        return shortcut;
    }

    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }

    private SubjectType(String name, String shortcut) {
        this.name = name;
        this.shortcut = shortcut;
    }

}
