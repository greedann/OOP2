package symulacja.zwierze;

import symulacja.Organizm;
import symulacja.World;
import symulacja.Zwierze;

import java.awt.*;

public class Antylopa extends Zwierze {
    public Antylopa(World world, int x, int y) {
        super(world, x, y);
        this.sila = 4;
        this.inicjatywa = 4;
        this.color = Color.CYAN;
    }

    @Override
    public Organizm copy(int x, int y) {
        return new Antylopa(this.world, x, y);
    }

    @Override
    public void akcja(int zasieg,boolean wech) {
        super.akcja(2, false);
    }

    @Override
    public void kolizja(Organizm organizm) {
        int dice = (int) (Math.random()*2);
        if (dice == 1) {
            System.out.println(this.to_str() + " uciekla przed " + organizm.to_str());
            int[] newpos = this.world.getFree(this.x, this.y);
            if (newpos[0] != -1) {
                this.x = newpos[0];
                this.y = newpos[1];
            }
            else {
                super.kolizja(organizm);
            }
        }
        else
        {
            super.kolizja(organizm);
        }
    }

    @Override
    public String to_str() {
        return "Antylopa";
    }

}
