package symulacja.rosliny;

import symulacja.Roslina;
public class Trawa extends Roslina {
    public Trawa(symulacja.World world, int x, int y) {
        super(world, x, y);
        this.sila = 0;
        this.color = java.awt.Color.GREEN;
    }

    @Override
    public symulacja.Organizm copy(int x, int y) {
        return new Trawa(this.world, x, y);
    }

    @Override
    public String to_str() {
        return "Trawa";
    }

    @Override
    public void kolizja(symulacja.Organizm org) {
        System.out.println(org.to_str() + " zjadl trawe");
        super.kolizja(org);
    }

}
