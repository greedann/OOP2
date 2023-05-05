public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        World world = new World();
        world.width = 10;
        world.height = 10;
        world.turn = 0;
        world.map = new char[world.width][world.height];
        Czlowiek czlowiek = new Czlowiek(world, 5, 5);
        world.organizmy.add(czlowiek);
        for (int i = 0; i < world.organizmy.size(); i++) {
            System.out.println(world.organizmy.get(i).symbol);
            System.out.println(world.organizmy.get(i).x + " " + world.organizmy.get(i).y);
        }
        System.out.println("Czlowiek: " + czlowiek.x + " " + czlowiek.y);
        System.out.println("Czlowiek: " + czlowiek.symbol);
    }
}