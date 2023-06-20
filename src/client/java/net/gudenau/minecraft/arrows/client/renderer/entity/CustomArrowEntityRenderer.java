package net.gudenau.minecraft.arrows.client.renderer.entity;

import net.gudenau.minecraft.arrows.entity.CustomArrowEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public final class CustomArrowEntityRenderer<T extends CustomArrowEntity> extends ProjectileEntityRenderer<T> {
    @NotNull
    private final Identifier texture;

    public CustomArrowEntityRenderer(@NotNull EntityRendererFactory.Context context, @NotNull Identifier texture) {
        super(context);

        this.texture = texture;
    }

    @Override
    public Identifier getTexture(CustomArrowEntity entity) {
        return texture;
    }
}
