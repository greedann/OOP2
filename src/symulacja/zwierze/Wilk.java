package symulacja.zwierze;

import symulacja.Zwierze;

import java.awt.*;

public class Wilk extends Zwierze{
    public Wilk(symulacja.World world, int x, int y) {
        super(world, x, y);
        this.sila = 9;
        this.inicjatywa = 5;
        this.color = Color.GRAY;
    }

    @Override
    public symulacja.Organizm copy(int x, int y) {
        return new Wilk(this.world, x, y);
    }

    @Override
    public String to_str() {
        return "Wilk";
    }
}
