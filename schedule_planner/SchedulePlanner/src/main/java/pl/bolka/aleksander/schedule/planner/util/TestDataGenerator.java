package pl.bolka.aleksander.schedule.planner.util;

import pl.bolka.aleksander.schedule.planner.model.entity.*;

import java.sql.Time;
import java.util.*;

public class TestDataGenerator {

    private List<Object> data;

    private final int ROOMS_NUMBER = 30;

    public TestDataGenerator() {
        super();
        data = new ArrayList<>();
    }

    public List<Object> addToDatabase() {
        
        generate();
//        Collections.reverse(data);
        return data;

    }

    private void generate() {

        generateRoomsWithDaysAndHours();
        Faculty faculty = generateFaculty();
        Specialization specialization = generateSpecialization();

        List<StudentGroup> studentGroups = generateStudentGroups(specialization);

        List<List<Subject>> subjects = generateSubjects(faculty, studentGroups.size());
//        data.save(przedmiot);

        for (StudentGroup group : studentGroups) {
//            group.setSubject(subjects.get(group.getSemester()));
        }

        for (List<Subject> subject : subjects) {
        	
        }
        
        List<Subject> subjectsToSave = new ArrayList<>();
        subjects.forEach(subjectsToSave::addAll);
        List<Lecturer> lecturers = generateLecturers(faculty, subjectsToSave);
        
        data.add(faculty);
        data.add(specialization);
        data.addAll(studentGroups);
        data.addAll(lecturers);
        data.addAll(subjectsToSave);

    }

    private List<Lecturer> generateLecturers(Faculty faculty, List<Subject> list) {
        List<Lecturer> lecturers = new ArrayList<>();
        for (int i = 0; i < list.size() / 5; i++) {
            Lecturer lecturer = new Lecturer();
//            lecturer.setFaculty(faculty);
            lecturer.setFirstName("Imie" + i);
            lecturer.setLastName("Nazwisko" + i);
            List<Subject> subjects = new ArrayList<>();
            for (int j = i * 5; j < i * 5 + 5; j++) {
                List<Lecturer> lecturers1 = new ArrayList<>();
                lecturers1.add(lecturer);
                list.get(j).setLecturer(lecturers1);
                subjects.add(list.get(j));
            }
            lecturer.setSubject(subjects);
            lecturers.add(lecturer);
        }
        return lecturers;
    }

    private List<List<Subject>> generateSubjects(Faculty faculty, int uniqueSubjectsGroups) {
        List<List<Subject>> subjects = new ArrayList<>();
        for (int i = 1; i < uniqueSubjectsGroups; i++) {
            List<Subject> groupSubjects = new ArrayList<>();
            for (int subjectsCount = 0; subjectsCount < random(5, 10); subjectsCount++) {
                Subject subject = generateSubject(faculty, i, subjectsCount);
                groupSubjects.add(subject);
            }
            subjects.add(groupSubjects);
        }
        return subjects;
    }

    private Subject generateSubject(Faculty faculty, int i, int subjectsCount) {
        Subject subject = new Subject();
        subject.setFaculty(faculty);
        subject.setName("Przedmiot" + i + "_" + subjectsCount);
        subject.setSubjectType(getRandomSubjectType());
        return subject;
    }

    private List<StudentGroup> generateStudentGroups(Specialization kierunek) {
        List<StudentGroup> studentGroups = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            studentGroups.addAll(generateStudentGroups(kierunek, random(1, 5), i));
        }
        return studentGroups;
    }

    private List<StudentGroup> generateStudentGroups(Specialization specialization, int groupNumber, int semester) {
        List<StudentGroup> studentGroups = new ArrayList<>();
        for (int i = 0; i < groupNumber; i++) {
            StudentGroup grupa = new StudentGroup();
            grupa.setNumber(i);
            grupa.setQuantity(random(15, 20));
//            grupa.setSemester(semester);
            grupa.setSpecialization(specialization);
            studentGroups.add(grupa);
        }
        return studentGroups;
    }

    private Specialization generateSpecialization(Faculty faculty) {
        Specialization specialization = generateSpecialization();
        specialization.setFaculty(faculty);
        return specialization;
    }

    private Specialization generateSpecialization() {
        Specialization specialization = new Specialization();
        specialization.setSemesterCount(7);
        specialization.setName("kierunek testowy");
        specialization.setShortcut("TIN");
        specialization.setGrade(Grade.INZYNIER);
        specialization.setType(StudiesType.DZIENNE);
        return specialization;
    }

    private Faculty generateFaculty() {
        Faculty wydzial = new Faculty();
        wydzial.setName("Wydział‚ testowy");
        wydzial.setShortcut("skrot wydzialu");
        return wydzial;
    }

    private void generateRoomsWithDaysAndHours() {
//        Set<Day> dzienList = addHoursToDays();
//        generateRooms(dzienList);
    }

//    private Set<Day> addHoursToDays() {
//        Set<Hour> godzinas = generateHours();
////        Set<Day> dzienList = generateDays(godzinas);
////        return dzienList;
//    }

    private void generateRooms(Set<Day> dzienList) {
        data.addAll(addDaysToRooms(dzienList));
    }

    private List<FreeRoom> addDaysToRooms(Set<Day> dzienList) {
        List<FreeRoom> freeRooms = new ArrayList<>();
        for (int i = 0; i < ROOMS_NUMBER; i++) {
            FreeRoom room = new FreeRoom();
            room.setRoomSpace(random(15, 20));
//            room.setNumber(i);
//            room.setDay(dzienList);
            freeRooms.add(room);
        }
        return freeRooms;
    }

//    private Set<Day> generateDays(Set<Hour> godzinas) {
//        Day dzien = new Day();
//        dzien.setName("Poniedziałek");
//        dzien.setHour(godzinas);
//        Day dzien2 = new Day();
//        dzien2.setName("Wtorek");
//        dzien2.setHour(godzinas);
//        Day dzien3 = new Day();
//        dzien3.setName("Środa");
//        dzien3.setHour(godzinas);
//        Day dzien4 = new Day();
//        dzien4.setName("Czwartek");
//        dzien4.setHour(godzinas);
//        Day dzien5 = new Day();
//        dzien5.setName("Piątek");
//        dzien5.setHour(godzinas);
//        Set<Day> dzienList = new LinkedHashSet<>();
//        dzienList.save(dzien);
//        dzienList.save(dzien2);
//        dzienList.save(dzien3);
//        dzienList.save(dzien4);
//        dzienList.save(dzien5);
//        for (Day dzien8 : dzienList) {
//            data.save(dzien8);
//        }
//        return dzienList;
//    }

    private Set<Hour> generateHours() {
        Set<Hour> godzinas = new LinkedHashSet<>();
        for (int i = 0; i < 13; i++) {
            @SuppressWarnings("deprecation")
            Time time = new Time(7 + i, 15, 0);
            @SuppressWarnings("deprecation")
            Time time2 = new Time(7 + i + 1, 15, 0);
            Hour godzina = new Hour();
            godzina.setTimeFrom(time);
            godzina.setTimeTo(time2);
            data.add(godzina);
            godzinas.add(godzina);
        }
        return godzinas;
    }

    private int random(int min, int max) {
        Random random = new Random();
        int nextInt = random.nextInt(max - min);
        return nextInt + min + 1;
    }

    private SubjectType getRandomSubjectType() {
        switch (random(0, 4)) {
            case 0:
                return SubjectType.CWICZENIA;
            case 1:
                return SubjectType.LABOLATORIUM;
            case 2:
                return SubjectType.PROJEKT;
            case 3:
                return SubjectType.SEMINARIUM;
            case 4:
                return SubjectType.WYKLAD;
            default:
                return SubjectType.CWICZENIA;
        }
    }

}
