package net.gudenau.minecraft.arrows;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.gudenau.minecraft.arrows.entity.*;
import net.gudenau.minecraft.arrows.item.CustomArrowItem;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ProjectileDispenserBehavior;
import net.minecraft.entity.*;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public final class GudArrows implements ModInitializer {
    public static final String MOD_ID = "gud_arrows";

    @Override
    public void onInitialize() {
        Items.init();
        Entities.init();
    }

    @NotNull
    public static Identifier identifier(@NotNull String path) {
        return new Identifier(MOD_ID, path);
    }

    public static final class Entities {
        public static final EntityType<EggArrowEntity> EGG_ARROW = arrowType(EggArrowEntity::new);
        public static final EntityType<ExplosiveArrowEntity> EXPLOSIVE_ARROW = arrowType(ExplosiveArrowEntity::new);
        public static final EntityType<FireArrowEntity> FIRE_ARROW = arrowType(FireArrowEntity::new);
        public static final EntityType<IceArrowEntity> ICE_ARROW = arrowType(IceArrowEntity::new);
        public static final EntityType<LightningArrowEntity> LIGHTNING_ARROW = arrowType(LightningArrowEntity::new);

        private static void init() {
            register("egg_arrow", EGG_ARROW);
            register("explosive_arrow", EXPLOSIVE_ARROW);
            register("fire_arrow", FIRE_ARROW);
            register("ice_arrow", ICE_ARROW);
            register("lightning_arrow", LIGHTNING_ARROW);
        }

        private static <T extends ArrowEntity> EntityType<T> arrowType(EntityType.EntityFactory<T> factory) {
            return FabricEntityTypeBuilder.create(SpawnGroup.MISC, factory)
                .dimensions(EntityDimensions.changing(0.5F, 0.5F))
                .trackRangeChunks(5)
                .trackedUpdateRate(20)
                .build();
        }

        private static void register(@NotNull String name, @NotNull EntityType<?> type) {
            Registry.register(Registries.ENTITY_TYPE, identifier(name), type);
        }
    }

    public static final class Items {
        @NotNull
        public static final Item EGG_ARROW = new CustomArrowItem<EggArrowEntity>(new CustomArrowItem.Factory<>() {
            @NotNull
            @Override
            public EggArrowEntity apply(@NotNull World world, @NotNull LivingEntity shooter) {
                return new EggArrowEntity(world, shooter);
            }

            @NotNull
            @Override
            public EggArrowEntity apply(@NotNull World world, @NotNull Position position) {
                return new EggArrowEntity(world, position.getX(), position.getY(), position.getZ());
            }
        }, new FabricItemSettings());
        public static final Item EXPLOSIVE_ARROW = new CustomArrowItem<ExplosiveArrowEntity>(new CustomArrowItem.Factory<>() {
            @NotNull
            @Override
            public ExplosiveArrowEntity apply(@NotNull World world, @NotNull LivingEntity shooter) {
                return new ExplosiveArrowEntity(world, shooter);
            }

            @NotNull
            @Override
            public ExplosiveArrowEntity apply(@NotNull World world, @NotNull Position position) {
                return new ExplosiveArrowEntity(world, position.getX(), position.getY(), position.getZ());
            }
        }, new FabricItemSettings());
        public static final Item FIRE_ARROW = new CustomArrowItem<FireArrowEntity>(new CustomArrowItem.Factory<>() {
            @NotNull
            @Override
            public FireArrowEntity apply(@NotNull World world, @NotNull LivingEntity shooter) {
                return new FireArrowEntity(world, shooter);
            }

            @NotNull
            @Override
            public FireArrowEntity apply(@NotNull World world, @NotNull Position position) {
                return new FireArrowEntity(world, position.getX(), position.getY(), position.getZ());
            }
        }, new FabricItemSettings());
        public static final Item ICE_ARROW = new CustomArrowItem<IceArrowEntity>(new CustomArrowItem.Factory<>() {
            @NotNull
            @Override
            public IceArrowEntity apply(@NotNull World world, @NotNull LivingEntity shooter) {
                return new IceArrowEntity(world, shooter);
            }

            @NotNull
            @Override
            public IceArrowEntity apply(@NotNull World world, @NotNull Position position) {
                return new IceArrowEntity(world, position.getX(), position.getY(), position.getZ());
            }
        }, new FabricItemSettings());
        public static final Item LIGHTNING_ARROW = new CustomArrowItem<LightningArrowEntity>(new CustomArrowItem.Factory<>() {
            @NotNull
            @Override
            public LightningArrowEntity apply(@NotNull World world, @NotNull LivingEntity shooter) {
                return new LightningArrowEntity(world, shooter);
            }

            @NotNull
            @Override
            public LightningArrowEntity apply(@NotNull World world, @NotNull Position position) {
                return new LightningArrowEntity(world, position.getX(), position.getY(), position.getZ());
            }
        }, new FabricItemSettings());

        private static void init() {
            var items = new ArrayList<Item>();
            items.add(register("egg_arrow", EGG_ARROW));
            items.add(register("explosive_arrow", EXPLOSIVE_ARROW));
            items.add(register("fire_arrow", FIRE_ARROW));
            items.add(register("ice_arrow", ICE_ARROW));
            items.add(register("lightning_arrow", LIGHTNING_ARROW));

            ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register((entries) -> items.forEach(entries::add));

            items.stream()
                .filter(CustomArrowItem.class::isInstance)
                .map(CustomArrowItem.class::cast)
                .forEach((arrow) ->
                    DispenserBlock.registerBehavior(arrow, new ProjectileDispenserBehavior(){
                        @Override
                        protected ProjectileEntity createProjectile(World world, Position position, ItemStack stack) {
                            var entity = arrow.createArrow(world, position);
                            entity.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
                            return entity;
                        }
                    })
                );
        }

        private static <T extends Item> T register(@NotNull String name, @NotNull T item) {
            return Registry.register(Registries.ITEM, identifier(name), item);
        }
    }
}
