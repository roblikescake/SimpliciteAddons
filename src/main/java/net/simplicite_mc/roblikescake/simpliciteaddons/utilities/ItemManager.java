package net.simplicite_mc.roblikescake.simpliciteaddons.utilities;

import net.simplicite_mc.roblikescake.simpliciteaddons.SimpliciteAddons;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.SpawnEgg;

import java.util.HashMap;

public class ItemManager {
    public SimpliciteAddons pl;
    public static HashMap<EntityType, HeadData> headData;

    public ItemManager(SimpliciteAddons pl) {
        this.pl = pl;
        pl.getServer().addRecipe(ItemManager.getAnimalCatcherRecipe());
        headData = new HashMap<EntityType, HeadData>();
        headData.put(EntityType.CHICKEN, new HeadData(2, "MHF_Chicken", "Chicken Head"));
        headData.put(EntityType.COW, new HeadData(2, "MHF_Cow", "Cow Head"));
        headData.put(EntityType.OCELOT, new HeadData(3, "MHF_Ocelot", "Ocelot Head"));
        headData.put(EntityType.PIG, new HeadData(2, "MHF_Pig", "Pig Head"));
        headData.put(EntityType.SHEEP, new HeadData(2, "MHF_Sheep", "Sheep Head"));
        headData.put(EntityType.HORSE, new HeadData(2, "gavertoso", "Horse Head"));
        headData.put(EntityType.SQUID, new HeadData(3, "MHF_Squid", "Squid Head"));
        headData.put(EntityType.BAT, new HeadData(5, "bozzobrain", "Bat Head"));
        headData.put(EntityType.VILLAGER, new HeadData(3, "MHF_Villager", "Villager Head"));
        headData.put(EntityType.MUSHROOM_COW, new HeadData(2, "MHF_MushromCow", "Mooshroom Head"));
        headData.put(EntityType.ENDERMAN, new HeadData(3, "MHF_Enderman", "Enderman Head"));
        headData.put(EntityType.WOLF, new HeadData(2, "Budwolf", "Wolf Head"));
        headData.put(EntityType.PIG_ZOMBIE, new HeadData(4, "MHF_PigZombie", "Zombie Pigman Head"));
        headData.put(EntityType.BLAZE, new HeadData(3, "MHF_Blaze", "Blaze Head"));
        headData.put(EntityType.CAVE_SPIDER, new HeadData(2, "MHF_CaveSpider", "Cave Spider Head"));
        headData.put(EntityType.CREEPER, new HeadData(4, "ILuv2Kill", "Creeper Head"));
        headData.put(EntityType.GHAST, new HeadData(4, "MHF_Ghast", "Ghast Head"));
        headData.put(EntityType.MAGMA_CUBE, new HeadData(3, "MHF_LavaSlime", "Magma Cube Head"));
        headData.put(EntityType.SILVERFISH, new HeadData(3, "AlexVMiner", "Silverfish Head"));
        headData.put(EntityType.SKELETON, new HeadData(2, "skeleton639", "Skeleton Skull"));
        headData.put(EntityType.SLIME, new HeadData(3, "HappyHappyMan", "Slime Head"));
        headData.put(EntityType.SPIDER, new HeadData(2, "MHF_Spider", "Spider Head"));
        headData.put(EntityType.WITCH, new HeadData(5, "scrafbrothers4", "Witch Head"));
        headData.put(EntityType.ZOMBIE, new HeadData(2, "Zombie183", "Zombie Head"));
        headData.put(EntityType.SNOWMAN, new HeadData(3, "scraftbrothers2", "Snowman Head"));
        headData.put(EntityType.IRON_GOLEM, new HeadData(3, "MHF_Golem", "Iron Golem Head"));
        headData.put(EntityType.ENDER_DRAGON, new HeadData(10, "KingEndermen", "EnderDragon Head"));
        headData.put(EntityType.WITHER, new HeadData(10, "WitherGamer", "Wither Head"));
    }


    // Create AnimalCatcher Recipe
    public static ShapedRecipe getAnimalCatcherRecipe() {
        ShapedRecipe AnimalCatcher = new ShapedRecipe(getAnimalCatcher());
        AnimalCatcher.shape(" X ", "XXX", " X ");
        AnimalCatcher.setIngredient('X', Material.EGG);

        return AnimalCatcher;
    }

    // Create AnimalCatcher Item
    public static ItemStack getAnimalCatcher() {
        ItemStack itemStack = new ItemStack(Material.EGG);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.AQUA + "AnimalCatcher");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    // Create Animal SpawnEgg for MobCatcher
    public static ItemStack getAnimalSpawnEgg(EntityType entityType) {
        ItemStack animalSpawnEgg = new ItemStack(Material.MONSTER_EGG);
        animalSpawnEgg.setData(new SpawnEgg(entityType));
        return animalSpawnEgg;
    }

    // Create MobHead
    public static ItemStack getMobHead(EntityType entityType) {
        ItemStack mobHead = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta skullMeta = (SkullMeta) mobHead.getItemMeta();
        skullMeta.setOwner(headData.get(entityType).getOwner());
        skullMeta.setDisplayName(headData.get(entityType).getDisplayName());
        mobHead.setItemMeta(skullMeta);
        return mobHead;
    }

    // Create PlayerHead
    public static ItemStack getPlayerHead(String playerName) {
        ItemStack playerHead = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta skullMeta = (SkullMeta) playerHead.getItemMeta();
        skullMeta.setOwner(playerName);
        skullMeta.setDisplayName(playerName + "'s Head");
        playerHead.setItemMeta(skullMeta);
        return playerHead;
    }
}