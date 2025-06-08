package cc.unilock.vintageremix.mixin.late.computronics;

import mods.railcraft.common.core.IRailcraftObjectContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Supplier;

@Mixin(targets = "pl/asie/computronics/integration/railcraft/SignalTypes$1", remap = false)
public class SignalTypesInnerMixin {
	@Redirect(method = "<init>", at = @At(value = "NEW", target = "mods/railcraft/common/core/IRailcraftObjectContainer$Definition"))
	private IRailcraftObjectContainer.Definition createDefinition(IRailcraftObjectContainer obj, String tag, Supplier<?> altRecipeObject) {
		return new IRailcraftObjectContainer.Definition(tag, altRecipeObject);
	}
}
