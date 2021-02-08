package Animals;

public class BabyJedi extends Animal{

    public static String animalTypePlural = "BABY JEDI'S";
    public static String animalTypeSingular = "BABY JEDI";

    private static boolean eatsMeat = false;
    private static boolean eatsFish = true;
    private static boolean eatsSalad = true;

    private static int kiloOfFoodNeededFor10to30PercentHealth = 1;
    private static int maxNewbornBabies = 2;

    public BabyJedi(String name, String gender) {
        super(name, gender, eatsFish, eatsMeat, eatsSalad, animalTypeSingular, animalTypePlural, kiloOfFoodNeededFor10to30PercentHealth, maxNewbornBabies);
    }
}
