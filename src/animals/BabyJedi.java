package animals;

import java.io.Serializable;

public class BabyJedi extends Animal implements Serializable {

    public static String animalTypePlural = "BABY JEDI'S";
    public static String animalTypeSingular = "BABY JEDI";

    private static boolean eatsMeat = false;
    private static boolean eatsFish = true;
    private static boolean eatsSalad = true;

    private static int maxNewbornBabies = 1;

    public BabyJedi(String gender, String name) {
        super(gender, name, eatsFish, eatsMeat, eatsSalad, animalTypeSingular, animalTypePlural, maxNewbornBabies);
    }
}
