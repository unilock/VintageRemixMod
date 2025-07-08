package cc.unilock.vintageremix.mixin.late.charset.accessor;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import pl.asie.charset.lib.utils.UnlistedPropertyGeneric;
import pl.asie.charset.module.immersion.stacks.BlockStacks;
import pl.asie.charset.module.immersion.stacks.TileEntityStacks;

@Mixin(value = BlockStacks.class, remap = false)
public interface BlockStacksAccessor {
	@Accessor
	static UnlistedPropertyGeneric<TileEntityStacks> getPROPERTY_TILE() {
		throw new AssertionError();
	}
}
