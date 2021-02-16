package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RandomGameMode {

    private static List<String> randomNamesList = Arrays.asList(

            //<editor-fold desc="THE NAMES">
            "Banana",
            "Apple Pie",
            "MonkeyDonkie",
            "Star Lover",
            "Superstar",
            "Foxie",
            "The Immortal",
            "Banana Split",
            "Cookie",
            "Daim",
            "Mars",
            "Snickers",
            "Coke",
            "Goofy",
            "The Nerd",
            "The One",
            "The Cool One",
            "The Funny One",
            "The Führer",
            "Gestapo",
            "Master Yoda",
            "Poopie",
            "Iron Maiden",
            "Metallica",
            "Rammstein",
            "Flipper",
            "Trickster",
            "The Smart One"
            //</editor-fold>

    );

    private static ArrayList<String> randomNames = new ArrayList<>();


    private static boolean alreadyAdded = false;
    public static String randomName(){

        if(!alreadyAdded){
            randomNames.addAll(randomNamesList);
            alreadyAdded = true;
        }

        int index = ((int) (Math.random() * randomNames.size()));
        String name = randomNames.get(index);
        randomNames.remove(index);
        return name;

    }

}
