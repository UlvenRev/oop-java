public class Main
{
    public static void main(String[] args)
    {
        // Constructing each individual component *outside* the closet class - this is aggregation, because each element is independent of the closet
        Door door = new Door("mirror");
        Hanger[] hangers = {
            new Hanger(), new Hanger(), new Hanger()
        };
        ClothingPiece[] clothes = {
            new ClothingPiece("Shirt", "Cotton"), new ClothingPiece("Joggers", "Fleece"), new ClothingPiece("Jacket", "Leather")
        };
        
        // Passing in the elements to closet - i.e. "composing" the closet out of 3 other parts (objects): door, hangers and clothes
        Closet closet = new Closet(door, hangers, clothes);
        
        // Calling all the closet's functions
        closet.openTheCloset();
        closet.checkForEmptyHangers();
        closet.checkForClothes();
        closet.hangClothes();
        closet.hangClothes();  // Trying to hang something again when you're out of clothes and hangers, to check the "else" statement execution
        closet.closeTheCloset();
    }
}