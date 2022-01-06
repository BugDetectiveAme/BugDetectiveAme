package techniques;

import classes.*;

public class OOP {
    public static void main(String[] args) throws Exception {
        Animal garfield = new Animal("Garfield",4,"I want lasagna",true,"cat");
        Animal gura = new Animal("Gawr Gura",2,"a",true,"shark");
        Human nick = new Human("Nicholas Mulderrig","I forgor");
        Human ben = new Human("Ben Shapiro","Bazinga!");
        Animal[] animals = {garfield,gura,nick,ben};
        gura.speak();
        ben.speak();

        gura.move();
        nick.move();
    }
}