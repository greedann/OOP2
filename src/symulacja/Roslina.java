package symulacja;

public class Roslina extends Organizm{
    public Roslina(World world, int x, int y) {
        super(world, x, y);
        sila = 0;
        inicjatywa = 0;
    }

    @Override
    public void akcja(int zasieg,boolean wech) {
        int dice = (int) (Math.random() * 5);
        if (dice ==0)
        {
            int newX = this.x;
            int newY = this.y;
            int direction = (int) (Math.random() * 4);
            switch (direction)
            {
                case 0:
                    newX--;
                    break;
                case 1:
                    newX++;
                    break;
                case 2:
                    newY--;
                    break;
                case 3:
                    newY++;
                    break;
            }
            if (newX < 0 ||  newX >= this.world.getWidth() || newY < 0 || newY >= this.world.getHeight() || this.world.isOccupied(newX, newY))
            {
                return;
            }
        else
            {
                this.world.addOrganizm(this.copy(newX, newY));
            }

        }
    }

    @Override
    public void kolizja(Organizm organizm) {
        organizm.setX(this.x);
        organizm.setY(this.y);
        this.world.removeOrganizm(this);
    }

    @Override
    public String to_str() {
        return null;
    }

    @Override
    public Organizm copy(int x, int y) {
        return null;
    }
}
