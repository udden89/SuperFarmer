package Player;

import Animals.Animal;
import java.util.ArrayList;

public class Player {

    private String playerName;
    private int gold = 10000;

    private int stackOfKiloFish = 0;
    private int stackOfKiloMeat = 0;
    private int stackOfKiloSalad = 0;

    private int sickAnimals = 0;

    public ArrayList<Animal> animals = new ArrayList<>();


    public Player(String playerName){
        this.playerName = playerName;
    }


    //<editor-fold desc="Getters & Setters">
    public ArrayList<Animal> animals() {
        return animals;
    }
    public void setAnimals(ArrayList<Animal> animals) {
        this.animals = animals;
    }
    public int getStackOfKiloFish() {
        return stackOfKiloFish;
    }
    public void setStackOfKiloFish(int stackOfKiloFish) {
        this.stackOfKiloFish = stackOfKiloFish;
    }
    public int getStackOfKiloMeat() {
        return stackOfKiloMeat;
    }
    public void setStackOfKiloMeat(int stackOfKiloMeat) {
        this.stackOfKiloMeat = stackOfKiloMeat;
    }
    public int getStackOfKiloSalad() {
        return stackOfKiloSalad;
    }
    public void setStackOfKiloSalad(int stackOfKiloSalad) {
        this.stackOfKiloSalad = stackOfKiloSalad;
    }
    public String getPlayerName() {
        return playerName;
    }
    public int getGold() {
        return gold;
    }
    public void setGold(int gold) {
        this.gold = gold;
    }
    public int getSickAnimals() {
        return sickAnimals;
    }
    public void setSickAnimals(int sickAnimals) {
        this.sickAnimals = sickAnimals;
    }

    //</editor-fold>

}
