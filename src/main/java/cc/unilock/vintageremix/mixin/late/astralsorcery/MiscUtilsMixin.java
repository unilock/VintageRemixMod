package cc.unilock.vintageremix.mixin.late.astralsorcery;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import hellfirepvp.astralsorcery.common.util.MiscUtils;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Mixin(value = MiscUtils.class, remap = false)
public class MiscUtilsMixin {
	// Don't allow attacking tamed entities
	@ModifyReturnValue(method = "canPlayerAttackServer", at = @At("TAIL"))
	private static boolean canPlayerAttackServer$tail(boolean original, @Nullable EntityLivingBase source, @Nonnull EntityLivingBase target) {
		if (source != null && (source.isOnSameTeam(target) || target.isOnSameTeam(source))) {
			return false;
		}

		return original;
	}
}
