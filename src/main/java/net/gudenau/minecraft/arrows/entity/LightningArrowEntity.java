package net.gudenau.minecraft.arrows.entity;

import net.gudenau.minecraft.arrows.GudArrows;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public final class LightningArrowEntity extends CustomArrowEntity {
    public LightningArrowEntity(EntityType<? extends LightningArrowEntity> entityType, World world) {
        super(entityType, world);
    }

    public LightningArrowEntity(World world, LivingEntity owner) {
        super(world, owner);
    }

    public LightningArrowEntity(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(GudArrows.Items.LIGHTNING_ARROW);
    }

    @Override
    protected void handleCollision(HitResult hitResult, BlockPos pos) {
        EntityType.LIGHTNING_BOLT.spawn((ServerWorld) getWorld(), getBlockPos(), SpawnReason.TRIGGERED);
    }
}
