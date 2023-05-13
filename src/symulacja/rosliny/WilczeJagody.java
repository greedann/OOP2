package symulacja.rosliny;

import symulacja.Roslina;

import java.awt.*;

public class WilczeJagody extends Roslina{
    public WilczeJagody(symulacja.World world, int x, int y) {
        super(world, x, y);
        this.sila = 99;
        this.color = Color.blue;
    }

    @Override
    public symulacja.Organizm copy(int x, int y) {
        return new WilczeJagody(this.world, x, y);
    }

    @Override
    public String to_str() {
        return "WilczeJagody";
    }

    @Override
    public void kolizja(symulacja.Organizm org) {
        System.out.println(org.to_str() + " zjadl WilczeJagody i umarł");
        super.kolizja(org);
        this.world.removeOrganizm(org);
    }
}
