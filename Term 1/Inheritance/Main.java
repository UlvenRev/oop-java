public class Main {
    public static void main(String[] args) {
        // Both videoGame and spiderMan DO HAVE repetitions but it's only for
        // demostration of how they behave differently depending from what classes you
        // call the methods! You wouldn't instantiate both irl, it's only to demonstrate
        Game videoGame = new VideoGame("Spider-Man", "PS", false, 1);
        VideoGame spiderMan = new SpiderMan("Spider-Man", "PS", false, 1);

        spiderMan.printGameName(); // This can be called from wherever because spiderMan extends VideoGame extends
                                   // Game, and getGameName() is defined in Game class (the most top)
        videoGame.printGamePlot(); // Here, because videoGame extends Game class, it takes the getGamePlot() method
                                   // from the GAME class, which is why we get "name has a plot!"
        // Example of a polymorphism - printGamePlot() was OVERRIDDEN in SpiderMan, so
        // now when we call it on spiderMan it gives a different output than the one
        // called on videoGame
        spiderMan.printGamePlot(); // spiderMan extends VideoGame class which extends Game class, so technically I
                                   // also should get "name has a plot!", but I OVERRIDE this method in SpiderMan
                                   // class, which is why I get a different output
        videoGame.save();
        System.out.println("------------------");

        VideoGame dbd = new DeadByDaylight("Dead By Daylight", "PC", true, 5);

        dbd.printGamePlot(); // Example of a polymorphism
        dbd.checkAmountOfPlayers();
        dbd.save(); // Example of a polymorphism

        System.out.println("------------------");
        // This time I CANNOT write Game boardGame = new BoardGame(...), because I made
        // BoardGame to be abstract - I don't need BoardGame class on its own, but I do
        // need its children to have the method checkAmountOfPlayers() - an abstract
        // method
        BoardGame poker = new Poker("Poker", "paper cards, plastic chips", 3, "Texas Hold 'Em");
        BoardGame dnd = new DnD("Dungeons & Dragons", "wooden board, paper, rolling dice", 3,
                "Nomad Wizards and The Cheapest Spell Book");
        poker.printGameName(); // Inherited all the way up from Game class without being overridden
        poker.checkBoardGameMaterial();
        poker.printGamePlot(); // Example of a polymorphism
        poker.checkAmountOfPlayers(); // This works ONLY
        poker.save(); // Example of a polymorphism
        System.out.println("------------------");
        dnd.printGameName();
        dnd.checkBoardGameMaterial();
        dnd.printGamePlot(); // Example of a polymorphism
        dnd.checkWifiAccess();
        dnd.save(); // Example of a polymorphism
        System.out.println("------------------");

        // Showcase of poker's equals method:
        BoardGame poker2 = new Poker("Poker", "paper cards, plastic chips", 3, "Omaha");
        // For poker 1 type it's "Texas Hold 'Em" and for poker 2 it's "Omaha", so we're
        // gonna get false and "The two pokers are different."
        System.out.println(
                poker.equals(poker2) ? "These two poker games are the same!" : "The two pokers are different.");

        // Showcase of DnD's toString method:
        String state = dnd.toString();
        System.out.println(state);

        // For other concrete classes equals and toString methods work exactly the same!
    }
}