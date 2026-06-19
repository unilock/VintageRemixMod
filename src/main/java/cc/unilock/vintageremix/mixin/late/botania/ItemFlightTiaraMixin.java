package cc.unilock.vintageremix.mixin.late.botania;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import vazkii.botania.common.item.ItemTemperanceStone;
import vazkii.botania.common.item.equipment.bauble.ItemFlightTiara;

@Mixin(value = ItemFlightTiara.class, remap = false)
public class ItemFlightTiaraMixin {
	@WrapOperation(method = "onWornTick", at = @At(value = "INVOKE", target = "Lvazkii/botania/common/core/helper/ItemNBTHelper;getInt(Lnet/minecraft/item/ItemStack;Ljava/lang/String;I)I", ordinal = 1))
	private int onWornTick$getInt(ItemStack stack, String tag, int defaultExpected, Operation<Integer> original, @Local(name = "p") EntityPlayer p) {
		return ItemTemperanceStone.hasTemperanceActive(p) ? -1 : original.call(stack, tag, defaultExpected);
	}
}
