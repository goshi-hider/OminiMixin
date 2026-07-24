package dev.goshi.omnimixin.internal.network;

import dev.goshi.OmniMixin;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record SyncedValuePayload(
        int entityId,
        Identifier valueId,
        byte typeOrdinal,
        String encodedValue
) implements CustomPayload {

    public static final CustomPayload.Id<SyncedValuePayload> ID =
            new CustomPayload.Id<>(OmniMixin.id("synced_value"));

    public static final PacketCodec<RegistryByteBuf, SyncedValuePayload> CODEC = PacketCodec.tuple(
            PacketCodecs.VAR_INT, SyncedValuePayload::entityId,
            Identifier.PACKET_CODEC, SyncedValuePayload::valueId,
            PacketCodecs.BYTE, SyncedValuePayload::typeOrdinal,
            PacketCodecs.STRING, SyncedValuePayload::encodedValue,
            SyncedValuePayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
