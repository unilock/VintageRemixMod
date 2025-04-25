package cc.unilock.vintageremix;

import com.google.common.collect.Lists;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import zone.rong.mixinbooter.IEarlyMixinLoader;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

@IFMLLoadingPlugin.Name("VintageRemix")
@IFMLLoadingPlugin.MCVersion("1.12.2")
public class EarlyMixinLoader implements IFMLLoadingPlugin, IEarlyMixinLoader {
	@Override
	public String[] getASMTransformerClass() {
		return null; // NO-OP
	}

	@Override
	public String getModContainerClass() {
		return null; // NO-OP
	}

	@Nullable
	@Override
	public String getSetupClass() {
		return null; // NO-OP
	}

	@Override
	public void injectData(Map<String, Object> data) {
		// NO-OP
	}

	@Override
	public String getAccessTransformerClass() {
		return null; // NO-OP
	}

	@Override
	public List<String> getMixinConfigs() {
		return Lists.newArrayList("vintageremix.mixins.early.json");
	}
}
