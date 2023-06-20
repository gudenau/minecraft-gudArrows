package net.gudenau.minecraft.arrows.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.gudenau.minecraft.arrows.GudArrows;
import net.gudenau.minecraft.arrows.client.renderer.entity.CustomArrowEntityRenderer;

public final class GudArrowsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        registerRenderers();
    }

    private void registerRenderers() {
        EntityRendererRegistry.register(GudArrows.Entities.EGG_ARROW, (context) -> new CustomArrowEntityRenderer<>(context, GudArrows.identifier("egg_arrow")));
        EntityRendererRegistry.register(GudArrows.Entities.EXPLOSIVE_ARROW, (context) -> new CustomArrowEntityRenderer<>(context, GudArrows.identifier("explosive_arrow")));
        EntityRendererRegistry.register(GudArrows.Entities.FIRE_ARROW, (context) -> new CustomArrowEntityRenderer<>(context, GudArrows.identifier("fire_arrow")));
        EntityRendererRegistry.register(GudArrows.Entities.ICE_ARROW, (context) -> new CustomArrowEntityRenderer<>(context, GudArrows.identifier("ice_arrow")));
        EntityRendererRegistry.register(GudArrows.Entities.LIGHTNING_ARROW, (context) -> new CustomArrowEntityRenderer<>(context, GudArrows.identifier("lightning_arrow")));
    }
}
