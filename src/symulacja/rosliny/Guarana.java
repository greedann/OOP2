package symulacja.rosliny;

import symulacja.Roslina;
public class Guarana extends Roslina{
    public Guarana(symulacja.World world, int x, int y) {
        super(world, x, y);
        this.sila = 0;
        this.color = java.awt.Color.PINK;
    }

    @Override
    public symulacja.Organizm copy(int x, int y) {
        return new Guarana(this.world, x, y);
    }

    @Override
    public String to_str() {
        return "Guarana";
    }

    @Override
    public void kolizja(symulacja.Organizm organizm) {
        System.out.println(organizm.to_str() + " zjadl guarane i otrzymal 3 sily");
        organizm.setSila(organizm.getSila() + 3);
        super.kolizja(organizm);
    }
}
