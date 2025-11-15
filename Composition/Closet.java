public class Closet 
{
    // These are the parts the closet is going to "own" 
    private Door door;
    private Hanger[] hangers;
    private ClothingPiece[] clothes;
    
    // Constructing a closet with pieces passed in, i.e. they were created outside of this class - i.e. Aggregation! 
    // The closet is composed of these elements, it "has" them ("has-a" relationship, i.e. composition), but doesn't control them. If closet is removed, 
    // you'll still have doors, hangers and clothes 
    public Closet(Door door, Hanger[] hangers, ClothingPiece[] clothes) 
    {
        this.door = door;
        this.hangers = hangers;
        this.clothes = clothes;
    }
    
    // A function to open the closet
    public void openTheCloset() 
    {
        if (!door.isOpened())  // Door's class accessor
        {
            door.toggleOpenState();  // Toggle the state of the door for the future reference
            System.out.println("You opened the " + door.type + " door. The closet is now opened.");
        } 
        else  // In case the closet was already opened
        {
            System.out.println("The closet is already opened.");
        }
    }
    
    // A function to check for empty hangers - returns a boolean for future reference to know if you can hang more items inside the closet
    public boolean checkForEmptyHangers()
    {
        if (!door.isOpened())  // In case you haven't opened the door before checking the hangers, it'll open automatically
        {
            System.out.println("The " + door.type + " door wasn't opened, opening now...");
            door.toggleOpenState();
        }
        boolean allEmpty = true;  // A boolean to return
        for (Hanger item : hangers)   // Taking *each* item of the hangers list and working with it - same logic goes for other for loops
        {
            if (!item.isEmpty())  // Hanger's class accessor - tells if the current hanger we're looking at in hangers list is empty or not
            {
                allEmpty = false;
                System.out.println("This hanger is not empty...");
            }
        }
        return allEmpty;
    }
    
    // A function to take a look at all the clothes you have
    public void checkForClothes() 
    {
        for (ClothingPiece item : clothes)
        {
            item.checkClothingPiece();  // Calling ClothingPiece's class function that prints out what the item is, i.e. its type and material
        }
    }
    
    // A function to hang clothes
    public void hangClothes()
    {
        for (ClothingPiece item : clothes)  // For each item we check:
        {
            if (!item.isHanged() && checkForEmptyHangers())  // if it's not hanged yet and if there's place to hang it (checkForEmptyHangers() returns )
            {
                item.hang();
                System.out.println(item.type + " item was hanged!");
            }
            else 
            {
                System.out.println("No empty hangers to use or all the clothes are hanged!");
                break;  // To not repeat it for every item since we have a for loop here
            }
        }
    } 
    
    // A function to close the closet
    public void closeTheCloset()
    {
        if (!door.isOpened())  // In case it's already closed
        {
           System.out.println("The closet is already closed."); 
        } 
        else 
        {
            door.toggleOpenState();
            System.out.println("Sucessfully closed the closet!");
        }
    }
}