package cc.unilock.vintageremix;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = VintageRemix.MOD_ID, name = VintageRemix.MOD_NAME, version = VintageRemix.MOD_VERSION, dependencies = "required:mixinbooter;after:astralsorcery;after:computronics;after:correlated;after:thermionics_world")
public class VintageRemix {
	public static final String MOD_ID = "@MOD_ID@";
	public static final String MOD_NAME = "@MOD_NAME@";
	public static final String MOD_VERSION = "@MOD_VERSION@";
	public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		LOGGER.info("Heyo!");
	}
}
