package dev.goshi.omnimixin.internal.mixin.item;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.goshi.omnimixin.api.common.Cancellable;
import dev.goshi.omnimixin.api.event.item.FinishUseContext;
import dev.goshi.omnimixin.api.event.item.PlayItem;
import dev.goshi.omnimixin.api.event.item.UseContext;
import dev.goshi.omnimixin.api.event.item.UseOnBlockContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ItemStack.class)
abstract class ItemStackUseMixin {

    @WrapMethod(method = "use(Lnet/minecraft/world/World;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/util/Hand;)Lnet/minecraft/util/TypedActionResult;")
    private TypedActionResult<ItemStack> omnimixin$wrapUse(
            World world, PlayerEntity user, Hand hand, Operation<TypedActionResult<ItemStack>> original
    ) {
        ItemStack self = (ItemStack) (Object) this;
        Cancellable cancel = new Cancellable();
        PlayItem.USE.invoker().use(new UseContext(self, world, user, hand, cancel));
        if (cancel.isCancelled()) {
            return TypedActionResult.pass(self);
        }
        return original.call(world, user, hand);
    }

    @WrapMethod(method = "useOnBlock(Lnet/minecraft/item/ItemUsageContext;)Lnet/minecraft/util/ActionResult;")
    private ActionResult omnimixin$wrapUseOnBlock(ItemUsageContext context, Operation<ActionResult> original) {
        ItemStack self = (ItemStack) (Object) this;
        Cancellable cancel = new Cancellable();
        PlayItem.USE_ON_BLOCK.invoker().useOnBlock(new UseOnBlockContext(self, context, cancel));
        if (cancel.isCancelled()) {
            return ActionResult.PASS;
        }
        return original.call(context);
    }

    @WrapMethod(method = "finishUsing(Lnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;)Lnet/minecraft/item/ItemStack;")
    private ItemStack omnimixin$wrapFinishUsing(World world, LivingEntity user, Operation<ItemStack> original) {
        ItemStack self = (ItemStack) (Object) this;
        Cancellable cancel = new Cancellable();
        PlayItem.FINISH_USE.invoker().finishUse(new FinishUseContext(self, world, user, cancel));
        if (cancel.isCancelled()) {
            return self;
        }
        return original.call(world, user);
    }
}
