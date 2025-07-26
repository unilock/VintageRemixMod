package cc.unilock.vintageremix.mixin.early.forge;

import net.minecraftforge.common.crafting.CraftingHelper;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CraftingHelper.class)
public class CraftingHelperMixin {
	// Quiet recipe loading errors

	@Redirect(method = "lambda$loadRecipes$22", at = @At(value = "INVOKE", target = "Lorg/apache/logging/log4j/Logger;error(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V"))
	private static void loadRecipes$error(Logger instance, String s, Object o1, Object o2) {
		instance.error(s, o1);
	}

	@Redirect(method = "lambda$loadRecipes$22", at = @At(value = "INVOKE", target = "Lorg/apache/logging/log4j/Logger;error(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V"))
	private static void loadRecipes$error(Logger instance, String s, Object o1, Object o2, Object o3) {
		instance.error(s, o1, o2);
	}
}
