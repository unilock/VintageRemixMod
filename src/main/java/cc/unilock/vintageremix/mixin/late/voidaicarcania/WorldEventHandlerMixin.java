package cc.unilock.vintageremix.mixin.late.voidaicarcania;

import com.camellias.voidaicarcania.util.handlers.WorldEventHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = WorldEventHandler.class, remap = false)
public class WorldEventHandlerMixin {
	// Silence beta warning
	@Inject(method = "onPlayerLogin", at = @At("HEAD"), cancellable = true)
	private static void onPlayerLogin$head(CallbackInfo ci) {
		ci.cancel();
	}
}
