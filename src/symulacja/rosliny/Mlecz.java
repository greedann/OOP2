package symulacja.rosliny;

import symulacja.Roslina;

public class Mlecz extends Roslina{
    public Mlecz(symulacja.World world, int x, int y) {
        super(world, x, y);
        this.sila = 0;
        this.color = java.awt.Color.YELLOW;
    }

    @Override
    public symulacja.Organizm copy(int x, int y) {
        return new Mlecz(this.world, x, y);
    }

    @Override
    public String to_str() {
        return "Mlecz";
    }

    @Override
    public void akcja(int zasieg,boolean wech) {
        for(int i = 0; i < 3; i++) {
            super.akcja(1, false);
        }
    }

    @Override
    public void kolizja(symulacja.Organizm organizm) {
        System.out.println(organizm.to_str() + " zjadl mlecz");
        super.kolizja(organizm);
    }
}
