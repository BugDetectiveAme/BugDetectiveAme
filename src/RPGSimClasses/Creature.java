package RPGSimClasses;

import java.util.Random;

public class Creature {
    private final String name;
    private final String type;
    private int health;
    private int power;
    private int spPower;
    private int speed;

    public Creature(String type, int power, int spPower, int speed) {
        String[] syllables = {"pi","ka","chu","mu","mei","gu","ra","ko","ro","ne"};
        String name = "";
        for (int i=0;i<3;++i) {
            name += syllables[new Random().nextInt(10)];
        }
        name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
        this.name = name;
        this.type = type;
        this.health = 100;
        this.power = power;
        this.spPower = spPower;
        this.speed = speed;
    }

    public Creature(String name, String type, int health, int power, int spPower, int speed) {
        this.name = name;
        this.type = type;
        this.health = health;
        this.power = power;
        this.spPower = spPower;
        this.speed = speed;
    }

    public Creature() {
        this.name = null;
        this.type = null;
    }

    public void printInfo(int count) {
        System.out.printf(String.valueOf(count) + " ---%s---\nType: %s\nHealth: %d\nPower: %d\nSpecial Power: %d\nSpeed: %d\n",name,type,health,power,spPower,speed);
    }

    public void printInfo() {
        System.out.printf("---%s---\nType: %s\nHealth: %d\nPower: %d\nSpecial Power: %d\nSpeed: %d\n",name,type,health,power,spPower,speed);
    }

    public void setHealth(int val) {
        this.health = val;
    }

    public void setPower(int val) {
        this.power = val;
    }

    public void setSpPower(int val) {
        this.spPower = val;
    }

    public void setSpeed(int val) {
        this.speed = val;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public String toFormat() {
        return this.name + "//" + this.type + "//" + this.health + "//" + this.power + "//" + this.spPower + "//" + this.speed + ";;";
    }
}
