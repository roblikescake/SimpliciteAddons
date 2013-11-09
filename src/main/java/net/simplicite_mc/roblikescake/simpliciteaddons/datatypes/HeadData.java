package net.simplicite_mc.roblikescake.simpliciteaddons.datatypes;

public class HeadData {
    private int dropChance;
    private String owner;
    private String displayName;

    public HeadData(int dropChance, String owner, String displayName) {
        this.dropChance = dropChance;
        this.owner = owner;
        this.displayName = displayName;
    }

    public int getDropChance() {
        return dropChance;
    }

    public String getOwner() {
        return owner;
    }

    public String getDisplayName() {
        return displayName;
    }
}
