package animals;

import java.io.Serializable;

public class Bear extends Animal implements Serializable {

    public static String animalTypePlural = "BEARS";
    public static String animalTypeSingular = "BEAR";

    private static boolean eatsMeat = false;
    private static boolean eatsFish = true;
    private static boolean eatsSalad = true;

    private static int maxNewbornBabies = 1;

    public Bear(String gender, String name) {
        super(gender, name, eatsFish, eatsMeat, eatsSalad, animalTypeSingular, animalTypePlural, maxNewbornBabies);
    }

}
