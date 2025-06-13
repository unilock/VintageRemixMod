package cc.unilock.vintageremix.mixin.late.thermionics_world;

import com.elytradev.thermionics.world.ThermionicsWorld;
import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = ThermionicsWorld.class, remap = false)
public class ThermionicsWorldMixin {
	@Redirect(method = "preInit", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;setTranslationKey(Ljava/lang/String;)Lnet/minecraft/block/Block;", remap = true))
	private Block cancelSetTranslationKey(Block instance, String key) {
		return instance; // NO-OP
	}

	@Redirect(method = "preInit", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;setHardness(F)Lnet/minecraft/block/Block;", remap = true))
	private Block cancelSetHardness(Block instance, float hardness) {
		return instance; // NO-OP
	}

	@Redirect(method = "preInit", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;setHarvestLevel(Ljava/lang/String;I)V", remap = true))
	private void cancelSetHarvestLevel(Block instance, String toolClass, int level) {
		// NO-OP
	}
}
