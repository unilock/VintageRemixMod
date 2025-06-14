package cc.unilock.vintageremix.mixin.late.iridescent;

import com.elytradev.iridescent.module.spiritgraves.EntityGrave;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import zabi.minecraft.bmtr.ModConfig;

@Mixin(value = EntityGrave.class, remap = false)
public class EntityGraveMixin {
	@Unique
	private static final int ADDITIONAL = ModConfig.getExtraSlots(); // Bring Me The Rings!

	@ModifyExpressionValue(method = "<init>", at = @At(value = "CONSTANT", args = "intValue=49"))
	private int init$constant$modifyInventorySize(int original) {
		return original + ADDITIONAL;
	}

	@ModifyExpressionValue(method = "giveItems", at = @At(value = "CONSTANT", args = "intValue=7"))
	private int giveItems$constant$modifyBaubles(int original) {
		return original + ADDITIONAL;
	}

	@ModifyExpressionValue(method = "giveItems", at = @At(value = "CONSTANT", args = "intValue=48"))
	private int giveItems$constant$modifyGlasses(int original) {
		return original + ADDITIONAL;
	}
}
