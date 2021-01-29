package Player;

import Animals.Animal;
import Animals.AnimalCAM;
import Game.IOFunctions;

import java.util.ArrayList;


public class Feeding {

    public static boolean feedAnimalMenu(Player player, ArrayList<Animal> animals){

        while(true){

            AnimalCAM.printAnimalToChoose(animals,"Feeding animals", "feed");

            int choice = IOFunctions.convertStringToInt();
            if (choice == 0){
                return false;
            }

            //Loop for finding the right animal of users choice.
            for(int i = 0; i <animals.size(); i++){
                if(animals.get(choice-1).getName().equals(animals.get(i).getName())){

                    switch (animals.get(i).getClass().getSimpleName().toUpperCase()){
                        case "WOLF":
                            if(feeding(player, animals, i)){
                                return true;
                            }

                        default:
                            return false;
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

            System.out.println("Press 0 to go back");

            String choice = IOFunctions.inputString();
            System.out.println(choice);

            int kilo = 1;

            switch (choice.toUpperCase()){

                case "F":

                    if(player.getStackOfKiloFish() >= kilo){
                        System.out.println("How many kilo of fish do you want to use?");
                        kilo = IOFunctions.convertStringToInt();
                        if(!IOFunctions.areYouSure(" use these fish?")){
                            return false;
                        }
                        restoreHealth(animals.get(index), kilo);
                        player.setStackOfKiloFish(player.getStackOfKiloFish()-kilo);
                    return true;
                    }
                    else {
                        System.out.println(IOFunctions.notInStock);
                        return false;
                    }

                case "M":

                case "S":

                case "0":
                    return false;

                default:
                    System.out.println("Please enter F, M, S or 0");

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
