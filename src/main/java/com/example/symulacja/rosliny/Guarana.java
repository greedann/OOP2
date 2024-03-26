package com.example.symulacja.rosliny;

import com.example.symulacja.Roslina;
import com.example.symulacja.World;
import com.example.symulacja.Organizm;

public class Guarana extends Roslina{
    public Guarana(World world, int x, int y) {
        super(world, x, y);
        this.sila = 0;
        this.color = java.awt.Color.PINK;
    }

    @Override
    public Organizm copy(int x, int y) {
        return new Guarana(this.world, x, y);
    }

    @Override
    public String to_str() {
        return "Guarana";
    }

    @Override
    public void kolizja(Organizm organizm) {
        System.out.println(organizm.to_str() + " zjadl guarane i otrzymal 3 sily");
        organizm.setSila(organizm.getSila() + 3);
        super.kolizja(organizm);
    }
}
