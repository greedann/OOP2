package symulacja;

public class Zwierze extends  Organizm{
    public Zwierze(World world, int x, int y) {
        super(world, x, y);
    }

    @Override
    public void akcja(int zasieg,boolean wech) {
        this.wiek++;
        int nx = this.x;
        int ny = this.y;
        int direction = (int) (Math.random() * 4);
        switch (direction) {
            case 0:
                nx += zasieg;
                break;
            case 1:
                nx -= zasieg;
                break;
            case 2:
                ny += zasieg;
                break;
            case 3:
                ny -= zasieg;
                break;
        }
        if (nx >= 0 && nx < this.world.getBlockWidth() && ny >= 0 && ny < this.world.getBlockHeight()) {
            if (!this.world.isOccupied(nx, ny)) {
                this.x = nx;
                this.y = ny;
            } else if (!wech){
                this.world.getOrganizm(nx, ny).kolizja(this);
            }
        }
    }

    @Override
    public void kolizja(Organizm organizm) {
        // check if organizms are of similar type
        if (this.getClass() == organizm.getClass()) {
            // check if they are of the same type
            int[] free = this.world.getFree(this.x, this.y);
            if (free[0] != -1) {
                this.world.addOrganizm(this.copy(free[0], free[1]));
                System.out.println(this.to_str() + " rozmnozyl sie");
            }
        } else if (this.getClass() == Czlowiek.class) {
            this.world.removeOrganizm(organizm);
        } else if (this.getSila() <= organizm.getSila()) {
            organizm.setX(this.x);
            organizm.setY(this.y);
            System.out.println(organizm.to_str() + " zabil " + this.to_str());
            this.world.removeOrganizm(this);
        }
        else {
            System.out.println(this.to_str() + " zabil " + organizm.to_str());
            this.world.removeOrganizm(organizm);
        }


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
