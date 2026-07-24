package dev.goshi.omnimixin.api.event.world;

import dev.goshi.omnimixin.internal.OmniMixinLog;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public final class PlayBlock {

    private PlayBlock() {
    }

    @FunctionalInterface
    public interface Interact {
        void interact(BlockInteractContext context);
    }

    @FunctionalInterface
    public interface Place {
        void place(PlaceContext context);
    }

    @FunctionalInterface
    public interface NeighborUpdate {
        void neighborUpdate(NeighborUpdateContext context);
    }

    public static final Event<Interact> INTERACT = EventFactory.createArrayBacked(
            Interact.class,
            listeners -> context -> {
                for (Interact listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.interact(context), "PlayBlock.INTERACT");
                }
            }
    );

    public static final Event<Place> PLACE = EventFactory.createArrayBacked(
            Place.class,
            listeners -> context -> {
                for (Place listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.place(context), "PlayBlock.PLACE");
                }
            }
    );

    public static final Event<NeighborUpdate> NEIGHBOR_UPDATE = EventFactory.createArrayBacked(
            NeighborUpdate.class,
            listeners -> context -> {
                for (NeighborUpdate listener : listeners) {
                    OmniMixinLog.safeRun(() -> listener.neighborUpdate(context), "PlayBlock.NEIGHBOR_UPDATE");
                }
            }
    );
}
