package cc.unilock.vintageremix.mixin.early.minecraft;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.EntityLivingBase;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EntityLivingBase.class)
public class EntityLivingBaseMixin {
	@Shadow
	protected boolean isJumping;

	@ModifyExpressionValue(method = "travel", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/EntityLivingBase;collidedHorizontally:Z", opcode = Opcodes.GETFIELD, ordinal = 1))
	private boolean travel$collidedHorizontally(boolean original) {
		return original || this.isJumping;
	}
}
