package Animals;

public class Bear extends Animal{

    public static String animalTypePlural = "BEARS";
    public static String animalTypeSingular = "BEAR";

    private static boolean eatsMeat = false;
    private static boolean eatsFish = true;
    private static boolean eatsSalad = true;

    private static int kiloOfFoodNeededFor10to30PercentHealth = 2;
    private static int maxNewbornBabies = 1;

    public Bear(String gender, String name) {
        super(name, gender, eatsFish, eatsMeat, eatsSalad, animalTypeSingular, animalTypePlural, kiloOfFoodNeededFor10to30PercentHealth, maxNewbornBabies);
    }

}
