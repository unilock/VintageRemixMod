package cc.unilock.vintageremix.mixin.late.thermionics_world;

import com.elytradev.thermionics.world.gen.ChunkProviderCompositor;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.chunk.Chunk;
import org.dimdev.jeid.ducks.IModSupportsJEID;
import org.dimdev.jeid.ducks.INewChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ChunkProviderCompositor.class, remap = false)
public class ChunkProviderCompositorMixin implements IModSupportsJEID {
	// Explicit REID support(?)
	@Inject(method = "generateChunk", at = @At(value = "INVOKE", target = "Lcom/elytradev/thermionics/world/Benchmark;endFrame()V"))
	private void generateChunk$endFrame$pre(CallbackInfoReturnable<Chunk> cir, @Local Chunk chunk, @Local byte[] biomes) {
		int[] intBiomeArray = ((INewChunk) chunk).getIntBiomeArray();
		for (int i = 0; i < intBiomeArray.length; ++i) {
			intBiomeArray[i] = biomes[i];
		}
	}
}
