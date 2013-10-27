package net.simplicite_mc.roblikescake.simpliciteaddons.utilities;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.SpawnEgg;

import net.simplicite_mc.roblikescake.simpliciteaddons.SimpliciteAddons;

public class ItemManager {

    public ItemManager() {
        SimpliciteAddons.p.getServer().addRecipe(getAnimalCatcherRecipe());
    }

    /**
     * Create AnimalCatcher Recipe
     */
    public static ShapedRecipe getAnimalCatcherRecipe() {
        ShapedRecipe AnimalCatcher = new ShapedRecipe(getAnimalCatcher());
        AnimalCatcher.shape(" X ", "XXX", " X ");
        AnimalCatcher.setIngredient('X', Material.EGG);

        return AnimalCatcher;
    }

    /**
     * Create AnimalCatcher Item
     */
    public static ItemStack getAnimalCatcher() {
        ItemStack itemStack = new ItemStack(Material.EGG);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.AQUA + "AnimalCatcher");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    /**
     * Create Animal SpawnEgg for MobCatcher
     */
    public static ItemStack getAnimalSpawnEgg(EntityType entityType) {
        ItemStack animalSpawnEgg = new ItemStack(Material.MONSTER_EGG);
        animalSpawnEgg.setData(new SpawnEgg(entityType));
        return animalSpawnEgg;
    }

    /**
     * Create MobHead
     */
    public static ItemStack getMobHead(EntityType entityType) {
        ItemStack mobHead = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta skullMeta = (SkullMeta) mobHead.getItemMeta();
        skullMeta.setOwner(HeadManager.getOwner(entityType));
        skullMeta.setDisplayName(HeadManager.getDisplayName(entityType));
        mobHead.setItemMeta(skullMeta);
        return mobHead;
    }

    /**
     * Create PlayerHead
     */
    public static ItemStack getPlayerHead(String playerName) {
        ItemStack playerHead = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta skullMeta = (SkullMeta) playerHead.getItemMeta();
        skullMeta.setOwner(playerName);
        skullMeta.setDisplayName(playerName + "'s Head");
        playerHead.setItemMeta(skullMeta);
        return playerHead;
    }
}