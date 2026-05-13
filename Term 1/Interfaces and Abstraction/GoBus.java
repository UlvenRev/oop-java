/*
For this class, same as for CityLink and BusEireann, nothing else is needed. Since their methods are identicall and don't differ, they are all
implemented in the BusVendor abstract class and these classes INHERIT from the ABSTRACT class.

Which is why in here the only unique thing I define is the name of the class.
*/

public class GoBus extends BusVendor {
    public GoBus() {
        this.vendorName = "GoBus";
    }
}
