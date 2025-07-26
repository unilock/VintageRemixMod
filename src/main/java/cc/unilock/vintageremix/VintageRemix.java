package cc.unilock.vintageremix;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
		modid = VintageRemix.MOD_ID,
		name = VintageRemix.MOD_NAME,
		version = VintageRemix.MOD_VERSION,
		dependencies = "required:mixinbooter;" + 
				"after:academy;" +
				"after:astralsorcery;" +
				"after:charset;" +
				"after:computronics;" +
				"after:correlated;" +
				"after:iridescent;" +
				"after:metallurgy;" +
				"after:shotgunsandglitter;" +
				"after:thermionics_world;" +
				"after:voidaicarcania;"
)
public class VintageRemix {
	public static final String MOD_ID = "@MOD_ID@";
	public static final String MOD_NAME = "@MOD_NAME@";
	public static final String MOD_VERSION = "@MOD_VERSION@";
	public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

//	@Mod.EventHandler
//	public void preInit(FMLPreInitializationEvent event) {
//		CapabilityItemHandler.ITEM_HANDLER_CAPABILITY = getCapability(IItemHandler.class);
//		CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY = getCapability(IFluidHandler.class);
//		CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY = getCapability(IFluidHandlerItem.class);
//		CapabilityAnimation.ANIMATION_CAPABILITY = getCapability(IAnimationStateMachine.class);
//		CapabilityEnergy.ENERGY = getCapability(IEnergyStorage.class);
//	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		LOGGER.info("Heyo!");
	}

//	@SuppressWarnings("unchecked")
//	private <T> Capability<T> getCapability(Class<T> type) {
//		return (Capability<T>) ((CapabilityManagerAccessor) (Object) CapabilityManager.INSTANCE).getProviders().get(type.getName().intern());
//	}
}
