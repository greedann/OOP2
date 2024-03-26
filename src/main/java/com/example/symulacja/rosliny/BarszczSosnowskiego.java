package com.example.symulacja.rosliny;

import com.example.symulacja.Roslina;
import com.example.symulacja.World;
import com.example.symulacja.Organizm;

public class BarszczSosnowskiego extends Roslina{
    public BarszczSosnowskiego(World world, int x, int y) {
        super(world, x, y);
        this.sila = 10;
        this.color = java.awt.Color.MAGENTA;
    }

    @Override
    public Organizm copy(int x, int y) {
        return new BarszczSosnowskiego(this.world, x, y);
    }

    @Override
    public String to_str() {
        return "Barszcz Sosnowskiego";
    }

    @Override
    public void akcja(int zasieg,boolean wech) {
        this.world.killZwierzeNeardy(this.x,this.y);
        super.akcja(zasieg,wech);
    }

    @Override
    public void kolizja(Organizm org) {
        System.out.println(org.to_str() + " zjadl Barszcz Sosnowskiego i umarł");
        super.kolizja(org);
        this.world.removeOrganizm(org);
    }
}
