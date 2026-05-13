public class VideoGame extends Game {
    String deviceName;
    int playerAmount;
    boolean wifiAccess;
    boolean saved;

    public VideoGame(String name, String deviceName, boolean wifiAccess, int playerAmount) {
        super(name);
        this.deviceName = deviceName;
        this.wifiAccess = wifiAccess;
        this.playerAmount = playerAmount;
    }

    @Override
    public void save() {
        saved = true;
        System.out.println(name + " was saved on your " + deviceName + "!");
    }

    public void checkWifiAccess() {
        if (wifiAccess) {
            System.out.println(name + " needs wifi for you to play!");
        } else {
            System.out.println(name + " doesn't need wifi for you to play!");
        }
    }

    public void checkAmountOfPlayers() {
        System.out.println(name + " is for " + playerAmount + " player(s)!");
    }

    public String getDeviceName() {
        return deviceName;
    }

    public int getPlayerAmount() {
        return playerAmount;
    }

    public boolean getWifiAccess() {
        return wifiAccess;
    }

    public boolean getSaved() {
        return saved;
    }

    public void setDeviceName(String name) {
        deviceName = name;
    }

    public void setPlayerAmount(int number) {
        playerAmount = number;
    }

    public void setWifiAccess(boolean wifi) {
        wifiAccess = wifi;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }
}