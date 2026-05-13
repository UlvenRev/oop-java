public class Poker extends BoardGame {
    int playerAmount;
    String pokerType;

    public Poker(String name, String material, int playerAmount, String pokerType) {
        super(name, material);
        this.playerAmount = playerAmount;
        this.pokerType = pokerType;
    }

    @Override
    public void checkAmountOfPlayers() {
        System.out.println(name + " requires at least " + playerAmount + " players: a dealer and others as players!");
    }

    @Override
    public void save() {
        System.out.println(name + " is a gambling board game - it can't be saved mid game!");
    }

    @Override
    public void printGamePlot() {
        System.out.println(name + " doesn't have a plot - only money!");
    }

    @Override
    public String toString() {
        String state = "\nName: " + name + "\nMaterial: " + material + "\nAmount of players: " + playerAmount
                + "\nPoker type: " + pokerType;
        return state;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj instanceof Poker) {
            Poker poker = (Poker) obj;
            if (this.name.equals(poker.name) && this.material.equals(poker.material)
                    && this.playerAmount == poker.playerAmount && this.pokerType.equals(poker.pokerType)) {
                return true;
            }
        }
        return false;
    }

    public void checkPokerType() {
        System.out.println("You're playing " + pokerType + "!");
    }

    public int getPlayerAmount() {
        return playerAmount;
    }

    public String getPokerType() {
        return pokerType;
    }

    public void setPlayerAmount(int number) {
        playerAmount = number;
    }

    public void setPokerType(String type) {
        pokerType = type;
    }
}