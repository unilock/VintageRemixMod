package cc.unilock.vintageremix.mixin.late.jei;

import mezz.jei.api.recipe.transfer.IRecipeTransferHandler;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = IRecipeTransferRegistry.class, remap = false)
public interface IRecipeTransferRegistryMixin {
	@Shadow void addRecipeTransferHandlerWithOutput(IRecipeTransferHandler<?> recipeTransferHandler, String recipeCategoryUid);

	// Re-implement renamed method
	@Deprecated
	default void addRecipeTransferHandler(IRecipeTransferHandler<?> recipeTransferHandler, String recipeCategoryUid) {
		addRecipeTransferHandlerWithOutput(recipeTransferHandler, recipeCategoryUid);
	}
}
