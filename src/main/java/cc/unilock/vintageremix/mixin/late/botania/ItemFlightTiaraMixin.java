package cc.unilock.vintageremix.mixin.late.botania;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import vazkii.botania.common.item.ItemTemperanceStone;
import vazkii.botania.common.item.equipment.bauble.ItemFlightTiara;

@Mixin(value = ItemFlightTiara.class, remap = false)
public class ItemFlightTiaraMixin {
	@Definition(id = "cooldown", local = @Local(name = "cooldown"))
	@Expression("cooldown == 0")
	@WrapOperation(method = "onWornTick", at = @At("MIXINEXTRAS:EXPRESSION"))
	private boolean onWornTick$cooldown(int left, int right, Operation<Boolean> original, @Local(name = "p") EntityPlayer p) {
		return original.call(left, right) && !ItemTemperanceStone.hasTemperanceActive(p);
	}
}
