package asset.items;

public class Item {
    //todo - define additional item properties not specific to implementations, as needed

    private final String NAME;
    //all EquipableItem itemIDs should be lower than all other itemIDs
    int itemID;
    int pricetag;

    //todo - replace with a builder pattern?
    public Item(int id, String name, int price) {
        itemID = id;
        NAME = name;
        pricetag = price;
    }

    public String getName() {
        return NAME;
    }

    public int getItemID() {
        return itemID;
    }

    public int getPricetag() {
        return pricetag;
    }
}
