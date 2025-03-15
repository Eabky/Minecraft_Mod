package net.eabky_dev.codexa.entity.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.eabky_dev.codexa.entity.client.model.OtherworldlyLanternModel;
import net.eabky_dev.codexa.entity.custom.OtherworldlyLanternBlockEntity;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class OtherworldlyLanternRenderer extends GeoBlockRenderer<OtherworldlyLanternBlockEntity>
{

    public OtherworldlyLanternRenderer(BlockEntityRendererProvider.Context context)
    {
        super(new OtherworldlyLanternModel());
    }

    private int getLightLevel(Level level, BlockPos pos)
    {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}