package animals;

import game.IOFunctions;
import player.Player;
import store.Store;

import java.util.ArrayList;

public class Breeding {

    public static boolean startProcessOfBreedingTwoAnimals(Player player){

        AnimalHelper.printAnimalToChoose(player.animals, "Breeding", "breed");

        //Chose a animal to breed.
        int indexAnimal = IOFunctions.convertStringToInt(0, player.animals.size());

        if(indexAnimal <= 0){
            return false;
        }

        Animal animalToBreed = player.animals.get(indexAnimal-1);

        //Chose a second animal to breed with the first one.
        Animal breedingPartner = chooseBreedingPartner(player, animalToBreed);
        if(breedingPartner==null){
            return false;
        }

        printChanceToBreed(animalToBreed, breedingPartner, calculateBonusChance(animalToBreed, breedingPartner));

        if(!IOFunctions.printAndAskIfUserAreSure("Continue? This will end your turn.")){
            return false;
        }

        if(rollTheDiceForBreeding(animalToBreed, breedingPartner)){

            int howManyBabies = decideHowManyBabies(animalToBreed);

            System.out.println("Congratz!! You have successfully breaded "
                    + howManyBabies + " new " + animalToBreed.animalTypePlural);

            createBabies(player, animalToBreed, howManyBabies);

        }else {
            IOFunctions.printLine();
            IOFunctions.printSomethingWithThreadSleep
                    ("Sorry, could not produce new cute babies.", 50);
            IOFunctions.printLine();
        }
        return true;
    }

    private static boolean rollTheDiceForBreeding(Animal animalToBreed, Animal breedingPartner){

        double chanceOfNewBaby = (Math.random() * 100) + 1; // 1-100 chance

        double bonusChance = calculateBonusChance(animalToBreed, breedingPartner);
        chanceOfNewBaby *= bonusChance;

        return chanceOfNewBaby >= 50;
    }

    private static void createBabies(Player player, Animal animal, int howManyBabies){

        for (int i = 0; i < howManyBabies; i++) {
            Store.createAnAnimal(player, animal.animalType, true);
        }
    }

    private static int decideHowManyBabies(Animal animal) {
        return (int) (Math.random() * animal.getMaxNewbornBabies())+1;
    }

    private static void printChanceToBreed(Animal selectedAnimal, Animal breedingPartner, double bonusChance) {
        System.out.println(selectedAnimal.getName() + " and " + breedingPartner.getName()
                + " have a chance of " + Math.floor(50 + (100 * (bonusChance-1)))
                +  "% of getting up to " +
                selectedAnimal.getMaxNewbornBabies() + " babies.");
    }

    private static double calculateBonusChance(Animal selectedAnimal, Animal breedingPartner) {

        double bonusChance = 1;
        double tenPercentOfMaxHP = 0.1 * selectedAnimal.getMaxHealth();

        //For every 10% of max hp, increase chance of getting a baby by 1%.
        for(int i = 0; selectedAnimal.getHealth() >= i; i += tenPercentOfMaxHP){
            if(breedingPartner.getHealth() >= i){
                bonusChance += 0.01;
            }
            bonusChance += 0.01;
        }
        return bonusChance;
    }

    private static Animal chooseBreedingPartner(Player player, Animal firstAnimal){

        //list that will hold all suitable partners.
        ArrayList<Animal> tempAnimalList = new ArrayList<>();

        addSuitableBreedingPartnersToList(player, firstAnimal, tempAnimalList);

        if(tempAnimalList.isEmpty()){
            System.out.println("Sorry, no available partners!");
            return null;
        }

        printAllSuitablePartners(tempAnimalList);

        int animalIndex = IOFunctions.convertStringToInt(1,tempAnimalList.size());

        return tempAnimalList.get(animalIndex-1);

    }

    private static void addSuitableBreedingPartnersToList
            (Player player, Animal firstAnimal, ArrayList<Animal> tempAnimalList){

        for (Animal animal : player.animals) {

            //Filter of suitable partners.
            if((firstAnimal.getName().equalsIgnoreCase(animal.getName()))
                    || (firstAnimal.getGender().equalsIgnoreCase(animal.getGender()))
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

            System.out.println("["+ number + "] - Choose your "
                    + animal.getName() + " (type: "
                    + animal.getAnimalType() + ", health: "
                    + animal.getHealth() + ", gender: "
                    + animal.getGender() + ").");

            number++;
        }




    }
}
