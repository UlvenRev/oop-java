public class Hanger
{
    private boolean empty;  // The only thing to know about the hanger if it's empty or not
    
    // Constructor: by default the hanger is empty
    public Hanger()
    {
        empty = true;
    }
    
    // An accessor to see if it's empty
    public boolean isEmpty() 
    {
        return empty;
    }
}
