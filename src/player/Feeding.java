package player;

import animals.Animal;
import animals.AnimalHelper;
import game.IOFunctions;

import java.util.ArrayList;


public class Feeding {


    public static boolean startProcessOfFeedingAnimal(Player player, ArrayList<Animal> animals){

        AnimalHelper.printAnimalToChoose(animals,"Feeding animals", "feed");
        Animal animal = animals.get(IOFunctions.convertStringToInt(0, animals.size()-1));

        if(feeding(player, animal)){
            return true;
        }else
            return false;
    }

    public static boolean feeding(Player player, Animal animal){

        while(true){

            IOFunctions.printLine();
            System.out.println("Feeding " + animal.getName() + " with: \n");

            if(animal.isEatsFish()){
                System.out.println("Press F for fish (you have: " + player.getStackOfKiloFish()
                        + " kilo available. Fish restores 10-30% health/kg)");
            }
            if(animal.isEatsMeat()){
                System.out.println("Press M for meat (you have: " + player.getStackOfKiloMeat()
                        + " kilo available. Meat restores 10-30% health/kg)");
            }
            if(animal.isEatsSalad()){
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
                        if(!IOFunctions.printAndAskIfUserAreSure("Are you sure? This will end your turn.")){
                            return false;
                        }
                        restoreHealth(animal, kilo);
                        player.setStackOfKiloFish(player.getStackOfKiloFish()-kilo);
                    return true;
                    }
                    else {
                        System.out.println(IOFunctions.notInStock);
                        return false;
                    }

                case "M":

                    if(player.getStackOfKiloMeat() >= kilo){
                        System.out.println("How many kilo of meat do you want to use?");
                        kilo = IOFunctions.convertStringToInt();
                        if(!IOFunctions.printAndAskIfUserAreSure("Are you sure? This will end your turn.")){
                            return false;
                        }
                        restoreHealth(animal, kilo);
                        player.setStackOfKiloMeat(player.getStackOfKiloMeat()-kilo);
                        return true;
                    }
                    else {
                        System.out.println(IOFunctions.notInStock);
                        return false;
                    }

                case "S":
                    if(player.getStackOfKiloSalad() >= kilo){
                        System.out.println("How many kilo of salad do you want to use?");
                        kilo = IOFunctions.convertStringToInt();
                        if(!IOFunctions.printAndAskIfUserAreSure("Are you sure? This will end your turn.")){
                            return false;
                        }
                        restoreHealth(animal, kilo);
                        player.setStackOfKiloSalad(player.getStackOfKiloSalad()-kilo);
                        return true;
                    }
                    else {
                        System.out.println(IOFunctions.notInStock);
                        return false;
                    }

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
