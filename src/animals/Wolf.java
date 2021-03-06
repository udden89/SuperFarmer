package animals;

import java.io.Serializable;

public class Wolf extends Animal implements Serializable {

    public static String animalTypePlural = "WOLVES";
    public static String animalTypeSingular = "WOLF";

    private static boolean eatsMeat = true;
    private static boolean eatsFish = true;
    private static boolean eatsSalad = false;

    private static int maxNewbornBabies = 5;

    public Wolf(String gender, String name) {
        super(gender, name, eatsFish, eatsMeat, eatsSalad, animalTypeSingular, animalTypePlural, maxNewbornBabies);
    }
}
