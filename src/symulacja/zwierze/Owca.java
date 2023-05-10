package symulacja.zwierze;

import symulacja.Zwierze;
public class Owca extends Zwierze {
    public Owca(symulacja.World world, int x, int y) {
        super(world, x, y);
        this.sila = 4;
        this.inicjatywa = 4;
        this.color = java.awt.Color.WHITE;
    }

    @Override
    public symulacja.Organizm copy(int x, int y) {
        return new Owca(this.world, x, y);
    }

    @Override
    public String to_str() {
        return "Owca";
    }
}
