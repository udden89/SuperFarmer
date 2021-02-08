package Animals;

public class Wolf extends Animal{

    public static String animalTypePlural = "WOLVES";
    public static String animalTypeSingular = "WOLF";

    private static boolean eatsMeat = true;
    private static boolean eatsFish = true;
    private static boolean eatsSalad = false;

    private static int kiloOfFoodNeededFor10to30PercentHealth = 1;
    private static int maxNewbornBabies = 5;

    public Wolf(String gender, String name) {
        super(name, gender, eatsFish, eatsMeat, eatsSalad, animalTypeSingular, animalTypePlural, kiloOfFoodNeededFor10to30PercentHealth, maxNewbornBabies);
    }
}
