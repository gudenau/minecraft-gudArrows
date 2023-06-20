package net.gudenau.minecraft.arrows.entity;

import net.gudenau.minecraft.arrows.GudArrows;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public final class EggArrowEntity extends CustomArrowEntity {
    public EggArrowEntity(EntityType<? extends EggArrowEntity> entityType, World world) {
        super(entityType, world);
    }

    public EggArrowEntity(World world, LivingEntity owner) {
        super(world, owner);
    }

    public EggArrowEntity(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(GudArrows.Items.EGG_ARROW);
    }

    @Override
    protected void handleCollision(HitResult result, BlockPos pos) {
        int count = 1;
        if (random.nextInt(32) == 0) {
            count = 4;
        }

        var world = getWorld();

        for(int i = 0; i < count; ++i) {
            var chicken = EntityType.CHICKEN.create(world);
            if (chicken == null) {
                continue;
            }

            chicken.setBreedingAge(-24000);
            chicken.refreshPositionAndAngles(getX(), getY(), getZ(), getYaw(), 0);
            world.spawnEntity(chicken);
        }
    }
}
