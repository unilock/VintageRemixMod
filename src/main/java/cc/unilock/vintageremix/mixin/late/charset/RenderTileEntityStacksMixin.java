package cc.unilock.vintageremix.mixin.late.charset;

import cc.unilock.vintageremix.mixin.late.charset.accessor.BlockStacksAccessor;
import cc.unilock.vintageremix.mixin.late.charset.accessor.StackShapesAccessor;
import cc.unilock.vintageremix.mixin.late.charset.accessor.TileEntityStacksAccessor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.model.pipeline.UnpackedBakedQuad;
import net.minecraftforge.common.property.IExtendedBlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import pl.asie.charset.lib.material.ColorLookupHandler;
import pl.asie.charset.lib.material.ItemMaterial;
import pl.asie.charset.lib.material.ItemMaterialRegistry;
import pl.asie.charset.lib.render.model.ModelTransformer;
import pl.asie.charset.lib.utils.RenderUtils;
import pl.asie.charset.module.immersion.stacks.RenderTileEntityStacks;
import pl.asie.charset.module.immersion.stacks.StackShapes;
import pl.asie.charset.module.immersion.stacks.TileEntityStacks;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

@Mixin(RenderTileEntityStacks.class)
public abstract class RenderTileEntityStacksMixin {
	@Shadow(remap = false) private static @Final int[][][] QUAD_ORDERS;
	@Shadow(remap = false) private static @Final EnumFacing[][] QUAD_FACES;
	@Shadow(remap = false) private static @Final int[][] QUAD_UVS;

	@Shadow(remap = false) public abstract ModelTransformer.IVertexTransformer createTransformer(int i, ItemStack stack, long rand);
	@Shadow public abstract boolean isAmbientOcclusion();

	/**
	 * @author unilock
	 * @reason akka.japi.Function -> java.util.function.Function
	 */
	@Overwrite
	public List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, long rand) {
		if (side != null || !(state instanceof IExtendedBlockState)) {
			return Collections.emptyList();
		}

		TileEntityStacks stacks = ((IExtendedBlockState) state).getValue(BlockStacksAccessor.getPROPERTY_TILE());
		if (stacks == null) {
			return Collections.emptyList();
		}

		List<BakedQuad> list = new ArrayList<>();

		for (int i = 0; i < 64; i++) {
			ItemStack stack = ((TileEntityStacksAccessor) stacks).getStacks()[i];
			if (stack == null) {
				continue;
			}

			if (StackShapes.isIngot(stack)) {
				Vec3d[] vecs = StackShapesAccessor.getINGOT_POSITIONS()[i];

				ItemMaterial material = ItemMaterialRegistry.INSTANCE.getMaterialIfPresent(stack);
				ItemMaterial blockMaterial = material != null ? material.getRelated("block") : null;
				Function<EnumFacing, TextureAtlasSprite> getter;
				int c;

				if (blockMaterial == null) {
					getter = (f) -> RenderUtils.getItemSprite(new ItemStack(Blocks.IRON_BLOCK), f);
					c = ColorLookupHandler.INSTANCE.getColor(stack, RenderUtils.AveragingMode.FULL) | 0xFF000000;
				} else {
					getter = (f) -> RenderUtils.getItemSprite(blockMaterial.getStack(), f);
					c = Minecraft.getMinecraft().getItemColors().colorMultiplier(blockMaterial.getStack(), 0);
				}

				float[] color = new float[]{
						MathHelper.clamp(((c >> 16) & 0xFF) / 255.0f, 0, 1),
						MathHelper.clamp(((c >> 8) & 0xFF) / 255.0f, 0, 1),
						MathHelper.clamp(((c) & 0xFF) / 255.0f, 0, 1),
						1.0f
				};

				int j = 0;
				int yOff = (i >> 3) & 1;

				for (int[] vecOrder : QUAD_ORDERS[yOff]) {
					UnpackedBakedQuad.Builder builder = new UnpackedBakedQuad.Builder(DefaultVertexFormats.ITEM);
					EnumFacing face = QUAD_FACES[yOff][j];
					TextureAtlasSprite sprite;

					try {
						sprite = getter.apply(face);
					} catch (Exception e) {
						try {
							sprite = getter.apply(null);
						} catch (Exception ee) {
							throw new RuntimeException(ee);
						}
					}

					builder.setTexture(sprite);
					builder.setApplyDiffuseLighting(isAmbientOcclusion());
					builder.setContractUVs(false);
					builder.setQuadOrientation(face);

					int uv_offset = j < 4 ? 1 : 0;
					if (yOff == 1) uv_offset = 1 - uv_offset;

					for (int k = 0; k < vecOrder.length; k++) {
						Vec3d vec = vecs[vecOrder[k]];
						int[] uv = QUAD_UVS[(k + uv_offset) & 3];
						for (int e = 0; e < builder.getVertexFormat().getElementCount(); e++) {
							VertexFormatElement el = builder.getVertexFormat().getElement(e);
							switch (el.getUsage()) {
								case POSITION:
									builder.put(e, (float) vec.x / 16f, (float) vec.y / 16f, (float) vec.z / 16f, 1);
									break;
								case COLOR:
									builder.put(e, color);
									break;
								case NORMAL:
									builder.put(e, face.getXOffset(), face.getYOffset(), face.getZOffset(), 0);
									break;
								case UV:
									float u = sprite.getInterpolatedU(uv[0]);
									float v = sprite.getInterpolatedV(uv[1]);
									builder.put(e, u, v, 0, 1);
									break;
								default:
									builder.put(e);
							}
						}
					}

					list.add(builder.build());
					j++;
				}
			} else if (StackShapes.isFlatPlaced(stack)) {
				// renderer the second
				IBakedModel model = RenderUtils.getItemModel(stack, stacks.getWorld(), Minecraft.getMinecraft().player);
				ModelTransformer.IVertexTransformer transformer = createTransformer(i, stack, rand);
				try {
					for (BakedQuad quad : model.getQuads(state, null, 0)) {
						list.add(ModelTransformer.transform(quad, transformer));
					}
					for (EnumFacing facing : EnumFacing.VALUES) {
						for (BakedQuad quad : model.getQuads(state, facing, 0)) {
							list.add(ModelTransformer.transform(quad, transformer));
						}
					}
				} catch (ModelTransformer.TransformationFailedException e) {
					throw new RuntimeException(e);
				}
			}
		}

		return list;
	}
}
