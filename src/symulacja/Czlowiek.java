package symulacja;

import java.awt.*;

public class Czlowiek extends Zwierze{
    int umiejetnosc;

    public Czlowiek(World world, int x, int y) {
        super(world, x, y);
        this.color = Color.RED;
        this.sila = 5;
        this.inicjatywa = 4;
        this.umiejetnosc = 0;
    }

    @Override
    public void kolizja(Organizm organizm) {
        this.world.removeOrganizm(organizm);
    }

    @Override
    public void akcja(int zasieg, boolean wech) {
        this.wiek++;
        Organizm organizm = world.getOrganizm(this.x, this.y);
        if (organizm != null) {
            this.world.removeOrganizm(organizm);
        }
        if (this.umiejetnosc > 0) {
            this.umiejetnosc--;
            if (this.umiejetnosc == 0) {
                this.umiejetnosc = -5;
            }
        }
        else if (this.umiejetnosc < 0) {
            this.umiejetnosc++;
        }
    }

    @Override
    public String to_str() {
        return "symulacja.Czlowiek";
    }

    @Override
    public Organizm copy(int x, int y) {
        return new Czlowiek(this.world, this.x, this.y);
    }

    public boolean setUmiejetnosc(int umiejetnosc) {
        if (this.umiejetnosc == 0) {
            this.umiejetnosc = umiejetnosc;
            return true;
        }
        return false;
    }

    public int getUmiejetnosc() {
        return this.umiejetnosc;
    }


}
