public class Roslina extends Organizm{
    public Roslina(World world, int x, int y) {
        super(world, x, y);
        sila = 0;
        inicjatywa = 0;
    }

    @Override
    public void akcja() {

    }

    @Override
    public void kolizja(Organizm organizm) {

    }

    @Override
    public String to_str() {
        return null;
    }

    @Override
    public Organizm copy() {
        return null;
    }
}
