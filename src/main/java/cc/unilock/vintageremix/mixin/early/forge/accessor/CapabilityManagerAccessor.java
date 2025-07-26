package cc.unilock.vintageremix.mixin.early.forge.accessor;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.IdentityHashMap;

@Mixin(CapabilityManager.class)
public interface CapabilityManagerAccessor {
	@Accessor
	IdentityHashMap<String, Capability<?>> getProviders();
}
