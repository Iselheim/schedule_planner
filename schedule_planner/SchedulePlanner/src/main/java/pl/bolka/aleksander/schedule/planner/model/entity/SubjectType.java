package pl.bolka.aleksander.schedule.planner.model.entity;

import pl.bolka.aleksander.schedule.planner.exceptions.NotExistingTypeException;

import java.util.ArrayList;
import java.util.List;

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

    SubjectType(String name, String shortcut) {
        this.name = name;
        this.shortcut = shortcut;
    }

    public static List<String> getAllAsList() {
        List<String> stringList = new ArrayList<>();
        SubjectType[] values = SubjectType.values();
        for(SubjectType subjectType :values){
            stringList.add(subjectType.getName());
        }
        return stringList;
    }

    public static SubjectType getFromString(String type) throws NotExistingTypeException{
        SubjectType[] values = SubjectType.values();
        for(SubjectType sType : values){
            if(sType.getName().equals(type)){
                return sType;
            }
        }
        throw new NotExistingTypeException("Taki typ przedmiotu nie istnieje!");
    }
}
