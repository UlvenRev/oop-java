public class ClothingPiece
{
    public String type;  // Making this one public so that I can reach it from the Closet class like was done with the Door class
    private String material;
    private boolean hanged;
    
    // Constructor which is asking for a type and a material of the clothing piece
    public ClothingPiece(String type, String material)
    {
        this.type = type;
        this.material = material;
    }
    
    // Accessor to know if the item is hanged or not
    public boolean isHanged() 
    {
        return hanged;
    }
    
    // Mutator to hang it
    public void hang()
    {
        hanged = true;
    }
    
    // A function to check out what clothing piece we are holding
    public void checkClothingPiece() 
    {
        System.out.println("This is a " + type + " made from " + material + ".");
    }
}