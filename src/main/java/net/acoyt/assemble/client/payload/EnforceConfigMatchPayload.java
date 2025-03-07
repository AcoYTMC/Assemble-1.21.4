package net.acoyt.assemble.client.payload;

import net.acoyt.assemble.Assemble;
import net.acoyt.assemble.AssembleConfig;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static net.minecraft.util.Formatting.*;

public record EnforceConfigMatchPayload(int encoding) implements CustomPayload {
    public static final CustomPayload.Id<EnforceConfigMatchPayload> ID = new Id<>(Assemble.id("enforce_config_match"));
    public static final PacketCodec<PacketByteBuf, EnforceConfigMatchPayload> CODEC = PacketCodec.tuple(PacketCodecs.VAR_INT, EnforceConfigMatchPayload::encoding, EnforceConfigMatchPayload::new);

    private static final Text DISCONNECT_TEXT = Text.literal("The Server and Client Configurations do not match").formatted(new Formatting[]{BOLD, DARK_RED});

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public static void send(ServerPlayerEntity player, int encoding) {
        ServerPlayNetworking.send(player, new EnforceConfigMatchPayload(encoding));
    }

    public static class Receiver implements ClientPlayNetworking.PlayPayloadHandler<EnforceConfigMatchPayload> {
        @Override
        public void receive(EnforceConfigMatchPayload payload, ClientPlayNetworking.Context context) {
            if (AssembleConfig.encode() != payload.encoding()) {
                context.player().networkHandler.getConnection().disconnect(DISCONNECT_TEXT);
            }
        }
    }
}
