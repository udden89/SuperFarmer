package animals;

import game.IOFunctions;
import player.Player;
import store.Store;

import java.util.ArrayList;

public class Breeding {

    public static boolean startProcessOfBreedingTwoAnimals(Player player){

        while(true){

                AnimalHelper.printAnimalToChoose(player.animals, "Breeding", "breed");

                //Chose a animal to breed.
                Animal animalToBreed = player.animals.get(IOFunctions.convertStringToInt(0, player.animals.size()));
                //Chose a second animal to breed with the first one.
                Animal breedingPartner = chooseBreedingPartner(player, animalToBreed);



                return true;

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

        if(!IOFunctions.printAndAskIfUserAreSure("continue?")){
            return false;
        }

        //Breed and create new animals if chance variable hits over 50.
        if(chanceOfNewBaby > 50){
            System.out.println("Congratz!! You have successfully breaded " + howManyBabies + " new baby " + selectedAnimal.animalTypePlural);
            System.out.println("Rolled: " + chanceOfNewBaby + " (50+ needed");

            for (int i = 0; i < howManyBabies; i++) {
                Store.createAnAnimal(player, selectedAnimal.getAnimalType(),true);
            }

        }else {
            System.out.println("Sorry, there were no new cute babies...");
            System.out.println("Rolled: " + Math.floor(chanceOfNewBaby) + " (50+ needed)");
        }
        return true;
    }

    private static Animal chooseBreedingPartner(Player player, Animal firstAnimal){

        //list that will hold all suitable partners.
        ArrayList<Animal> tempAnimalList = new ArrayList<>();

        addSuitableBreedingPartnersToList(player, firstAnimal, tempAnimalList);
        printAllSuitablePartners(tempAnimalList);

        return tempAnimalList.get(IOFunctions.convertStringToInt()-1);


    }

    private static void addSuitableBreedingPartnersToList(Player player, Animal firstAnimal, ArrayList<Animal> tempAnimalList){

        for (Animal animal : player.animals) {

            //Filter of what to not add into suitable partners.
            if(firstAnimal.getName().equalsIgnoreCase(animal.getName())
                    || firstAnimal.getGender().equalsIgnoreCase(animal.getGender())
                    || (!firstAnimal.getAnimalType().equalsIgnoreCase(animal.getAnimalType()))){
                continue;
            }
            tempAnimalList.add(animal);
        }
    }

    private static void printAllSuitablePartners(ArrayList<Animal> tempAnimalList){

        int number = 1;
        //Prints all suitable partners.
        for(Animal animal : tempAnimalList){

            System.out.println("Press " + (number) + " - Choose your "
                    + animal.getName() + " (type: "
                    + animal.getAnimalType() + ", health: "
                    + animal.getHealth() + ", gender: "
                    + animal.getGender() + ").");

            number++;
        }

        if(tempAnimalList.isEmpty()){
            System.out.println("Sorry, no available partners!");
        }

    }


}
