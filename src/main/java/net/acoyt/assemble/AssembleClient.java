package net.acoyt.assemble;

import net.acoyt.assemble.init.ModBlocks;
import net.acoyt.assemble.init.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.EmptyEntityRenderer;

public class AssembleClient implements ClientModInitializer {
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.SEAT, EmptyEntityRenderer::new);

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), new Block[]{ModBlocks.CHICKEN_BUCKET, ModBlocks.FLOWERING_VINE});

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getTranslucent(), ModBlocks.COPPER_GLASS);
    }
}
