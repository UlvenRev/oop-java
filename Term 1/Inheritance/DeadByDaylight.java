public class DeadByDaylight extends VideoGame {
    String genre;
    boolean crossplayEnabled;

    public DeadByDaylight(String name, String device, boolean wifiAccess, int playerAmount) {
        super(name, device, wifiAccess, playerAmount);
        genre = "Horror";
        crossplayEnabled = false;
    }

    @Override
    public void printGamePlot() {
        System.out.println("In " + name
                + " you and the team need to fix 5 generators while avoiding the killer and escape the facility!");
    }

    @Override
    public void checkAmountOfPlayers() {
        super.checkAmountOfPlayers();
        System.out.println("It's much more interesting to play with friends!");
    }

    public void printConfigInfo() {
        if (crossplayEnabled) {
            System.out.println(name + " now has a crossplay feature!");
        } else {
            System.out.println("For now, " + name + " is not a crossplay game.");
        }
    }

    @Override
    public String toString() {
        String state = "\nName: " + name + "\nDevice: " + deviceName + "\nWifi access: " + wifiAccess
                + "\nAmount of players: " + playerAmount + "\nGenre: " + genre + "\nCrossplay enabled: "
                + crossplayEnabled;
        return state;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj instanceof DeadByDaylight) {
            DeadByDaylight dbd = (DeadByDaylight) obj;
            // Makes sense to only check name, amount of players and genre to know if it's
            // the same game or not
            if (this.name.equals(dbd.name) && this.playerAmount == dbd.playerAmount && this.genre == dbd.genre) {
                return true;
            }
        }
        return false;
    }

    public String getGenre() {
        return genre;
    }

    public boolean getCrossplayEnabled() {
        return crossplayEnabled;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setCrossplay(boolean crossplay) {
        crossplayEnabled = crossplay;
    }
}