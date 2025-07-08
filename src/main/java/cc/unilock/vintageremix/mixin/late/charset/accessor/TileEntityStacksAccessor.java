package cc.unilock.vintageremix.mixin.late.charset.accessor;

import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import pl.asie.charset.module.immersion.stacks.TileEntityStacks;

@Mixin(value = TileEntityStacks.class, remap = false)
public interface TileEntityStacksAccessor {
	@Accessor
	ItemStack[] getStacks();
}
