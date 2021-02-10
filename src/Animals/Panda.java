package Animals;

public class Panda extends Animal{

    public static String animalTypePlural = "PANDAS";
    public static String animalTypeSingular = "PANDA";

    private static boolean eatsMeat = false;
    private static boolean eatsFish = false;
    private static boolean eatsSalad = true;

    private static int maxNewbornBabies = 1;

    public Panda(String gender, String name) {
        super(name, gender, eatsFish, eatsMeat, eatsSalad, animalTypeSingular, animalTypePlural, maxNewbornBabies);
    }
}
