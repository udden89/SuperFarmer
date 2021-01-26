package Game;

import Animals.Animal;
import Animals.BabyJedi;
import Player.Player;

import java.awt.event.KeyEvent;

public class CodeTesting {

    public static void inputGenderOfNewAnimal() {

        int health = 120;

        double min = (0.1*health);
        double max = ((0.3*health)-min)+1;
        System.out.println(max + "\n" + min);

        for(int i = 0 ; i < 1000; i++){
            int random = (int) (Math.random() * (max))+ (int)min; //Random health between 10-30%

            //if(random == 10 || random ==30)
            System.out.println(random);
        }


    }

}
