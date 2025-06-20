package cc.unilock.vintageremix.mixin.late.academy;

import cn.lambdalib2.LambdaLib2;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = LambdaLib2.class, remap = false)
public class LambdaLib2Mixin {
	@WrapWithCondition(method = "initClient", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/fml/common/eventhandler/EventBus;register(Ljava/lang/Object;)V"))
	private boolean initClient$register$wrap(EventBus instance, Object eventType) {
		return false;
	}
}
