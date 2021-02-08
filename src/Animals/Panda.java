package Animals;

public class Panda extends Animal{

    public static String animalTypePlural = "EAGLES";
    public static String animalTypeSingular = "EAGLE";

    private static boolean eatsMeat = false;
    private static boolean eatsFish = false;
    private static boolean eatsSalad = true;

    private static int kiloOfFoodNeededFor10to30PercentHealth = 1;
    private static int maxNewbornBabies = 1;

    public Panda(String gender, String name) {
        super(name, gender, eatsFish, eatsMeat, eatsSalad, animalTypeSingular, animalTypePlural, kiloOfFoodNeededFor10to30PercentHealth, maxNewbornBabies);
    }
}
