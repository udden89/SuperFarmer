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

                //Choose a first animal to breed and return its index.
                int index = IOFunctions.convertStringToInt(0,animals.size())-1;

                //Choose a second suitable animal to the first picked. Returns true if a player chose to breed.
                if (breedingAndAddIfBabies(player, animals.get(index), chooseBreedingPartner(animals.get(index), animals, animals.get(index)))) {
                    return true;
                }

            //Needed because "0" is the standard back key and it interferes with the arraylist index if user want to to go back.
            }catch (java.lang.IndexOutOfBoundsException e){
                return false;
            }

        }
    }

    private static boolean breedingAndAddIfBabies(Player player, Animal selectedAnimal, Animal breedingPartner){


        double chanceOfNewBaby = (Math.random() * 100) + 1; // 1-100 chance
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

        chanceOfNewBaby *= bonusChance;

        // Prints: X and X have a chance of X% to get up to X babies.
        System.out.println(selectedAnimal.getName() + " and " + breedingPartner.getName()
                + " have a chance of " + Math.floor(50 + (100 * (bonusChance-1)))
                +  "% of getting up to " +
                selectedAnimal.getMaxNewbornBabies() + " babies.");

        if(!IOFunctions.areYouSure("continue?")){
            return false;
        }

        //Breed and create new animals if chance variable hits over 50.
        if(chanceOfNewBaby > 50){
            System.out.println("Congratz!! You have successfully breaded " + howManyBabies + " new baby " + selectedAnimal.animalTypePlural);
            System.out.println("Rolled: " + chanceOfNewBaby + " (50+ needed");
            Store.createAnimalToPlayersAnimalList(player, howManyBabies, selectedAnimal.getAnimalType(),true);
        }else {
            System.out.println("Sorry, there were no new cute babies...");
            System.out.println("Rolled: " + Math.floor(chanceOfNewBaby) + " (50+ needed)");
        }
        return true;
    }

    private static Animal chooseBreedingPartner(Animal firstAnimal, ArrayList<Animal> animals, Animal secondAnimal){

        int number = 0;

        //list that holds all suitable partners.
        ArrayList<Animal> tempAnimalList = new ArrayList<>();

        while(true){

            for (Animal animal : animals) {

                //Filter of what to not add into suitable partners.
                if(firstAnimal.getName().equalsIgnoreCase(animal.getName())
                        || firstAnimal.getGender().equalsIgnoreCase(animal.getGender())
                        || (!firstAnimal.getAnimalType().equalsIgnoreCase(secondAnimal.getAnimalType()))){
                    continue;
                }
                tempAnimalList.add(animal);
            }

            if(tempAnimalList.isEmpty()){
                System.out.println("Sorry, no available partners!");
            }

            //Prints all suitable partners.
            for(Animal animal : tempAnimalList){

                System.out.println("Press " + (number+1) + " - Choose your "
                        + animal.getName() + " (type: "
                        + animal.getAnimalType() + ", health: "
                        + animal.getHealth() + ", gender: "
                        + animal.getGender() + ").");

                number++;
            }
            return tempAnimalList.get(IOFunctions.convertStringToInt()-1);
        }

    }


}
