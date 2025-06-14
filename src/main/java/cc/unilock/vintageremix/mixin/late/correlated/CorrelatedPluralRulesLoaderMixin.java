package cc.unilock.vintageremix.mixin.late.correlated;

import com.ibm.icu.impl.ICUResourceBundle;
import com.ibm.icu.util.UResourceBundle;
import com.unascribed.correlated.CorrelatedPluralRulesLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = CorrelatedPluralRulesLoader.class, remap = false)
public class CorrelatedPluralRulesLoaderMixin {
	// Update deprecated API usage
	@Redirect(method = "getPluralBundle", at = @At(value = "INVOKE", target = "Lcom/ibm/icu/impl/ICUResourceBundle;getBundleInstance(Ljava/lang/String;Ljava/lang/String;Ljava/lang/ClassLoader;Z)Lcom/ibm/icu/util/UResourceBundle;"))
	private UResourceBundle getPluralBundle$getBundleInstance$pre(String baseName, String localeID, ClassLoader root, boolean disableFallback) {
		return ICUResourceBundle.getBundleInstance(baseName, localeID, root, false);
	}
}
