package cc.unilock.vintageremix.mixin.late.geckolib3;

import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import software.bernie.geckolib3.resource.GeckoLibCache;

@Mixin(value = GeckoLibCache.class, remap = false)
public abstract class GeckoLibCacheMixin {
	// Silence extraneous logging

	@Redirect(method = "onResourceManagerReload", at = @At(value = "INVOKE", target = "Ljava/lang/Exception;printStackTrace()V"), remap = true)
	private void onResourceManagerReload$printStackTrace(Exception instance) {
		// NO-OP
	}

	@Redirect(method = "onResourceManagerReload", at = @At(value = "INVOKE", target = "Lorg/apache/logging/log4j/Logger;error(Ljava/lang/String;Ljava/lang/Throwable;)V"), remap = true)
	private void onResourceManagerReload$error(Logger instance, String s, Throwable throwable) {
		instance.error(s);
	}
}
