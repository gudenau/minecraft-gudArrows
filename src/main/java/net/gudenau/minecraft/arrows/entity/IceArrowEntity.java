package net.gudenau.minecraft.arrows.entity;

import net.gudenau.minecraft.arrows.GudArrows;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.TorchBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public final class IceArrowEntity extends CustomArrowEntity {
    public IceArrowEntity(EntityType<? extends IceArrowEntity> entityType, World world) {
        super(entityType, world);
    }

    public IceArrowEntity(World world, LivingEntity owner) {
        super(world, owner);
    }

    public IceArrowEntity(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(GudArrows.Items.ICE_ARROW);
    }

    @Override
    protected void handleCollision(HitResult result, BlockPos pos) {
        if(result instanceof EntityHitResult entityResult) {
            var entity = entityResult.getEntity();
            entity.setFireTicks(0);
            if(entity instanceof LivingEntity livingEntity) {
                livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20 * 15));
            }
        } else {
            var world = getWorld();

            for (var offset : BlockPos.iterateOutwards(pos, 1, 1, 1)) {
                var state = world.getBlockState(offset);
                var block = state.getBlock();
                System.out.println(offset);

                if(block instanceof FluidBlock fluidBlock) {
                    var fluid = fluidBlock.getFluidState(state);
                    if(fluid.isIn(FluidTags.LAVA)) {
                        world.setBlockState(offset, Blocks.OBSIDIAN.getDefaultState());
                    } else if(fluid.isIn(FluidTags.WATER)) {
                        world.setBlockState(offset, Blocks.ICE.getDefaultState());
                    }
                } else if(block instanceof AbstractFireBlock) {
                    world.removeBlock(offset, false);
                } else if (block instanceof TorchBlock) {
                    world.breakBlock(offset, true);
                } else {
                    //world.setBlockState(offset, Blocks.DIAMOND_BLOCK.getDefaultState());
                }
            }
        }
    }
}
