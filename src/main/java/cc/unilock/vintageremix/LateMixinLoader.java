package cc.unilock.vintageremix;

import com.google.common.collect.Sets;
import net.minecraftforge.fml.common.Loader;
import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LateMixinLoader implements ILateMixinLoader {
	private static final String PREFIX = "vintageremix.mixins.late.";
	private static final String SUFFIX = ".json";

	private static final Set<String> MODS = Sets.newHashSet(
			"academy",
			"astralsorcery",
			"computronics",
			"correlated",
			"iridescent",
			"metallurgy",
			"shotgunsandglitter",
			"thermionics_world"
	);

	@Override
	public List<String> getMixinConfigs() {
		List<String> configs = new ArrayList<>();
		for (String mod : MODS) {
			if (Loader.isModLoaded(mod)) {
				configs.add(PREFIX+mod+SUFFIX);
			}
		}
		return configs;
	}
}
