package animals;

public class Eagle extends Animal{

    public static String animalTypePlural = "EAGLES";
    public static String animalTypeSingular = "EAGLE";

    private static boolean eatsMeat = true;
    private static boolean eatsFish = true;
    private static boolean eatsSalad = false;

    private static int maxNewbornBabies = 3;

    public Eagle(String gender, String name) {
        super(gender, name, eatsFish, eatsMeat, eatsSalad, animalTypeSingular, animalTypePlural, maxNewbornBabies);
    }
}
