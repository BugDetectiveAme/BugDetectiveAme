package HockeyGame;

public class Player {
    private String name;
    private int att;
    private int def;

    public Player(String name,int att,int def) {
        this.name = name;
        this.att = att;
        this.def = def;
    }

    public String getName() {
        return this.name;
    }

    public int getAtt() {
        return this.att;
    }

    public int getDef() {
        return this.def;
    }
}
