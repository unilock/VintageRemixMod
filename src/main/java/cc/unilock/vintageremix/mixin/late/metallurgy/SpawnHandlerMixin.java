package cc.unilock.vintageremix.mixin.late.metallurgy;

import it.hurts.metallurgy_reforged.handler.SpawnHandler;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = SpawnHandler.class, remap = false)
public class SpawnHandlerMixin {
	// Hide warning message
	@Redirect(method = "onPlayerLogin", at = @At(value = "FIELD", target = "Lit/hurts/metallurgy_reforged/config/GeneralConfig;warning:Z", opcode = Opcodes.GETSTATIC))
	private static boolean onPlayerLogin$warning$redirect() {
		return false;
	}
}
