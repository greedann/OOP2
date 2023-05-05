abstract public class Organizm {
    int x;
    int y;
    char symbol;
    int sila;
    int inicjatywa;
    int wiek;
    World world;
    public Organizm(World world, int x, int y) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.symbol = 'O';
        this.wiek = 0;
    }
    public abstract void akcja();
    public abstract void kolizja(Organizm organizm);
    public abstract String to_str();
    public abstract Organizm copy();

}
