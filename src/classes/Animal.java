package classes;

public class Animal {
    private String name;
    private int numberOfLegs;
    private String sound;
    private boolean hasTail;
    private String species;

    public Animal(String name,int numberOfLegs,String sound,boolean hasTail,String species) {
        this.name = name;
        this.numberOfLegs = numberOfLegs;
        this.sound = sound;
        this.hasTail = hasTail;
        this.species = species;
    }

    public void speak() {
        System.out.println(name + " says '" + sound + "'");
    }

    public void move() {
        System.out.println(name + " moves one space");
    }
}


/*pseudocode
public class Animal
    private name
    private numberOfLegs
    private sound
    private hasTail
    private typeOfAnimal

    procedure Animal(theName,theNumberOfLegs,theSound,tail,theSpecies)
        this.name = theName
        this.numberOfLegs = theNumberOfLegs
        this.sound = theSound
        this.hasTail = tail
        this.species = theSpecies
    endprocedure
 */