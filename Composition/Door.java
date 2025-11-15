public class Door
{
    public String type;  // Mirror door or a simple wooden door. Making this variable public unlike others to be able to reach is from Closet class by saying door.type
    private boolean open;
    
    // Constructor for a Door class
    public Door(String type) 
    {
        this.type = type;
        open = false;  // Closed from the start
    }
    
    // An accessor to see if the door is opened
    public boolean isOpened()
    {
        return open;
    }
    
    // A mutator to open or close the door
    public void toggleOpenState() 
    {
        open = !open;
    }
}