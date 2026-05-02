package cc.unilock.vintageremix.mixin.late.iridescent;

import com.elytradev.iridescent.module.spiritgraves.ModuleSpiritGraves;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ModuleSpiritGraves.class)
public class ModuleSpiritGravesMixin {
	@ModifyArg(method = "onDeath", at = @At(value = "INVOKE", target = "Lcom/elytradev/iridescent/module/spiritgraves/EntityGrave;setPosition(DDD)V"), index = 1)
	private double onDeath$setPosition$modifyY(double y) {
		return Math.max(y, 4);
	}
}
