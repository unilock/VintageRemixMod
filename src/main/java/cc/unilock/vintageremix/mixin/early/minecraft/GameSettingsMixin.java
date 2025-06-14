package cc.unilock.vintageremix.mixin.early.minecraft;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.SoundCategory;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GameSettings.class)
public class GameSettingsMixin {
	// Catch exceptions
	@WrapMethod(method = "getSoundLevel")
	private float getSoundLevel$wrap(SoundCategory category, Operation<Float> original) {
		try {
			return original.call(category);
		} catch (Throwable e) {
//			VintageRemix.LOGGER.debug("GameSettings#getSoundLevel for SoundCategory "+category.getName()+" failed!");
			return 1.0F;
		}
	}
}
