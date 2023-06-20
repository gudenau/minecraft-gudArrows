package net.gudenau.minecraft.arrows.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class CustomArrowEntity extends ArrowEntity {
    public CustomArrowEntity(EntityType<? extends CustomArrowEntity> entityType, World world) {
        super(entityType, world);
    }

    public CustomArrowEntity(World world, LivingEntity owner) {
        super(world, owner);
    }

    public CustomArrowEntity(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    @Override
    protected void onBlockHit(BlockHitResult result) {
        if(!getWorld().isClient()) {
            var pos = result.getPos();
            handleCollision(result, new BlockPos((int) Math.floor(pos.x), (int) Math.floor(pos.y), (int) Math.floor(pos.z)));
        }
        discard();
    }

    @Override
    protected void onEntityHit(EntityHitResult result) {
        if(!getWorld().isClient()) {
            var pos = result.getPos();
            handleCollision(result, new BlockPos((int) Math.floor(pos.x), (int) Math.floor(pos.y), (int) Math.floor(pos.z)));
        }
        discard();
    }

    protected abstract void handleCollision(HitResult hitResult, BlockPos pos);
}
