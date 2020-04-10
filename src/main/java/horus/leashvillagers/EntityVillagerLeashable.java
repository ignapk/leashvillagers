package horus.leashvillagers;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;


public class EntityVillagerLeashable extends EntityVillager {

	public EntityVillagerLeashable(World worldIn) {
		super(worldIn);
	}

	public EntityVillagerLeashable(World worldIn, int professionId) {
		super(worldIn, professionId);
	}


	@Override
	public boolean canBeLeashedTo(EntityPlayer player) {
		return true;
	}

}
