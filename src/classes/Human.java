package classes;

//Oh you're a class alright, just not a *super* one

public class Human extends Animal {
    private String name;
    private String sound;

    public Human(String name,String sound) {
        super(name,2,sound,false,"human");
        this.name = name;
        this.sound = sound;
    }

    //Overrides the method in the Animal class
    //**Is necessary**
    @Override
    public void move() {
        System.out.println(name + " moves two spaces");
    }
}
