package symulacja;

import java.awt.*;

abstract public class Organizm {
    protected int x;
    protected int y;
    protected Color color;
    protected int sila;
    protected int inicjatywa;
    protected int wiek;
    protected World world;
    public Organizm(World world, int x, int y) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.wiek = 0;
    }
    public abstract void akcja(int zasieg,boolean wech);
    public abstract void kolizja(Organizm organizm);
    public abstract String to_str();
    public abstract Organizm copy(int x, int y);

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSila() {
        return sila;
    }

    public int getInicjatywa() {
        return inicjatywa;
    }

    public int getWiek() {
        return wiek;
    }

    public Color getColor() {
        return color;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y=y;
    }

    public void setSila(int sila) {
        this.sila = sila;
    }

    public void setWiek(int wiek) {
        this.wiek = wiek;
    }


}
