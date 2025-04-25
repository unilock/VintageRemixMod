package cc.unilock.vintageremix.mixin.late.astralsorcery;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(targets = "hellfirepvp/astralsorcery/common/constellation/perk/tree/nodes/key/KeyLightningArc$RepetitiveArcEffect", remap = false)
public class RepetitiveArcEffectMixin {
	@Shadow
	private EntityPlayer player;

	@WrapWithCondition(method = "fire", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z"))
	private boolean fire$modifyVisitedEntities(List<EntityLivingBase> instance, @Coerce Object entity) {
		return !((EntityLivingBase) entity).isOnSameTeam(this.player);
	}

	@Inject(method = "fire", at = @At(value = "INVOKE", target = "Ljava/util/List;removeAll(Ljava/util/Collection;)Z"))
	private void fire$modifyEntities(CallbackInfo ci, @Local(ordinal = 1) List<EntityLivingBase> entities) {
		entities.removeIf(e -> e.isOnSameTeam(this.player));
	}
}
