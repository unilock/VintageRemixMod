package cc.unilock.vintageremix.mixin.late.thermionics_world;

import com.elytradev.thermionics.world.gen.ChunkProviderCompositor;
import org.dimdev.jeid.ducks.IModSupportsJEID;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = ChunkProviderCompositor.class, remap = false)
public class ChunkProviderCompositorMixin implements IModSupportsJEID {
}
