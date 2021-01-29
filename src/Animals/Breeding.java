package Animals;

import Game.IOFunctions;
import Player.Player;
import Store.Store;

import java.util.ArrayList;

public class Breeding {

    public static boolean breedAnimalMenu(Player player, ArrayList<Animal> animals){

        while(true){

            try {

                AnimalCAM.printAnimalToChoose(animals, "Breeding", "breed");

                //Choose a animal to breed and return its index.
                int index = IOFunctions.convertStringToInt()-1;

                //Choose a suitable breeding partner and return its index.
                int indexOfBreedingPartner = chooseBreedingPartner(animals.get(index), animals, animals.get(index).getAnimalType());
                System.out.println("----BreedingPartner 1:----" + indexOfBreedingPartner);      //Used debugging

                if (breedingAndAddIfBabies(player, animals.get(index), animals.get(indexOfBreedingPartner))) {
                    return true;
                }

            //Needed because "0" is the standard back key and it interferes with the arraylist index.
            }catch (java.lang.IndexOutOfBoundsException e){
                return false;
            }

        }
    }

    private static int chooseBreedingPartner(Animal firstAnimal, ArrayList<Animal> animals, String animalType){

        int number = 1;
        int index = 0;
        int skipped = -1; //This is needed for combining real index and "fake" index of the printed one from "printAnimalToChoose" function.

        while(true){

            for (Animal animal : animals) {

                //Filter of what to print out (suitable partners).
                if(firstAnimal.getName().equalsIgnoreCase(animal.getName())
                || firstAnimal.getGender().equalsIgnoreCase(animal.getGender())
                || !firstAnimal.getAnimalType().equalsIgnoreCase(animalType)){
                    System.out.println("---- skipped in for loop:" + skipped);   //Used debugging
                    skipped++;
                    continue;
                }

                System.out.println("Press " + number + " - Choose your "
                        + animal.getName().toUpperCase() + " (type: "
                        + animal.getClass().getSimpleName() + ", health: "
                        + animal.getHealth() + ", gender: "
                        + animal.getGender() + ")."
                        + animals.indexOf(animal));
                number++;

            }

            //Used for logic for getting printed numbers in synk with real index.
            if(animals.indexOf(firstAnimal) == animals.size()-1){
                skipped--;
            }

            index = (IOFunctions.convertStringToInt() + skipped);
            System.out.println("---- skipped total:" + skipped);            //Used debugging

            if(index <= 1 || index >= number){
                System.out.println("----BreedingPartner 2:----"+ index);    //Used debugging
                return index;
            }
            else {
                System.out.println("Enter a number between 1-" + number);   //Used debugging
            }

        }
    }

    private static boolean breedingAndAddIfBabies(Player player, Animal selectedAnimal, Animal breedingPartner){

        System.out.println("----SelectedAnimal 3:----"+ player.getAnimals().indexOf(selectedAnimal));
        System.out.println("----BreedingPartner 4:----"+ player.getAnimals().indexOf(breedingPartner));

        double chanceOfNewBaby = (Math.random() * 100) + 1;
        System.out.println("DEBUG 0: " + chanceOfNewBaby); //DEBUG
        double bonusChance = 1;
        int howManyBabies = (int) (Math.random() * selectedAnimal.getMaxNewbornBabies())+1;
        double tenPercentOfMaxHP = 0.1 * selectedAnimal.getMaxHealth();


        //For every 10% of max hp, increase chance of getting a baby by 1%.
        for(int i = 0; selectedAnimal.getHealth() >= i; i += (int)tenPercentOfMaxHP ){
            if(breedingPartner.getHealth() >= i){
                bonusChance += 0.01;
            }
            bonusChance += 0.01;
        }
        System.out.println("DEBUG 1: " + bonusChance); //DEBUG

        chanceOfNewBaby *= bonusChance;

        System.out.println("DEBUG 2: " + chanceOfNewBaby); //DEBUG

        System.out.println(selectedAnimal.getName() + player.getAnimals().indexOf(selectedAnimal)
                + " and " + breedingPartner.getName()
                + player.getAnimals().indexOf(breedingPartner)
                + " have a chance of " + Math.floor(50 + (100 * bonusChance))
                +  "% of getting up to " +
                selectedAnimal.getMaxNewbornBabies() + " babies.");

        if(!IOFunctions.areYouSure("continue?")){
            return false;
        }


        if(chanceOfNewBaby > 50){
            System.out.println("Congratz!! You have successfully breaded " + howManyBabies + " new baby " + selectedAnimal.animalTypePlural);
            System.out.println("Rolled: " + chanceOfNewBaby + " (50+ needed");
            Store.createAnimalToPlayersAnimalList(player, howManyBabies, selectedAnimal.getAnimalType(),true);
        }else {
            System.out.println("Sorry, there were no new cute babies...");
            System.out.println("Rolled: " + chanceOfNewBaby + " (50+ needed");
        }
        return true;
    }



}
