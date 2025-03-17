package net.acoyt.assemble;

import net.acoyt.assemble.client.payload.EnforceConfigMatchPayload;
import net.acoyt.assemble.init.ModBlocks;
import net.acoyt.assemble.init.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.EmptyEntityRenderer;

public class AssembleClient implements ClientModInitializer {
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.SEAT, EmptyEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.CHARGED_COPPER, EmptyEntityRenderer::new);

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), new Block[]{ModBlocks.CHICKEN_BUCKET, ModBlocks.FLOWERING_VINE, ModBlocks.LEGAL_BRICK});

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getTranslucent(), new Block[]{ModBlocks.COPPER_GLASS});

        // Enforce Configuration Match
        ClientPlayNetworking.registerGlobalReceiver(EnforceConfigMatchPayload.ID, new EnforceConfigMatchPayload.Receiver());

        // Gooberfication
        FabricLoader.getInstance().getModContainer(Assemble.MOD_ID).ifPresent(modContainer -> ResourceManagerHelper.registerBuiltinResourcePack(Assemble.id("gooberfication"), modContainer, ResourcePackActivationType.NORMAL));
    }
}
