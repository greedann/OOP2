import java.lang.reflect.Array;
import java.util.ArrayList;

public class World {
    int width;
    int height;
    int turn;
    char[][] map;
    enum Ruch {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        STOP,
    }
    Ruch ruch;
    ArrayList<Organizm> organizmy = new ArrayList<Organizm>();
    Czlowiek czlowiek;

}
