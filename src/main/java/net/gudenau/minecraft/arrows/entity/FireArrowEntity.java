package net.gudenau.minecraft.arrows.entity;

import net.gudenau.minecraft.arrows.GudArrows;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public final class FireArrowEntity extends CustomArrowEntity {
    public FireArrowEntity(EntityType<? extends FireArrowEntity> entityType, World world) {
        super(entityType, world);
    }

    public FireArrowEntity(World world, LivingEntity owner) {
        super(world, owner);
    }

    public FireArrowEntity(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(GudArrows.Items.FIRE_ARROW);
    }

    @Override
    protected void handleCollision(HitResult result, BlockPos pos) {
        if(result instanceof EntityHitResult entityResult) {
            entityResult.getEntity().setOnFireFor(15);
        } else {
            var world = getWorld();
            var state = Blocks.FIRE.getDefaultState();

            for (var offset : BlockPos.iterateOutwards(pos, 1, 1, 1)) {
                if (world.getBlockState(offset).isAir() && state.canPlaceAt(world, offset)) {
                    world.setBlockState(offset, state);
                }
            }
        }
    }
}
