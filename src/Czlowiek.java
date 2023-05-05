public class Czlowiek extends Zwierze{
    int umiejetnosc;

    public Czlowiek(World world, int x, int y) {
        super(world, x, y);
        this.symbol = 'C';
        this.sila = 5;
        this.inicjatywa = 4;
        this.umiejetnosc = 0;
    }

    @Override
    public void akcja() {

    }

    @Override
    public void kolizja(Organizm organizm) {

    }

    @Override
    public String to_str() {
        return "Czlowiek";
    }

    @Override
    public Organizm copy() {
        return new Czlowiek(this.world, this.x, this.y);
    }


}
