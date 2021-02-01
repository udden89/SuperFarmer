package Animals;

import Game.IOFunctions;
import Player.Player;

import java.util.ArrayList;

//CAM Stands for calculations and menus
public class AnimalCAM {


    public static String genderOfNewAnimal(String animalType, boolean random) {

        if (random){
            int number = (int) (Math.random() * 10) + 1;
            return number <= 5 ? "FEMALE" : "MALE";
        }

        System.out.println(IOFunctions.line);
        System.out.println("What gender do you want on your new " + animalType + "?" );
        System.out.println("Press F for female");
        System.out.println("Press M for male");

        while (true) {

            String choice = IOFunctions.inputString().toUpperCase();

            if (choice.equals("F")) {
                return "FEMALE";
            } else if (choice.equals("M")) {
                return "MALE";
            } else {
                System.out.println("Please enter F or M");
            }
        }

    }

    public static String inputNameOfNewAnimal(String animalType){

        System.out.println(IOFunctions.line);
        System.out.println("Give your new " + animalType + " a name: ");

        return IOFunctions.inputString();
    }

    public static void printAboutAnimals(String animalType){

        switch (animalType.toUpperCase()){

            case "WOLF":
                System.out.println(IOFunctions.line);
                System.out.println("About the wolves: ");
                break;

            case "PANDA":
                System.out.println(IOFunctions.line);
                System.out.println("About the pandas: ");
                break;

            case "BEAR":
                System.out.println(IOFunctions.line);
                System.out.println("About the bears: ");
                break;

            case "EAGLE":
                System.out.println(IOFunctions.line);
                System.out.println("About the eagles: ");
                break;

            case "BABY JEDI":
                System.out.println(IOFunctions.line);
                System.out.println("About the baby jedi´s: ");
                break;

            default:
                System.out.println("Something went wrong in printAnimalInfo");

        }

    }

    public static void printAnimalToChoose(ArrayList<Animal> animals, String menuName , String verb){

        System.out.println(IOFunctions.line);
        System.out.println(menuName + "... ");
        System.out.println(IOFunctions.line + "\n");

        int number = 1;

        for (Animal animal : animals) {

            System.out.println("Enter " + number + " to " + verb + " your "
                    + animal.getName().toUpperCase() + " (type: "
                    + animal.getClass().getSimpleName() + ", health: "
                    + animal.getHealth() + ", gender: "
                    + animal.getGender() + ").");

            number++;

        }

        System.out.println("Press 0 to go back.");

    }

    public static int findIndexOfSelectedAnimal(ArrayList<Animal> animals, int playersChoice){

        //Loop for finding the index of selected animal.
        try{
            for(int i = 0; i <animals.size(); i++){
            if(animals.get(playersChoice-1).getName().equals(animals.get(i).getName())){
                return i;
            }
        }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return -1;
    }

    //Decrease health, increase age and making animal sick.
    public static void decreaseAnimalHealthAndAgePerRound(Player player, ArrayList<Animal> animals){

        for(int i = 0; i < animals.size(); i++){

            int health = animals.get(i).getMaxHealth();
            int sickRandom = (int) (Math.random() * 100) + 1;
            double min = (0.1 * health); //Min 10% of health
            double max = ( (0.3 * health) -min) +1; //Max 30% of health

            animals.get(i).setHealth(animals.get(i).getHealth() - ((int) (Math.random() * (max))+ (int)min));
            animals.get(i).setAge(animals.get(i).getAge()+1);

            //If sick remove a "life".
            if(animals.get(i).isSick){
                animals.get(i).roundsBeforeDeathIfSick--;
            }

            //If health < 0, animal reached max health or animal is sick remove the animal.
            if(animals.get(i).getHealth() <= 0
              || animals.get(i).getAge() == animals.get(i).getDeathAtAge()
              || animals.get(i).roundsBeforeDeathIfSick == 0){
                System.out.println(player.getPlayerName() + ", your " + animals.get(i).getName() + " has died");
                player.getDeadAnimals().add(animals.get(i));
                animals.remove(animals.get(i));
                i--;
                continue;
            }

            if(sickRandom <= 20){
                animals.get(i).setSick(true);
                System.out.println(player.getPlayerName() + ", your " + animals.get(i).getName() + " has become ill and needs to see a veterinary"
                + " (otherwise it will die under the next 1-3 rounds).");
            }
        }
    }

}
