package cc.unilock.vintageremix.mixin.early.forge;

import cc.unilock.vintageremix.mixin.early.forge.accessor.CapabilityManagerAccessor;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.model.animation.CapabilityAnimation;
import net.minecraftforge.common.model.animation.IAnimationStateMachine;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ForgeModContainer.class, remap = false)
public class ForgeModContainerMixin {
	@Inject(method = "preInit", at = @At("TAIL"))
	private void preInit$tail(CallbackInfo ci) {
		if (CapabilityItemHandler.ITEM_HANDLER_CAPABILITY == null) {
			CapabilityItemHandler.ITEM_HANDLER_CAPABILITY = getCapability(IItemHandler.class);
		}
		if (CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY == null) {
			CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY = getCapability(IFluidHandler.class);
		}
		if (CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY == null) {
			CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY = getCapability(IFluidHandlerItem.class);
		}
		if (CapabilityAnimation.ANIMATION_CAPABILITY == null) {
			CapabilityAnimation.ANIMATION_CAPABILITY = getCapability(IAnimationStateMachine.class);
		}
		if (CapabilityEnergy.ENERGY == null) {
			CapabilityEnergy.ENERGY = getCapability(IEnergyStorage.class);
		}
	}

	@Unique
	@SuppressWarnings("unchecked")
	private <T> Capability<T> getCapability(Class<T> type) {
		return (Capability<T>) ((CapabilityManagerAccessor) (Object) CapabilityManager.INSTANCE).getProviders().get(type.getName().intern());
	}
}
