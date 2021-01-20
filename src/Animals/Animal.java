package Animals;

public abstract class Animal {


    //These variables are used in game text.
    public String animalTypePlural = "";
    public String animalTypeSingular = "";

    private String name;
    private String gender;

    private int age = 1;
    private int deathAtAge = (int) (Math.random() * (4))+7; //Dead between age of 7-10
    private int health = 100;

    private boolean eatsMeat = true;
    private boolean eatsFish = true;
    private boolean eatsSalad = false;

    Animal(String name, String gender, boolean eatsFish, boolean eatsMeat, boolean eatsSalad, String animalTypeSingular, String animalTypePlural) {
        this.name = name;
        this.gender = gender;
        this.eatsFish = eatsFish;
        this.eatsMeat = eatsMeat;
        this.eatsSalad = eatsSalad;
        this.animalTypeSingular = animalTypeSingular;
        this.animalTypePlural = animalTypePlural;
    }



    //-----------------------GETTERS & SETTERS----------------------//

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
    //-----------------------END OF GETTERS & SETTERS----------------------//

}
