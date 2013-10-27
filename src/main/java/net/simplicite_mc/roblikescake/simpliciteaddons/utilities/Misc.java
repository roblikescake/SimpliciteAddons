package net.simplicite_mc.roblikescake.simpliciteaddons.utilities;

import org.bukkit.entity.EntityType;

public class Misc {

    /**
     * Check if I Want Entity Catchable
     */
    public static boolean isCatchable(EntityType entityType) {
        switch (entityType) {
            case CHICKEN:
            case COW:
            case OCELOT:
            case PIG:
            case SHEEP:
            case HORSE:
            case SQUID:
            case VILLAGER:
            case MUSHROOM_COW:
            case WOLF:
                return true;
            default:
                return false;
        }
    }
}
