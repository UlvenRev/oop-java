public class SpiderMan extends VideoGame {
    // I don't declare a name, deviceName and wifi because all of this is ALREADY in
    // the PARENT class VideoGame, so I can just reach for it from here
    String mainCharacter;
    String city;

    public SpiderMan(String name, String deviceName, boolean wifiAccess, int playerAmount) {
        super(name, deviceName, wifiAccess, playerAmount);
        mainCharacter = "Peter Parker";
        city = "New York";
    }

    @Override
    public void printGamePlot() {
        System.out
                .println("In " + name + " you play for " + mainCharacter + " who is trying to save " + city
                        + " from the villains!");
    }

    @Override
    public void checkAmountOfPlayers() {
        super.checkAmountOfPlayers(); // Takes the initial output of the checkAmountOfPlayers() method from the parent
                                      // VideoGame class
        System.out.println("You can enjoy the narrative of the game without distractions!"); // + this additional output
    }

    @Override
    public String toString() {
        String state = "\nName: " + name + "\nDevice: " + deviceName + "\nWifi access: " + wifiAccess
                + "\nAmount of players: " + playerAmount + "\nCity: " + city + "\nMain Character: " + mainCharacter;
        return state;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj instanceof SpiderMan) {
            SpiderMan spiderMan = (SpiderMan) obj;
            if (this.name.equals(spiderMan.name) && this.playerAmount == spiderMan.playerAmount
                    && this.mainCharacter == spiderMan.mainCharacter) {
                return true;
            }
        }
        return false;
    }

    public String getMainCharacter() {
        return mainCharacter;
    }

    public String getCity() {
        return city;
    }

    public void setMainCharacter(String name) {
        mainCharacter = name;
    }

    public void setCity(String name) {
        city = name;
    }
}