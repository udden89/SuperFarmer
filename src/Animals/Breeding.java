package Animals;

import Game.IOFunctions;
import Player.Player;
import Store.Store;

import java.util.ArrayList;

public class Breeding {

    public static boolean breedAnimalMenu(Player player, ArrayList<Animal> animals){

        while(true){

            AnimalCAM.printAndSelectAnimal(animals,"Breeding", "breed");
            int choice = IOFunctions.convertStringToInt();
            int index = AnimalCAM.findIndexOfSelectedAnimal(animals, choice);
            System.out.println("INDEX -------> " + index);

            int indexOfBreedingPartner = chooseBreedingPartner(animals.get(index), animals, animals.get(index).getAnimalType());

            System.out.println("INDEX -------> " + animals.get(index));

            if(resultOfBreeding(animals.get(index), animals.get(indexOfBreedingPartner), player)){
                return true;
            }

        }

    }

    private static int chooseBreedingPartner(Animal selectedAnimal, ArrayList<Animal> animals, String animalType){

        System.out.println("____________________________");
        System.out.println(selectedAnimal.getName());
        System.out.println(animals.get(0).getName());
        System.out.println(animalType);
        System.out.println("____________________________");

        int number = 1;

        while(true){

            for (Animal animal : animals) {

                //Filter of what to print out (suitable partners).
                if(selectedAnimal.getName().equalsIgnoreCase(animal.getName())
                || selectedAnimal.getGender().equals(animal.getGender())
                || !selectedAnimal.getAnimalType().equals(animalType)){

                    System.out.println("1" + animal.getName());
                    continue;
                }

                System.out.println("Enter " + number + " to choose your "
                + animal.getName().toUpperCase() + " (type: "
                + animal.getClass().getSimpleName() + ", health: "
                + animal.getHealth() + ", gender: "
                + animal.getGender() + ").");
                number++;

            }
            return IOFunctions.convertStringToInt();
        }
    }

    private static boolean resultOfBreeding(Animal selectedAnimal, Animal selectedSecondAnimal, Player player){

        int bonusChance = 0;
        int chanceOfNewBaby = (int) (Math.random() * 100) + 1;
        double tenPercentOfMaxHP = 0.1 * selectedAnimal.getMaxHealth();
        int howManyBabies = (int) (Math.random() * selectedAnimal.getMaxNewbornBabies())+1;

        //For every 10% of max hp, increase chance of getting a baby.
        for(int i = 0; selectedAnimal.getHealth() > i; i += (int)tenPercentOfMaxHP ){
            if(selectedSecondAnimal.getHealth() > i){
                bonusChance++;
            }
            bonusChance++;
        }

        ////For every 10% loss of max hp, decrease chance of getting a baby.
        for(int i = 0; selectedAnimal.getHealth() < i; i += (int)tenPercentOfMaxHP ){
            if(selectedSecondAnimal.getHealth() < i){
                bonusChance--;
            }
            bonusChance--;
        }


        if(chanceOfNewBaby + bonusChance > 50){
            System.out.println("total chanceOfNewBaby" + chanceOfNewBaby + bonusChance);
            System.out.println("Congratz!! You have successfully breaded " + howManyBabies + " new baby " + selectedAnimal.animalTypePlural);
            for(int i = 1; i < howManyBabies; i++){
                Store.createAnimalToPlayersAnimalList(player, howManyBabies, selectedAnimal.getAnimalType(),true);
            }
        }else {
            System.out.println("Sorry, there were no new cute babies...");
        }
        return true;

    }

}
