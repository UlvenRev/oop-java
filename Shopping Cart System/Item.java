public class Item
{
    private String name;
    private double price;
    private long itemId;
    
    public Item(String itemName, double itemPrice, long id)
    {
        name = itemName;
        price = itemPrice;
        itemId = id;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setPrice(double price)
    {
        this.price = price;
    }
    
    public double getPrice()
    {
        return price;
    }
    
    public long getID()
    {
        return itemId;
    }
}