package net.eabky_dev.codexa.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.entity.custom.TomahawkProjectileEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class TomahawkProjectileModel<T extends Entity> extends HierarchicalModel<T>
{
    private final ModelPart tomahawk;
    private final ModelPart cube_r1;
    private final ModelPart cube_r2;
    private final ModelPart cube_r3;
    private final ModelPart cube_r4;
    private final ModelPart cube_r5;


    public TomahawkProjectileModel(ModelPart root)
    {
        this.tomahawk = root.getChild("tomahawk");
        this.cube_r1 = this.tomahawk.getChild("cube_r1");
        this.cube_r2 = this.tomahawk.getChild("cube_r2");
        this.cube_r3 = this.tomahawk.getChild("cube_r3");
        this.cube_r4 = this.tomahawk.getChild("cube_r4");
        this.cube_r5 = this.tomahawk.getChild("cube_r5");
    }

    public static LayerDefinition createBodyLayer()
    {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition tomahawk = partdefinition.addOrReplaceChild("tomahawk", CubeListBuilder.create(), PartPose.offset(0.0F, 16.5F, 0.0F));

        PartDefinition cube_r1 = tomahawk.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(8, 7).addBox(1.5F, 2.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, -4.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r2 = tomahawk.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(7, 9).addBox(0.5F, -1.5F, -0.5F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, -5.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r3 = tomahawk.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(3, 10).addBox(-2.5F, -1.5F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, 5.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r4 = tomahawk.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(1, 4).addBox(-2.5F, -1.5F, 0.0F, 5.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r5 = tomahawk.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(18, 1).addBox(-0.5F, -9.0F, -0.5F, 1.0F, 18.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, -0.7854F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha)
    {
        tomahawk.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
    }

    @Override
    public ModelPart root()
    {
        return tomahawk;
    }
}
