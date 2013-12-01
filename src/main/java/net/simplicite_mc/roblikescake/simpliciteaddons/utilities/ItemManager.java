package net.simplicite_mc.roblikescake.simpliciteaddons.utilities;

import net.simplicite_mc.roblikescake.simpliciteaddons.SimpliciteAddons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemManager {

	public ItemManager() {
		SimpliciteAddons.p.getServer().addRecipe(getAnimalCatcherRecipe());
	}

	// Create the AnimalCatcher recipe.
	public static ShapedRecipe getAnimalCatcherRecipe() {
		ShapedRecipe AnimalCatcher = new ShapedRecipe(getAnimalCatcher());
		AnimalCatcher.shape(" X ", "XXX", " X ");
		AnimalCatcher.setIngredient('X', Material.EGG);
		return AnimalCatcher;
	}

	// Create the AnimalCatcher item.
	public static ItemStack getAnimalCatcher() {
		ItemStack itemStack = new ItemStack(Material.EGG);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(ChatColor.AQUA + "AnimalCatcher");
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}

	// Create an Animal SpawnEgg.
	public static ItemStack getAnimalSpawnEgg(short entityShort) {
		return (new ItemStack(Material.MONSTER_EGG, 1, entityShort));
	}

	// Create a MobHead.
	public static ItemStack getMobHead(EntityType entityType) {
		ItemStack mobHead = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta skullMeta = (SkullMeta) mobHead.getItemMeta();
		skullMeta.setOwner(HeadManager.getOwner(entityType));
		skullMeta.setDisplayName(HeadManager.getDisplayName(entityType));
		mobHead.setItemMeta(skullMeta);
		return mobHead;
	}

	// Create a PlayerHead.
	public static ItemStack getPlayerHead(String playerName) {
		ItemStack playerHead = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta skullMeta = (SkullMeta) playerHead.getItemMeta();
		skullMeta.setOwner(playerName);
		skullMeta.setDisplayName(playerName + "'s Head");
		playerHead.setItemMeta(skullMeta);
		return playerHead;
	}
}
