package pl.bolka.aleksander.schedule.planner.export.engine;

public enum DayEnum {
    Poniedzialek,
    Wtorek,
    Sroda,
    Czwartek,
    Piatek;

    public static DayEnum getDay(String day) {
        switch (day) {
            case "Poniedziałek":
                return Poniedzialek;
            case "Wtorek":
                return Wtorek;
            case "Środa":
                return Sroda;
            case "Czwartek":
                return Czwartek;
            case "Piątek":
                return Piatek;
            default:
                throw new RuntimeException("Nie obługiwany enum!");
        }
    }
}
