public class DnD extends BoardGame {
    int playerAmount;
    String campaignName;

    public DnD(String name, String material, int playerAmount, String campaignName) {
        super(name, material);
        this.playerAmount = playerAmount;
        this.campaignName = campaignName;
    }

    @Override
    public void checkAmountOfPlayers() {
        System.out.println(name + " needs at least " + playerAmount
                + " players: the Dungeon Master (narrator) and another player.");
    }

    @Override
    public void printGamePlot() {
        System.out.println(name
                + " is built fully on the plot, which can take you anywhere! The name of your current campaign is \""
                + campaignName + "\" and what will happen depends on the imagination of the Dungeon Master!");
    }

    @Override
    public void save() {
        System.out
                .println(name + "' progress was recorded, you can continue your journey next time with your friends!");
    }

    @Override
    public String toString() {
        String state = "\nName: " + name + "\nMaterial: " + material + "\nAmount of players: " + playerAmount
                + "\nCampaign name: " + campaignName;
        return state;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj instanceof DnD) {
            DnD dnd = (DnD) obj;
            if (this.name.equals(dnd.name) && this.material.equals(dnd.material)
                    && this.playerAmount == dnd.playerAmount && this.campaignName.equals(dnd.campaignName)) {
                return true;
            }
        }
        return false;
    }

    public int getPlayerAmount() {
        return playerAmount;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setPlayerAmount(int number) {
        playerAmount = number;
    }

    public void setCampaignNmae(String name) {
        campaignName = name;
    }
}