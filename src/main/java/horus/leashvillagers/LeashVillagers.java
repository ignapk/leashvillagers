package horus.leashvillagers;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

@Mod(modid = LeashVillagers.MODID, name = LeashVillagers.NAME, version = LeashVillagers.VERSION)
public class LeashVillagers
{
    public static final String MODID = "leashvillagers";
    public static final String NAME = "Leash Villagers";
    public static final String VERSION = "0.1.0";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {

    }
    
    
    @Mod.EventBusSubscriber(modid = LeashVillagers.MODID)
    public static class EventHandler {
    
	    private static int entityId = 0;

	    @SubscribeEvent(priority = EventPriority.HIGHEST)
	    public static void replaceVanillaVillager(EntityJoinWorldEvent event) {
	        Entity entity = event.getEntity();
	        World world = event.getWorld();
	        if (!world.isRemote) {
	            if (entity != null && entity instanceof EntityVillager && !(entity instanceof EntityVillagerLeashable)) {
			EntityVillager villager = (EntityVillager) entity;			 
			EntityVillagerLeashable villagerLeashable = new EntityVillagerLeashable(world, villager.getProfession());
			event.setCanceled(true);
			villagerLeashable.setLocationAndAngles(entity.posX, entity.posY, entity.posZ, 0, 0); 
			world.spawnEntity(villagerLeashable);
	  	    }
	        }

	    }
	    
	    @SubscribeEvent
	public static void onRegisterEntitiesEvent(RegistryEvent.Register<EntityEntry> event) {
		event.getRegistry().register(
				EntityEntryBuilder.create()
						.entity(EntityVillagerLeashable.class)
						.id("entity_villager_leashable", entityId++)
						.name("entity_villager_leashable")
						.tracker(160, 2, false)
						.build()
		);
	}
    }
}
