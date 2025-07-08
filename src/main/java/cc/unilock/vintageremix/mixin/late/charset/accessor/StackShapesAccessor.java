package cc.unilock.vintageremix.mixin.late.charset.accessor;

import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import pl.asie.charset.module.immersion.stacks.StackShapes;

@Mixin(value = StackShapes.class, remap = false)
public interface StackShapesAccessor {
	@Accessor
	static Vec3d[][] getINGOT_POSITIONS() {
		throw new AssertionError();
	}
}
