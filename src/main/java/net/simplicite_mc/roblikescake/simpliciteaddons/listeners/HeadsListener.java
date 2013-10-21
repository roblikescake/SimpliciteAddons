package net.simplicite_mc.roblikescake.simpliciteaddons.listeners;

import net.simplicite_mc.roblikescake.simpliciteaddons.SimpliciteAddons;
import net.simplicite_mc.roblikescake.simpliciteaddons.utilities.HeadData;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.HashMap;
import java.util.Random;

public class HeadsListener implements Listener {
    public SimpliciteAddons pl;
    HashMap<EntityType, HeadData> headData;

    public HeadsListener(SimpliciteAddons pl) {
        this.pl = pl;
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



    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onEntityDeath(EntityDeathEvent e) {
        if (e.getEntity().getKiller() instanceof Player) {
            EntityType entityType = e.getEntityType();
            Location entLoc = e.getEntity().getLocation();
            Random random = new Random();
            int diceRoll = random.nextInt(100);
            Player killer = e.getEntity().getKiller();
            String plPrefix = ChatColor.BLACK + "[" + ChatColor.AQUA + "SMC" + ChatColor.GRAY + "-" + ChatColor.DARK_AQUA + "Heads" + ChatColor.BLACK + "] ";

            if (e.getEntity() instanceof Monster) {
                if (diceRoll <= headData.get(entityType).getDropChance()) {
                    ItemStack mobHead = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                    SkullMeta skullMeta = (SkullMeta) mobHead.getItemMeta();
                    skullMeta.setOwner(headData.get(entityType).getOwner());
                    skullMeta.setDisplayName(headData.get(entityType).getDisplayName());
                    mobHead.setItemMeta(skullMeta);

                    entLoc.getWorld().dropItemNaturally(entLoc, mobHead);
                    killer.sendMessage(plPrefix + ChatColor.GREEN + "A " + ChatColor.BLUE + headData.get(entityType).getDisplayName() + ChatColor.GREEN + " dropped!");
                }
            } else if (e.getEntity() instanceof Player) {
                if (diceRoll <= 10) {
                    String playerName = ((Player) e.getEntity()).getName();
                    ItemStack playerHead = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                    SkullMeta skullMeta = (SkullMeta) playerHead.getItemMeta();
                    skullMeta.setOwner(playerName);
                    skullMeta.setDisplayName(playerName + "'s Head");
                    playerHead.setItemMeta(skullMeta);

                    entLoc.getWorld().dropItemNaturally(entLoc, playerHead);
                    pl.getServer().broadcastMessage(plPrefix + ChatColor.BLUE + playerName + ChatColor.GREEN + " was beheaded by " + ChatColor.BLUE + killer.getName() + ChatColor.GREEN  + "!");
                }
            }

        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onSkullPickup(PlayerPickupItemEvent e) {
        if (e.getItem().getItemStack().getType().equals(Material.SKULL_ITEM)) {
            ItemStack skull = e.getItem().getItemStack();
            SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
            for (HeadData h : headData.values()) {
                if (h.getOwner().equals(skullMeta.getOwner())) {
                    skullMeta.setDisplayName(h.getDisplayName());
                    skull.setItemMeta(skullMeta);
                }
            }
        }
    }
}