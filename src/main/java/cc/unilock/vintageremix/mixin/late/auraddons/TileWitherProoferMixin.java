package cc.unilock.vintageremix.mixin.late.auraddons;

import alexanders.mods.auraddons.block.tile.TileWitherProofer;
import com.llamalad7.mixinextras.sugar.Local;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = TileWitherProofer.class, remap = false)
public class TileWitherProoferMixin {
	// Prevent a NPE
	@Inject(method = "onMobGriefEvent", at = @At(value = "FIELD", target = "Lalexanders/mods/auraddons/block/tile/TileWitherProofer;world:Lnet/minecraft/world/World;", opcode = Opcodes.GETFIELD), cancellable = true)
	private static void onMobGriefEvent$getWorld$pre(CallbackInfo ci, @Local TileWitherProofer te) {
		if (!te.hasWorld()) {
			ci.cancel();
		}
	}
}
