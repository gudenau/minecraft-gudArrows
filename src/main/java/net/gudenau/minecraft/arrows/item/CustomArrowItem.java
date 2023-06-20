package net.gudenau.minecraft.arrows.item;

import net.gudenau.minecraft.arrows.entity.CustomArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public final class CustomArrowItem<T extends CustomArrowEntity> extends ArrowItem {
    private final Factory<T> factory;

    public CustomArrowItem(Factory<T> factory, Settings settings) {
        super(settings);

        this.factory = factory;
    }

    public CustomArrowEntity createArrow(World world, Position position) {
        return factory.apply(world, position);
    }

    @Override
    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
        return factory.apply(world, shooter);
    }

    public interface Factory<T extends CustomArrowEntity> {
        @NotNull
        T apply(@NotNull World world, @NotNull LivingEntity shooter);

        @NotNull
        T apply(@NotNull World world, @NotNull Position position);
    }
}
