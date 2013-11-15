package net.simplicite_mc.roblikescake.simpliciteaddons.utilities;

import net.simplicite_mc.roblikescake.simpliciteaddons.SimpliciteAddons;
import net.simplicite_mc.roblikescake.simpliciteaddons.datatypes.HeadData;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.HashMap;
import java.util.Random;

public class HeadManager {
	public static HashMap<EntityType, HeadData> headData;

	public HeadManager() {
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
		headData.put(EntityType.SKELETON, new HeadData(2, "SkeletonDSP", "Skeleton Skull"));
		headData.put(EntityType.SLIME, new HeadData(3, "HappyHappyMan", "Slime Head"));
		headData.put(EntityType.SPIDER, new HeadData(2, "MHF_Spider", "Spider Head"));
		headData.put(EntityType.WITCH, new HeadData(5, "scrafbrothers4", "Witch Head"));
		headData.put(EntityType.ZOMBIE, new HeadData(2, "Zombie183", "Zombie Head"));
		headData.put(EntityType.SNOWMAN, new HeadData(3, "scraftbrothers2", "Snowman Head"));
		headData.put(EntityType.IRON_GOLEM, new HeadData(3, "MHF_Golem", "Iron Golem Head"));
		headData.put(EntityType.ENDER_DRAGON, new HeadData(10, "KingEndermen", "EnderDragon Head"));
		headData.put(EntityType.WITHER, new HeadData(10, "WitherGamer", "Wither Head"));
	}

	/**
	 * Used for dropping the head of an entity.
	 * <p/>
	 *
	 * @param entity The entity to drop it's head
	 * @param killer The entity's killer
	 */
	public static void dropHeads(Entity entity, Player killer) {
		EntityType entityType = entity.getType();
		HeadData head = headData.get(entityType);

		int lootBonus = 0;

		if (killer.getItemInHand() != null) {
			lootBonus = killer.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS) * 2;
		}

		Location entityLocation = entity.getLocation();
		Random random = new Random();
		int diceRoll = random.nextInt(100);

		if (entity instanceof Player) {
			if (diceRoll <= (10 + lootBonus)) {
				String playerName = ((Player) entity).getName();
				String killerName = killer.getName();

				entityLocation.getWorld().dropItemNaturally(entityLocation, ItemManager.getPlayerHead(playerName));
				SimpliciteAddons.p.getServer().broadcastMessage(MessageManager.getPlayerBeheadedMessage(playerName, killerName));
			}
		} else if (head != null && diceRoll <= (head.getDropChance() + lootBonus)) {
			String mobHeadDisplayName = head.getDisplayName();

			entityLocation.getWorld().dropItemNaturally(entityLocation, ItemManager.getMobHead(entityType));
			killer.sendMessage(MessageManager.getMobHeadDroppedMessage(mobHeadDisplayName));
		}
	}

	/**
	 * Applies HeadData to a Skull Item upon pickup.
	 * <p/>
	 *
	 * @param item The item to apply HeadData to
	 */
	public static void applyHeadData(Item item) {
		if (!item.getItemStack().getType().equals(Material.SKULL_ITEM)) {
			return;
		}

		ItemStack itemStack = item.getItemStack();
		SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();

		for (HeadData head : HeadManager.headData.values()) {
			if (head.getOwner().equals(skullMeta.getOwner())) {
				skullMeta.setDisplayName(head.getDisplayName());
				itemStack.setItemMeta(skullMeta);
			}
		}
	}

	/**
	 * Gets Owner for MobHead.
	 * <p/>
	 *
	 * @param entityType The entity
	 * @return the Owner
	 */
	public static String getOwner(EntityType entityType) {
		return headData.get(entityType).getOwner();
	}

	/**
	 * Gets DisplayName for MobHead.
	 * <p/>
	 *
	 * @param entityType The entity
	 * @return the DisplayName
	 */
	public static String getDisplayName(EntityType entityType) {
		return headData.get(entityType).getDisplayName();
	}
}
