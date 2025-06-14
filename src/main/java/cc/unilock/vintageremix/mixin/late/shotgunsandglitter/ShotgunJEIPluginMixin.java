package cc.unilock.vintageremix.mixin.late.shotgunsandglitter;

import com.teamwizardry.shotgunsandglitter.api.BulletType;
import com.teamwizardry.shotgunsandglitter.client.jei.ShotgunJEIPlugin;
import com.teamwizardry.shotgunsandglitter.common.items.ModItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.ISubtypeRegistry;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = ShotgunJEIPlugin.class, remap = false)
public class ShotgunJEIPluginMixin implements IModPlugin {
	@Override
	public void registerSubtypes(ISubtypeRegistry subtypeRegistry) {
		subtypeRegistry.registerSubtypeInterpreter(ModItems.BULLET, (stack) -> BulletType.byOrdinal(stack.getItemDamage()).serializeName + "_" + ModItems.BULLET.getEffectFromItem(stack).getID());
	}
}
