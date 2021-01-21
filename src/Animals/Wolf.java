package Animals;

public class Wolf extends Animal{

    public static String animalTypePlural = "wolf";
    public static String animalTypeSingular = "wolves";

    private static boolean eatsMeat = true;
    private static boolean eatsFish = true;
    private static boolean eatsSalad = false;

    public Wolf(String name, String gender) {
        super(name, gender, eatsFish, eatsMeat, eatsSalad, animalTypeSingular, animalTypePlural);
    }
}
