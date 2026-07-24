package dev.goshi;

import dev.goshi.omnimixin.api.event.render.PlayCamera;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;

public class TemplateModClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		// OmniMixin's CALCULATE_FOV event hands us the fully-computed vanilla FOV
		// (baseFov) as the starting value of `modifier`, right before it's applied
		// to the camera. Vanilla bakes the bow/crossbow zoom-in effect directly into
		// that value while the item is being drawn, so we just overwrite it back to
		// the player's normal configured FOV whenever a ranged weapon is in use.
		PlayCamera.CALCULATE_FOV.register(context -> {
			MinecraftClient client = MinecraftClient.getInstance();
			ClientPlayerEntity player = client.player;

			if (player == null || !player.isUsingItem()) {
				return;
			}

			ItemStack activeStack = player.getActiveItem();
			if (activeStack.getItem() instanceof RangedWeaponItem) {
				context.modifier().set(client.options.getFov().getValue());
			}
		});

		TemplateMod.LOGGER.info("[{}] Bow/crossbow zoom disabled.", TemplateMod.MOD_ID);
	}
}
