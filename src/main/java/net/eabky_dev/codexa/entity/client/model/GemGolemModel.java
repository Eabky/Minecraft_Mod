package net.eabky_dev.codexa.entity.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.eabky_dev.codexa.entity.animations.ModAnimationDefinitions;
import net.eabky_dev.codexa.entity.custom.GemGolemEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class GemGolemModel<T extends Entity> extends HierarchicalModel<T>
{

	private final ModelPart Rocky;
	private final ModelPart Body;
	private final ModelPart Crystal3;
	private final ModelPart Cryatla1;
	private final ModelPart LeftArm;
	private final ModelPart Cystal3;
	private final ModelPart Crystal2;
	private final ModelPart LowerLeftArm;
	private final ModelPart RightArm;
	private final ModelPart LowerRightArm;
	private final ModelPart h_head;
	private final ModelPart h_jaw;
	private final ModelPart LeftLeg;
	private final ModelPart RightLeg4;
	private final ModelPart RightLeg;
	private final ModelPart RightLeg2;

	public GemGolemModel(ModelPart root)
	{
		this.Rocky = root.getChild("Rocky");
		this.Body = this.Rocky.getChild("Body");
		this.Crystal3 = this.Body.getChild("Crystal3");
		this.Cryatla1 = this.Body.getChild("Cryatla1");
		this.LeftArm = this.Body.getChild("LeftArm");
		this.Cystal3 = this.LeftArm.getChild("Cystal3");
		this.Crystal2 = this.LeftArm.getChild("Crystal2");
		this.LowerLeftArm = this.LeftArm.getChild("LowerLeftArm");
		this.RightArm = this.Body.getChild("RightArm");
		this.LowerRightArm = this.RightArm.getChild("LowerRightArm");
		this.h_head = this.Body.getChild("h_head");
		this.h_jaw = this.h_head.getChild("h_jaw");
		this.LeftLeg = this.Rocky.getChild("LeftLeg");
		this.RightLeg4 = this.LeftLeg.getChild("RightLeg4");
		this.RightLeg = this.Rocky.getChild("RightLeg");
		this.RightLeg2 = this.RightLeg.getChild("RightLeg2");
	}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Rocky = partdefinition.addOrReplaceChild("Rocky", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition Body = Rocky.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-20.25F, -15.75F, -2.25F, 40.5F, 20.25F, 20.25F, new CubeDeformation(0.0F))
		.texOffs(0, 120).addBox(-18.0F, -13.5F, -4.5F, 36.0F, 15.75F, 2.25F, new CubeDeformation(0.0F))
		.texOffs(122, 27).addBox(-15.75F, -15.75F, -4.5F, 31.5F, 2.25F, 2.25F, new CubeDeformation(0.0F))
		.texOffs(122, 31).addBox(-15.75F, 2.25F, -4.5F, 31.5F, 2.25F, 2.25F, new CubeDeformation(0.0F))
		.texOffs(234, 251).addBox(18.0F, -11.25F, -4.5F, 2.25F, 11.25F, 2.25F, new CubeDeformation(0.0F))
		.texOffs(14, 253).addBox(-20.25F, -11.25F, -4.5F, 2.25F, 11.25F, 2.25F, new CubeDeformation(0.0F))
		.texOffs(68, 140).addBox(-15.75F, -13.5F, 18.0F, 31.5F, 15.75F, 2.25F, new CubeDeformation(0.0F))
		.texOffs(50, 182).addBox(-18.0F, -11.25F, 18.0F, 2.25F, 11.25F, 2.25F, new CubeDeformation(0.0F))
		.texOffs(40, 204).addBox(15.75F, -11.25F, 18.0F, 2.25F, 11.25F, 2.25F, new CubeDeformation(0.0F))
		.texOffs(122, 35).addBox(-13.5F, -15.75F, 18.0F, 27.0F, 2.25F, 2.25F, new CubeDeformation(0.0F))
		.texOffs(190, 23).addBox(-13.5F, 2.25F, 18.0F, 27.0F, 2.25F, 2.25F, new CubeDeformation(0.0F))
		.texOffs(184, 58).addBox(-13.5F, 2.25F, -9.0F, 27.0F, 2.25F, 4.5F, new CubeDeformation(0.0F))
		.texOffs(0, 40).addBox(-15.75F, 4.5F, -9.0F, 31.5F, 13.5F, 22.5F, new CubeDeformation(0.0F))
		.texOffs(50, 198).addBox(15.75F, 4.5F, -6.75F, 2.25F, 11.25F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(0, 204).addBox(-18.0F, 4.5F, -6.75F, 2.25F, 11.25F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(240, 13).addBox(-9.0F, 13.5F, 13.5F, 18.0F, 6.75F, 2.25F, new CubeDeformation(0.0F))
		.texOffs(192, 152).addBox(-11.25F, 4.5F, 13.5F, 22.5F, 9.0F, 4.5F, new CubeDeformation(0.0F))
		.texOffs(80, 115).addBox(-11.25F, 18.0F, -2.25F, 22.5F, 9.0F, 15.75F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -42.75F, 0.0F));

		PartDefinition cube_r1 = Body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(68, 158).addBox(-11.2475F, -23.9975F, 11.2525F, 22.495F, 11.245F, 6.745F, new CubeDeformation(-0.002F)), PartPose.offsetAndRotation(0.0F, 42.75F, 0.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r2 = Body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(196, 65).addBox(-9.0F, -52.5F, -23.25F, 18.0F, 13.5F, 6.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 42.75F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r3 = Body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(90, 198).addBox(-26.9975F, -47.2475F, -6.7475F, 4.495F, 4.495F, 4.495F, new CubeDeformation(-0.002F))
		.texOffs(120, 251).addBox(-29.25F, -49.5F, -6.75F, 4.5F, 4.5F, 4.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 42.75F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r4 = Body.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(120, 241).addBox(-33.75F, -40.5F, -6.75F, 4.5F, 4.5F, 4.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 42.75F, 0.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition cube_r5 = Body.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(58, 176).addBox(-6.1091F, 6.4544F, 4.5F, 13.5F, 11.25F, 11.25F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -20.25F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r6 = Body.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(36, 242).addBox(3.96F, -26.685F, -5.58F, 8.37F, 5.58F, 9.765F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.185F, 7.875F, 5.58F, 0.0F, -0.3927F, 0.0F));

		PartDefinition cube_r7 = Body.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(216, 107).addBox(-22.275F, -13.2605F, 2.2149F, 12.15F, 8.1F, 9.675F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.25F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition Crystal3 = Body.addOrReplaceChild("Crystal3", CubeListBuilder.create().texOffs(152, 77).addBox(6.75F, -24.75F, -27.0F, 11.25F, 20.25F, 11.25F, new CubeDeformation(0.0F))
		.texOffs(168, 227).addBox(8.0F, -34.75F, -25.75F, 8.75F, 13.25F, 8.75F, new CubeDeformation(-1.0F)), PartPose.offsetAndRotation(0.0F, -13.5F, 27.0F, 0.0F, 0.0F, 0.4363F));

		PartDefinition Cryatla1 = Body.addOrReplaceChild("Cryatla1", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -13.5F, 27.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition cube_r8 = Cryatla1.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(248, 145).addBox(-11.2475F, -8.9975F, -20.2475F, 6.745F, 13.495F, 6.745F, new CubeDeformation(-0.002F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition cube_r9 = Cryatla1.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(244, 234).addBox(-4.5F, -13.5F, -18.0F, 6.75F, 13.5F, 6.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition LeftArm = Body.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(0, 77).addBox(-4.5F, -6.75F, -11.25F, 20.25F, 22.5F, 20.25F, new CubeDeformation(0.0F))
		.texOffs(234, 27).addBox(-2.25F, -4.5F, -13.5F, 15.75F, 18.0F, 2.25F, new CubeDeformation(0.0F))
		.texOffs(208, 206).addBox(15.75F, -6.75F, -9.0F, 2.25F, 15.75F, 15.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(24.75F, -6.75F, 6.75F, -0.1731F, -0.0227F, -0.1289F));

		PartDefinition cube_r10 = LeftArm.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(40, 227).addBox(20.2525F, -60.7475F, -11.2475F, 11.245F, 4.495F, 11.245F, new CubeDeformation(-0.002F)), PartPose.offsetAndRotation(-24.75F, 49.5F, -6.75F, 0.0F, -0.3927F, 0.0F));

		PartDefinition Cystal3 = LeftArm.addOrReplaceChild("Cystal3", CubeListBuilder.create(), PartPose.offsetAndRotation(2.25F, 0.0F, 4.5F, -0.0845F, 0.0447F, 0.1231F));

		PartDefinition cube_r11 = Cystal3.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(212, 166).addBox(24.75F, -87.75F, -11.25F, 9.0F, 22.5F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(136, 140).addBox(22.5F, -65.25F, -13.5F, 13.5F, 15.75F, 13.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-27.0F, 49.5F, -11.25F, -0.3927F, 0.0F, 0.0F));

		PartDefinition Crystal2 = LeftArm.addOrReplaceChild("Crystal2", CubeListBuilder.create().texOffs(176, 170).addBox(-4.5F, -11.25F, -2.25F, 9.0F, 27.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(140, 227).addBox(-3.25F, -30.25F, -1.0F, 6.5F, 24.5F, 6.5F, new CubeDeformation(-1.0F)), PartPose.offsetAndRotation(13.5F, -11.25F, -2.25F, -0.3937F, -0.0094F, 0.4361F));

		PartDefinition LowerLeftArm = LeftArm.addOrReplaceChild("LowerLeftArm", CubeListBuilder.create(), PartPose.offsetAndRotation(4.5F, 13.5F, 0.0F, 0.0F, 0.0F, 0.0436F));

		PartDefinition cube_r12 = LowerLeftArm.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(140, 214).addBox(22.5F, -33.75F, 2.25F, 2.25F, 6.75F, 4.5F, new CubeDeformation(0.0F))
		.texOffs(72, 242).addBox(15.7525F, -35.9975F, -2.2475F, 4.495F, 8.995F, 2.245F, new CubeDeformation(-0.002F))
		.texOffs(196, 86).addBox(9.0F, -20.25F, 0.0F, 11.25F, 6.75F, 13.5F, new CubeDeformation(0.0F))
		.texOffs(0, 182).addBox(11.25F, -22.5F, -2.25F, 13.5F, 11.25F, 11.25F, new CubeDeformation(0.0F))
		.texOffs(0, 165).addBox(9.0038F, -33.7463F, -2.2463F, 15.7425F, 4.4925F, 13.4925F, new CubeDeformation(-0.003F))
		.texOffs(0, 233).addBox(13.5F, -33.75F, 0.0F, 9.0F, 11.25F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(246, 65).addBox(24.75F, -42.75F, 0.0F, 2.25F, 9.0F, 11.25F, new CubeDeformation(0.0F))
		.texOffs(122, 0).addBox(6.75F, -45.0F, -2.25F, 18.0F, 11.25F, 15.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-29.25F, 36.0F, -6.75F, 0.0F, 0.0F, 0.3927F));

		PartDefinition RightArm = Body.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(80, 77).addBox(-15.75F, -11.25F, -9.0F, 18.0F, 20.25F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(110, 40).addBox(-13.5F, -6.75F, -11.25F, 13.5F, 13.5F, 22.5F, new CubeDeformation(0.0F)), PartPose.offset(-22.5F, -6.75F, 6.75F));

		PartDefinition cube_r13 = RightArm.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(84, 241).addBox(-18.0F, -6.75F, -4.5F, 11.25F, 11.25F, 6.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r14 = RightArm.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(234, 47).addBox(-11.25F, -13.5F, 0.0F, 11.25F, 2.25F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition LowerRightArm = RightArm.addOrReplaceChild("LowerRightArm", CubeListBuilder.create().texOffs(192, 125).addBox(-6.75F, 0.0F, -4.5F, 13.5F, 18.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.75F, 9.0F, 0.0F, -2.9234F, 0.0F, -2.9234F));

		PartDefinition cube_r15 = LowerRightArm.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(0, 253).addBox(22.5F, -33.75F, 2.25F, 2.25F, 6.75F, 4.5F, new CubeDeformation(0.0F))
		.texOffs(184, 65).addBox(15.7525F, -35.9975F, -2.2475F, 4.495F, 8.995F, 2.245F, new CubeDeformation(-0.002F))
		.texOffs(158, 206).addBox(9.0F, -20.25F, 0.0F, 11.25F, 6.75F, 13.5F, new CubeDeformation(0.0F))
		.texOffs(108, 192).addBox(11.25F, -22.5F, -2.25F, 13.5F, 11.25F, 11.25F, new CubeDeformation(0.0F))
		.texOffs(158, 108).addBox(9.0037F, -33.7463F, -2.2463F, 15.7425F, 4.4925F, 13.4925F, new CubeDeformation(-0.003F))
		.texOffs(238, 125).addBox(13.5F, -33.75F, 0.0F, 9.0F, 11.25F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(246, 85).addBox(24.75F, -42.75F, 0.0F, 2.25F, 9.0F, 11.25F, new CubeDeformation(0.0F))
		.texOffs(0, 138).addBox(6.75F, -45.0F, -2.25F, 18.0F, 11.25F, 15.75F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-29.25F, 36.0F, -6.75F, 0.0F, 0.0F, 0.3927F));

		PartDefinition h_head = Body.addOrReplaceChild("h_head", CubeListBuilder.create().texOffs(126, 170).addBox(-6.75F, -9.0F, -11.25F, 13.5F, 11.25F, 11.25F, new CubeDeformation(0.0F))
		.texOffs(248, 165).addBox(6.7525F, -6.7475F, -8.9975F, 2.245F, 11.245F, 8.995F, new CubeDeformation(-0.002F))
		.texOffs(158, 192).addBox(-9.0F, -9.0F, -6.75F, 2.25F, 2.25F, 6.75F, new CubeDeformation(0.0F))
		.texOffs(58, 165).addBox(-9.0F, -4.5F, -11.25F, 2.25F, 6.75F, 2.25F, new CubeDeformation(0.0F))
		.texOffs(168, 249).addBox(-8.975F, -6.725F, -8.975F, 2.2F, 11.2F, 8.95F, new CubeDeformation(-0.02F))
		.texOffs(126, 158).addBox(6.75F, -4.5F, -11.25F, 2.25F, 6.75F, 2.25F, new CubeDeformation(0.0F))
		.texOffs(108, 176).addBox(6.75F, -9.0F, -6.75F, 2.25F, 2.25F, 6.75F, new CubeDeformation(0.0F))
		.texOffs(84, 230).addBox(-6.75F, -11.25F, -9.0F, 13.5F, 2.25F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(190, 27).addBox(-4.5F, -11.25F, -11.25F, 9.0F, 2.25F, 2.25F, new CubeDeformation(0.0F))
		.texOffs(204, 238).addBox(-11.25F, -6.75F, -13.5F, 9.0F, 2.25F, 11.25F, new CubeDeformation(0.0F))
		.texOffs(240, 0).addBox(2.25F, -6.75F, -13.5F, 9.0F, 2.25F, 11.25F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -4.5F));

		PartDefinition h_jaw = h_head.addOrReplaceChild("h_jaw", CubeListBuilder.create().texOffs(190, 31).addBox(-4.5F, 0.0F, -11.25F, 9.0F, 2.25F, 2.25F, new CubeDeformation(0.0F))
		.texOffs(158, 136).addBox(-6.75F, -2.25F, -11.25F, 13.5F, 2.25F, 2.25F, new CubeDeformation(0.0F))
		.texOffs(90, 214).addBox(-6.75F, -2.25F, -9.0F, 13.5F, 4.5F, 11.25F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.5F, -2.25F));

		PartDefinition LeftLeg = Rocky.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(244, 216).addBox(-4.5F, 0.0F, -6.75F, 6.75F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, -15.75F, 4.5F));

		PartDefinition cube_r16 = LeftLeg.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(190, 0).addBox(2.25F, -20.25F, -9.0F, 11.25F, 9.0F, 13.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F, 15.75F, -4.5F, -0.3927F, 0.0F, 0.0F));

		PartDefinition RightLeg4 = LeftLeg.addOrReplaceChild("RightLeg4", CubeListBuilder.create().texOffs(248, 185).addBox(-4.4975F, 6.7525F, -6.7475F, 6.745F, 2.245F, 8.995F, new CubeDeformation(-0.002F)), PartPose.offset(0.0F, 6.75F, 0.0F));

		PartDefinition cube_r17 = RightLeg4.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(212, 251).addBox(4.5038F, -6.7462F, 2.2537F, 6.7425F, 6.7425F, 4.4925F, new CubeDeformation(-0.003F)), PartPose.offsetAndRotation(-9.0F, 9.0F, -4.5F, 0.3927F, 0.0F, 0.0F));

		PartDefinition RightLeg = Rocky.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(244, 198).addBox(-4.5F, 0.0F, -6.75F, 6.75F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.75F, -15.75F, 4.5F));

		PartDefinition cube_r18 = RightLeg.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(184, 35).addBox(2.25F, -20.25F, -9.0F, 11.25F, 9.0F, 13.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F, 15.75F, -4.5F, -0.3927F, 0.0F, 0.0F));

		PartDefinition RightLeg2 = RightLeg.addOrReplaceChild("RightLeg2", CubeListBuilder.create().texOffs(158, 125).addBox(-4.4975F, 6.7525F, -6.7475F, 6.745F, 2.245F, 8.995F, new CubeDeformation(-0.002F)), PartPose.offset(0.0F, 6.75F, 0.0F));

		PartDefinition cube_r19 = RightLeg2.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(190, 251).addBox(4.5038F, -6.7462F, 2.2537F, 6.7425F, 6.7425F, 4.4925F, new CubeDeformation(-0.003F)), PartPose.offsetAndRotation(-9.0F, 9.0F, -4.5F, 0.3927F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 512, 512);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);

		this.animateWalk(ModAnimationDefinitions.GEM_GOLEM_WALK, limbSwing, limbSwingAmount, 4.72f, 2.5f);
		this.animate(((GemGolemEntity) entity).idleAnimationState, ModAnimationDefinitions.GEM_GOLEM_IDLE, ageInTicks, 1f);
		this.animate(((GemGolemEntity) entity).attackAnimationState, ModAnimationDefinitions.GEM_GOLEM_PUNCH, ageInTicks, 1f);
		this.animate(((GemGolemEntity) entity).rageAnimationState, ModAnimationDefinitions.GEM_GOLEM_RAGE, ageInTicks, 1f);
		this.animate(((GemGolemEntity) entity).deathAnimationState, ModAnimationDefinitions.GEM_GOLEM_DEATH, ageInTicks, 1f);

	}

	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks)
	{
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.h_head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.h_head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Rocky.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root()
	{
		return Rocky;
	}
}