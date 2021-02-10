package Animals;

import java.util.ArrayList;

public abstract class Animal extends Object {

    //These variables are used in the text of the game.
    public String animalType;
    public String animalTypePlural;

    private String name;
    private String gender;

    private int age = 0;
    private int deathAtAge = (int) (Math.random() * (4))+7; //Dead between age of 7-10
    private int health = 100;
    private int maxHealth = 100;

    private int maxNewbornBabies;

    private boolean eatsMeat;
    private boolean eatsFish;
    private boolean eatsSalad;

    public boolean isSick = false;
    public int roundsBeforeDeathIfSick = (int) (Math.random() * 3) + 1;

    public static ArrayList<String> typeOfAnimals = new ArrayList<>();


    Animal(String name, String gender, boolean eatsFish, boolean eatsMeat, boolean eatsSalad,
           String animalType, String animalTypePlural, int maxNewbornBabies) {
        this.name = name;
        this.gender = gender;
        this.eatsFish = eatsFish;
        this.eatsMeat = eatsMeat;
        this.eatsSalad = eatsSalad;
        this.animalType = animalType;
        this.animalTypePlural = animalTypePlural;
        this.maxNewbornBabies = maxNewbornBabies;

    }

    public static void addAllAnimalTypes(){
        typeOfAnimals.add("WOLF");
        typeOfAnimals.add("PANDA");
        typeOfAnimals.add("BEAR");
        typeOfAnimals.add("EAGLE");
        typeOfAnimals.add("BABY JEDI");

    }


    //<editor-fold desc="Getters & Setters">
    public int getMaxNewbornBabies() {
        return maxNewbornBabies;
    }
    public void setMaxNewbornBabies(int maxNewbornBabies) {
        this.maxNewbornBabies = maxNewbornBabies;
    }
    public String getAnimalTypePlural() {
        return animalTypePlural;
    }
    public String getAnimalType() {
        return animalType;
    }
    public int getMaxHealth() {
        return maxHealth;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getDeathAtAge() {
        return deathAtAge;
    }
    public void setDeathAtAge(int deathAtAge) {
        this.deathAtAge = deathAtAge;
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public boolean isEatsMeat() {
        return eatsMeat;
    }
    public void setEatsMeat(boolean eatsMeat) {
        this.eatsMeat = eatsMeat;
    }
    public boolean isEatsFish() {
        return eatsFish;
    }
    public void setEatsFish(boolean eatsFish) {
        this.eatsFish = eatsFish;
    }
    public boolean isEatsSalad() {
        return eatsSalad;
    }
    public void setEatsSalad(boolean eatsSalad) {
        this.eatsSalad = eatsSalad;
    }
    public boolean isSick() {
        return isSick;}
    public void setSick(boolean sick) {
        isSick = sick;    }

    //</editor-fold>



}
