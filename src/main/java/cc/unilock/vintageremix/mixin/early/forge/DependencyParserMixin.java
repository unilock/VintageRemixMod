package cc.unilock.vintageremix.mixin.early.forge;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraftforge.fml.common.versioning.ArtifactVersion;
import net.minecraftforge.fml.common.versioning.DependencyParser;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Set;

@Mixin(value = DependencyParser.class, remap = false)
public class DependencyParserMixin {
	@WrapOperation(method = "parseDependency", at = @At(value = "INVOKE", target = "Ljava/util/Set;add(Ljava/lang/Object;)Z"))
	private boolean wrapAdd(Set<?> set, Object entry, Operation<Boolean> original) {
		if (entry instanceof ArtifactVersion && "randompatches".equals(((ArtifactVersion) entry).getLabel())) {
			return false;
		} else {
			return original.call(set, entry);
		}
	}
}
