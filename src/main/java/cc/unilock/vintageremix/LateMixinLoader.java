package cc.unilock.vintageremix;

import net.minecraftforge.fml.common.Loader;
import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.ArrayList;
import java.util.List;

public class LateMixinLoader implements ILateMixinLoader {
	private static final String PREFIX = "vintageremix.mixins.late.";
	private static final String SUFFIX = ".json";

	@Override
	public List<String> getMixinConfigs() {
		List<String> configs = new ArrayList<>();
		if (Loader.isModLoaded("astralsorcery")) {
			configs.add(PREFIX+"astralsorcery"+SUFFIX);
		}
		if (Loader.isModLoaded("computronics")) {
			configs.add(PREFIX+"computronics"+SUFFIX);
		}
		if (Loader.isModLoaded("correlated")) {
			configs.add(PREFIX+"correlated"+SUFFIX);
		}
		return configs;
	}
}
