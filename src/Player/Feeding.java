package Player;

import Animals.Animal;
import Game.IOFunctions;

import java.util.ArrayList;


public class Feeding {

    public static boolean feedAnimalMenu(Player player, ArrayList<Animal> animals){

        while(true){

            //<editor-fold desc="PRINTING FEED MENU">
            System.out.println(IOFunctions.line);
            System.out.println("Feeding animals...:");
            System.out.println(IOFunctions.line + "\n");

            int number = 1;
            for (Animal animal : animals) {

                //Enter X to feed your ANIMAL (type: CLASS, health: 100, gender: Female).
                System.out.println("Enter " + number + " to feed your "
                        + animal.getName().toUpperCase() + " (type: "
                        + animal.getClass().getSimpleName() + ", health: "
                        + animal.getHealth() + ", gender: "
                        + animal.getGender() + ").");
                number++;
            }
            //</editor-fold>

            int choice = IOFunctions.convertStringToInt();

            //Loop for finding the right animal of users choice.
            for(int i = 0; i <animals.size(); i++){
                if(animals.get(choice-1).getName().equals(animals.get(i).getName())){

                    switch (animals.get(i).getClass().getSimpleName().toLowerCase()){
                        case "wolf":
                            feeding(player, animals, i);
                            return true;
                        case "9":
                            return false;
                        default:
                            System.out.println("Something got wrong...");
                    }
                }
            }
        }

    }

    public static boolean feeding(Player player, ArrayList<Animal> animals, int index){

        while(true){

            System.out.println(IOFunctions.line);
            System.out.println("Feeding " + animals.get(index).getName() + " with: \n");

            if(animals.get(index).isEatsFish()){
                System.out.println("Press F for fish (you have: " + player.getStackOfKiloFish()
                        + " kilo available. Fish restores 10-30% health/kg)");
            }
            if(animals.get(index).isEatsMeat()){
                System.out.println("Press M for meat (you have: " + player.getStackOfKiloMeat()
                        + " kilo available. Meat restores 10-30% health/kg)");
            }
            if(animals.get(index).isEatsSalad()){
                System.out.println("Press S for salad (you have: " + player.getStackOfKiloSalad()
                        + " kilo available. Salad restores 10-30% health/kg)");
            }

            System.out.println("Press 9 to go back");

            String choice = IOFunctions.inputString().toUpperCase();

            int kilo = 0;

            switch (choice){

                case "F":
                    System.out.println("How many kilo of fish do you want to use?");
                    kilo = IOFunctions.convertStringToInt();
                    if(!IOFunctions.areYouSure()){
                        return false;
                    }
                    if(player.getStackOfKiloFish() >= kilo){

                        restoreHealth(animals.get(index), kilo);
                        player.setStackOfKiloFish(player.getStackOfKiloFish()-kilo);
                        return true;
                    }

                case "M":

                case "S":

                case "9":
                    return false;

                default:
                    System.out.println("Please enter F, M, S or 9");

            }
        }
    }

    //Should this function move to other class (food)????
    private static boolean restoreHealth(Animal animal, int kilo){

        if(animal.getHealth() >= animal.getMaxHealth()){
            System.out.println("Animal already on full health!");
            return false;
        }

        //Adds random health between 10-30% of max health per kilo food
        int health = animal.getMaxHealth();
        double min = (0.1*health);
        double max = ((0.3*health)-min)+1;

        for(int i = 0; i < kilo; i++){
            animal.setHealth(animal.getHealth() + ((int) (Math.random() * (max))+ (int)min));

            //If health exceeds max health, set health to max.
            if(animal.getHealth() > animal.getMaxHealth()){
                animal.setHealth(animal.getMaxHealth());
            }
            System.out.println("Health on " + animal.getName() + " restored to: " + animal.getHealth());
        }
        return true;
    }
}
