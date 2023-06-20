package net.gudenau.minecraft.arrows.entity;

import net.gudenau.minecraft.arrows.GudArrows;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public final class ExplosiveArrowEntity extends CustomArrowEntity {
    public ExplosiveArrowEntity(EntityType<? extends ExplosiveArrowEntity> entityType, World world) {
        super(entityType, world);
    }

    public ExplosiveArrowEntity(World world, LivingEntity owner) {
        super(world, owner);
    }

    public ExplosiveArrowEntity(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(GudArrows.Items.EXPLOSIVE_ARROW);
    }

    @Override
    protected void handleCollision(HitResult result, BlockPos pos) {
        getWorld().createExplosion(this, getX(), getY(), getZ(), isCritical() ? 6 : 3, World.ExplosionSourceType.TNT);
    }
}
