package Food;

import Animals.Wolf;

public abstract class Food {


    //Restores random health between 10-30% of max hp.
    public static int healthRestoredInPercent(int maxHealth){

        int health = maxHealth;
        int healthRestored;

        double min = (0.1*health); // Minimum cap of 10% of health
        double max = ((0.3*health)-min)+1; // Max cap on 30% of health

        return healthRestored = (int) ((Math.random() * (max)) + min);
    }
}
