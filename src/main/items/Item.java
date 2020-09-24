package main.items;

public abstract class Item {
    //todo - define additional item properties not specific to implementations, as needed

    private final String NAME;

    //todo - replace with a builder pattern?
    public Item(String name) {
        NAME = name;
    }

    public String getName() {
        return NAME;
    }
}
