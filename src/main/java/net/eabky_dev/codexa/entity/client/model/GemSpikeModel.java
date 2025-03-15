package net.eabky_dev.codexa.entity.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class GemSpikeModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart Spike;
	private final ModelPart Cystal3;
	private final ModelPart Crystal2;
	private final ModelPart Crystal3;
	private final ModelPart Crystal4;
	private final ModelPart LowerLeftArm;

	public GemSpikeModel(ModelPart root) {
		this.Spike = root.getChild("Spike");
		this.Cystal3 = this.Spike.getChild("Cystal3");
		this.Crystal2 = this.Spike.getChild("Crystal2");
		this.Crystal3 = this.Spike.getChild("Crystal3");
		this.Crystal4 = this.Spike.getChild("Crystal4");
		this.LowerLeftArm = this.Spike.getChild("LowerLeftArm");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Spike = partdefinition.addOrReplaceChild("Spike", CubeListBuilder.create().texOffs(36, 134).addBox(-11.75F, -13.25F, -10.5F, 20.25F, 22.5F, 20.25F, new CubeDeformation(0.0F))
		.texOffs(142, 27).addBox(-11.75F, -35.75F, -10.5F, 20.25F, 22.5F, 20.25F, new CubeDeformation(2.0F))
		.texOffs(142, 27).addBox(-11.75F, -62.75F, -10.5F, 20.25F, 22.5F, 20.25F, new CubeDeformation(5.0F))
		.texOffs(174, 138).addBox(-9.5F, -11.0F, -12.75F, 15.75F, 18.0F, 2.25F, new CubeDeformation(0.0F))
		.texOffs(152, 160).addBox(8.5F, -13.25F, -8.25F, 2.25F, 15.75F, 15.75F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r1 = Spike.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(149, 123).addBox(20.2525F, -60.7475F, -11.2475F, 11.245F, 4.495F, 11.245F, new CubeDeformation(-0.002F)), PartPose.offsetAndRotation(-32.0F, 43.0F, -6.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition Cystal3 = Spike.addOrReplaceChild("Cystal3", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.6129F, -10.2512F, 9.5008F, -2.2662F, 0.0447F, 0.1231F));

		PartDefinition cube_r2 = Cystal3.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(116, 160).addBox(-4.5F, -11.25F, -4.5F, 9.0F, 22.5F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.0F, 9.1654F, -13.3406F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r3 = Cystal3.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(116, 160).addBox(-4.5F, -11.25F, -4.5F, 9.0F, 22.5F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.8346F, 3.6594F, -0.3927F, 0.0F, 0.0F));

		PartDefinition Crystal2 = Spike.addOrReplaceChild("Crystal2", CubeListBuilder.create().texOffs(0, 142).addBox(-4.5F, -3.375F, -4.5F, 9.0F, 27.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(36, 177).addBox(-3.25F, -22.375F, -3.25F, 6.5F, 24.5F, 6.5F, new CubeDeformation(-1.0F)), PartPose.offsetAndRotation(14.9139F, 2.4204F, -8.4012F, 0.226F, 0.3254F, 2.6532F));

		PartDefinition Crystal3 = Spike.addOrReplaceChild("Crystal3", CubeListBuilder.create().texOffs(0, 142).addBox(-4.5F, -3.375F, -4.5F, 9.0F, 27.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(36, 177).addBox(-3.25F, -22.375F, -3.25F, 6.5F, 24.5F, 6.5F, new CubeDeformation(-1.0F)), PartPose.offsetAndRotation(14.9139F, 2.4204F, -8.4012F, 0.226F, 0.3254F, 2.6532F));

		PartDefinition Crystal4 = Spike.addOrReplaceChild("Crystal4", CubeListBuilder.create().texOffs(0, 142).addBox(-4.5F, -3.375F, -4.5F, 9.0F, 27.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(36, 177).addBox(-3.25F, -22.375F, -3.25F, 6.5F, 24.5F, 6.5F, new CubeDeformation(-1.0F)), PartPose.offsetAndRotation(14.9139F, 2.4204F, -8.4012F, 0.226F, 0.3254F, 2.6532F));

		PartDefinition cube_r4 = Crystal4.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(36, 177).addBox(-3.25F, -22.375F, -3.25F, 6.5F, 24.5F, 6.5F, new CubeDeformation(-1.0F))
		.texOffs(0, 142).addBox(-4.5F, -3.375F, -4.5F, 9.0F, 27.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.0F, 58.3506F, -7.553F, -2.9873F, -0.8625F, -1.7728F));

		PartDefinition LowerLeftArm = Spike.addOrReplaceChild("LowerLeftArm", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.75F, 7.0F, 0.75F, 0.0F, 0.0F, 0.0436F));

		PartDefinition cube_r5 = LowerLeftArm.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 0).addBox(22.5F, -33.75F, 2.25F, 2.25F, 6.75F, 4.5F, new CubeDeformation(0.0F))
		.texOffs(0, 43).addBox(15.7525F, -35.9975F, -2.2475F, 4.495F, 8.995F, 2.245F, new CubeDeformation(-0.002F))
		.texOffs(158, 102).addBox(9.0F, -20.25F, 0.0F, 11.25F, 6.75F, 13.5F, new CubeDeformation(0.0F))
		.texOffs(148, 0).addBox(11.25F, -22.5F, -2.25F, 13.5F, 11.25F, 11.25F, new CubeDeformation(0.0F))
		.texOffs(116, 143).addBox(9.0038F, -33.7463F, -2.2462F, 15.7425F, 4.4925F, 13.4925F, new CubeDeformation(-0.003F))
		.texOffs(64, 177).addBox(13.5F, -33.75F, 0.0F, 9.0F, 11.25F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(0, 178).addBox(24.75F, -42.75F, 0.0F, 2.25F, 9.0F, 11.25F, new CubeDeformation(0.0F))
		.texOffs(130, 75).addBox(6.75F, -45.0F, -2.25F, 18.0F, 11.25F, 15.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-29.25F, 36.0F, -6.75F, 0.0F, 0.0F, 0.3927F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Spike.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}