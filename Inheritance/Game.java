public abstract class Game // Have abstract here for "abstract void save();" to work
{
    protected String name;
    protected String plot;

    public Game(String name) {
        this.name = name;
    }

    public void printGameName() {
        System.out.println("This game is called " + name + "!");
    }

    public void printGamePlot() {
        System.out.println(name + " has a plot!");
    }

    abstract public void save(); // Will NOT WORK if the parent class is not abstract
    // Basically, every game MUST have a save method, but they will define
    // themselves how to save games (so an overriden method)
}