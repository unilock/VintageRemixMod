package cc.unilock.vintageremix.mixin.early.minecraft;

import cc.unilock.vintageremix.VintageRemix;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(CraftingManager.class)
public class CraftingManagerMixin {
	@WrapMethod(method = "findMatchingResult")
	private static ItemStack findMatchingResult$wrap(InventoryCrafting craftMatrix, World worldIn, Operation<ItemStack> original) {
		try {
			return original.call(craftMatrix, worldIn);
		} catch (Exception e) {
			StringBuilder builder = new StringBuilder();

			builder.append("Failed to find matching result with following crafting matrix: [");
			for (int i = 0; i < craftMatrix.getSizeInventory(); i++) {
				int x = i % craftMatrix.getWidth();
				int y = i / craftMatrix.getHeight();
				if (x == 0) {
					builder.append('[');
				}
				ItemStack stack = craftMatrix.getStackInSlot(i);
				builder.append(stack.getItem().getRegistryName()).append(':').append(stack.getMetadata());
				if (x != craftMatrix.getWidth()-1) {
					builder.append(", ");
				} else {
					builder.append(']');
					if (y != craftMatrix.getHeight()-1) {
						builder.append(", ");
					}
				}
			}
			builder.append(']');

			VintageRemix.LOGGER.error(builder);
			return ItemStack.EMPTY;
		}
	}
}
