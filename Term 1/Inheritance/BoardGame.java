public abstract class BoardGame extends Game {
    protected final boolean wifiAccess = false; // final means this variable will never change and be constant - you
                                                // won't ever
    // need wifi for board games
    protected String material;

    public BoardGame(String name, String material) {
        super(name);
        this.material = material;
    }

    public void checkWifiAccess() {
        System.out.println(name + " doesn't ever need a wifi access. Enjoy playing it anytime!");
    }

    public void checkBoardGameMaterial() {
        System.out.println("This game is made from " + material + "!");
    }

    // Same method as for video games, but here it is implemented through
    // abstraction
    public abstract void checkAmountOfPlayers(); // Each board game is supposed to have this method, but they will
                                                 // define the details themselves, which is why this method is abstract
                                                 // - which is why the parent class BoardGame is also abstract - to be
                                                 // able to this method later
}