// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class custom_model extends EntityModel<Entity> {
	private final ModelRenderer kraken;
	private final ModelRenderer hair;
	private final ModelRenderer base;
	private final ModelRenderer s1;
	private final ModelRenderer s3;
	private final ModelRenderer s4;
	private final ModelRenderer s2;
	private final ModelRenderer body;
	private final ModelRenderer frill_west;
	private final ModelRenderer frill_east;
	private final ModelRenderer head;
	private final ModelRenderer eye;
	private final ModelRenderer beak;
	private final ModelRenderer limbs;
	private final ModelRenderer tentacle1;
	private final ModelRenderer t1_4;
	private final ModelRenderer t1_3;
	private final ModelRenderer t1_2;
	private final ModelRenderer t1_1;
	private final ModelRenderer tentacle2;
	private final ModelRenderer t2_4;
	private final ModelRenderer t2_3;
	private final ModelRenderer t2_2;
	private final ModelRenderer t2_1;
	private final ModelRenderer arm_west;
	private final ModelRenderer gripper_west;
	private final ModelRenderer aw_4;
	private final ModelRenderer aw_1;
	private final ModelRenderer aw_2;
	private final ModelRenderer aw_3;
	private final ModelRenderer tentacle5;
	private final ModelRenderer t5_4;
	private final ModelRenderer t5_3;
	private final ModelRenderer t5_2;
	private final ModelRenderer t5_1;
	private final ModelRenderer arm_east;
	private final ModelRenderer gripper_east;
	private final ModelRenderer ae4;
	private final ModelRenderer ae1;
	private final ModelRenderer ae2;
	private final ModelRenderer ae3;
	private final ModelRenderer tentacle3;
	private final ModelRenderer t3_1;
	private final ModelRenderer t3_4;
	private final ModelRenderer t3_3;
	private final ModelRenderer t3_2;
	private final ModelRenderer tentacle4;
	private final ModelRenderer t4_4;
	private final ModelRenderer t4_1;
	private final ModelRenderer t4_2;
	private final ModelRenderer t4_3;
	private final ModelRenderer tentacle6;
	private final ModelRenderer t6_4;
	private final ModelRenderer t6_1;
	private final ModelRenderer t6_2;
	private final ModelRenderer t6_3;

	public custom_model() {
		textureWidth = 512;
		textureHeight = 512;

		kraken = new ModelRenderer(this);
		kraken.setRotationPoint(0.0F, 18.0F, 0.0F);
		

		hair = new ModelRenderer(this);
		hair.setRotationPoint(0.0F, 0.0F, 35.0F);
		kraken.addChild(hair);
		setRotationAngle(hair, -0.1745F, -0.0349F, 0.1745F);
		

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 0.0F, 0.0F);
		hair.addChild(base);
		base.setTextureOffset(0, 0).addBox(-5.0F, -5.0F, 1.0F, 10.0F, 10.0F, 5.0F, 0.0F, false);

		s1 = new ModelRenderer(this);
		s1.setRotationPoint(0.0F, 0.0F, 0.0F);
		hair.addChild(s1);
		s1.setTextureOffset(128, 0).addBox(-4.0F, -4.0F, 6.0F, 8.0F, 8.0F, 6.0F, 0.0F, false);

		s3 = new ModelRenderer(this);
		s3.setRotationPoint(0.0F, 0.0F, 0.0F);
		hair.addChild(s3);
		s3.setTextureOffset(320, 0).addBox(-2.0F, -2.0F, 17.0F, 4.0F, 4.0F, 5.0F, 0.0F, false);

		s4 = new ModelRenderer(this);
		s4.setRotationPoint(0.0F, 0.0F, 0.0F);
		hair.addChild(s4);
		s4.setTextureOffset(384, 0).addBox(-1.0F, -1.0F, 22.0F, 2.0F, 2.0F, 7.0F, 0.0F, false);

		s2 = new ModelRenderer(this);
		s2.setRotationPoint(0.0F, 0.0F, 0.0F);
		hair.addChild(s2);
		s2.setTextureOffset(256, 0).addBox(-2.0F, -2.0F, 12.0F, 4.0F, 4.0F, 5.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 6.0F, 0.0F);
		kraken.addChild(body);
		body.setTextureOffset(92, 128).addBox(-5.0F, 0.0F, 1.0F, 10.0F, 1.0F, 34.0F, 0.0F, false);
		body.setTextureOffset(128, 128).addBox(-6.0F, -12.0F, 0.0F, 12.0F, 12.0F, 36.0F, 0.0F, false);
		body.setTextureOffset(0, 175).addBox(6.0F, -11.0F, 1.0F, 1.0F, 10.0F, 34.0F, 0.0F, false);
		body.setTextureOffset(0, 234).addBox(-7.0F, -11.0F, 1.0F, 1.0F, 10.0F, 34.0F, 0.0F, false);
		body.setTextureOffset(0, 128).addBox(-5.0F, -13.0F, 1.0F, 10.0F, 1.0F, 34.0F, 0.0F, false);

		frill_west = new ModelRenderer(this);
		frill_west.setRotationPoint(7.0F, -6.0F, 24.0F);
		body.addChild(frill_west);
		setRotationAngle(frill_west, 0.0F, 0.0F, 0.1745F);
		frill_west.setTextureOffset(256, 128).addBox(0.0F, 0.0F, 2.0F, 9.0F, 1.0F, 1.0F, 0.0F, false);
		frill_west.setTextureOffset(256, 128).addBox(0.0F, 0.0F, 3.0F, 9.0F, 1.0F, 1.0F, 0.0F, false);
		frill_west.setTextureOffset(256, 128).addBox(0.0F, 0.0F, 4.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);
		frill_west.setTextureOffset(256, 128).addBox(0.0F, 0.0F, 1.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);
		frill_west.setTextureOffset(256, 128).addBox(0.0F, 0.0F, 0.0F, 7.0F, 1.0F, 1.0F, 0.0F, false);
		frill_west.setTextureOffset(256, 128).addBox(0.0F, 0.0F, 5.0F, 7.0F, 1.0F, 1.0F, 0.0F, false);
		frill_west.setTextureOffset(256, 128).addBox(0.0F, 0.0F, 6.0F, 7.0F, 1.0F, 1.0F, 0.0F, false);
		frill_west.setTextureOffset(256, 128).addBox(0.0F, 0.0F, -1.0F, 7.0F, 1.0F, 1.0F, 0.0F, false);
		frill_west.setTextureOffset(256, 128).addBox(0.0F, 0.0F, -3.0F, 7.0F, 1.0F, 1.0F, 0.0F, false);
		frill_west.setTextureOffset(256, 128).addBox(0.0F, 0.0F, -4.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);
		frill_west.setTextureOffset(256, 128).addBox(0.0F, 0.0F, -5.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		frill_west.setTextureOffset(256, 128).addBox(0.0F, 0.0F, -6.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		frill_west.setTextureOffset(256, 128).addBox(0.0F, 0.0F, -7.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		frill_west.setTextureOffset(256, 128).addBox(0.0F, 0.0F, -8.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		frill_west.setTextureOffset(256, 128).addBox(0.0F, 0.0F, 7.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);
		frill_west.setTextureOffset(256, 128).addBox(0.0F, 0.0F, 8.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		frill_west.setTextureOffset(256, 128).addBox(0.0F, 0.0F, 9.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		frill_west.setTextureOffset(256, 128).addBox(0.0F, 0.0F, -2.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);

		frill_east = new ModelRenderer(this);
		frill_east.setRotationPoint(-7.0F, -6.0F, 24.0F);
		body.addChild(frill_east);
		setRotationAngle(frill_east, 0.0F, 0.0F, -0.1745F);
		frill_east.setTextureOffset(256, 128).addBox(-9.0F, 0.0F, 2.0F, 9.0F, 1.0F, 1.0F, 0.0F, false);
		frill_east.setTextureOffset(256, 128).addBox(-9.0F, 0.0F, 3.0F, 9.0F, 1.0F, 1.0F, 0.0F, false);
		frill_east.setTextureOffset(256, 128).addBox(-8.0F, 0.0F, 4.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);
		frill_east.setTextureOffset(256, 128).addBox(-8.0F, 0.0F, 1.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);
		frill_east.setTextureOffset(256, 128).addBox(-7.0F, 0.0F, 0.0F, 7.0F, 1.0F, 1.0F, 0.0F, false);
		frill_east.setTextureOffset(256, 128).addBox(-7.0F, 0.0F, 5.0F, 7.0F, 1.0F, 1.0F, 0.0F, false);
		frill_east.setTextureOffset(256, 128).addBox(-7.0F, 0.0F, 6.0F, 7.0F, 1.0F, 1.0F, 0.0F, false);
		frill_east.setTextureOffset(256, 128).addBox(-7.0F, 0.0F, -1.0F, 7.0F, 1.0F, 1.0F, 0.0F, false);
		frill_east.setTextureOffset(256, 128).addBox(-7.0F, 0.0F, -3.0F, 7.0F, 1.0F, 1.0F, 0.0F, false);
		frill_east.setTextureOffset(256, 128).addBox(-6.0F, 0.0F, -4.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);
		frill_east.setTextureOffset(256, 128).addBox(-5.0F, 0.0F, -5.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		frill_east.setTextureOffset(256, 128).addBox(-5.0F, 0.0F, -6.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		frill_east.setTextureOffset(256, 128).addBox(-4.0F, 0.0F, -7.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		frill_east.setTextureOffset(256, 128).addBox(-2.0F, 0.0F, -8.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		frill_east.setTextureOffset(256, 128).addBox(-6.0F, 0.0F, 7.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);
		frill_east.setTextureOffset(256, 128).addBox(-4.0F, 0.0F, 8.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		frill_east.setTextureOffset(256, 128).addBox(-1.0F, 0.0F, 9.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		frill_east.setTextureOffset(256, 128).addBox(-8.0F, 0.0F, -2.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 0.0F, 1.0F);
		kraken.addChild(head);
		setRotationAngle(head, -0.0873F, 0.0873F, 0.1745F);
		head.setTextureOffset(384, 128).addBox(-5.0F, -4.0F, -17.0F, 10.0F, 10.0F, 16.0F, 0.0F, false);

		eye = new ModelRenderer(this);
		eye.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(eye);
		eye.setTextureOffset(0, 256).addBox(2.0F, -5.0F, -10.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
		eye.setTextureOffset(0, 320).addBox(-2.0F, -5.0F, -12.0F, 4.0F, 1.0F, 8.0F, 0.0F, false);
		eye.setTextureOffset(0, 256).addBox(-3.0F, -5.0F, -10.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);

		beak = new ModelRenderer(this);
		beak.setRotationPoint(0.0F, 1.0F, -16.0F);
		head.addChild(beak);
		beak.setTextureOffset(128, 256).addBox(-4.0F, -4.0F, -2.0F, 8.0F, 8.0F, 1.0F, 0.0F, false);
		beak.setTextureOffset(128, 266).addBox(1.0F, -2.0F, -3.0F, 2.0F, 5.0F, 1.0F, 0.0F, false);
		beak.setTextureOffset(128, 266).addBox(-3.0F, -2.0F, -3.0F, 2.0F, 5.0F, 1.0F, 0.0F, false);

		limbs = new ModelRenderer(this);
		limbs.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(limbs);
		

		tentacle1 = new ModelRenderer(this);
		tentacle1.setRotationPoint(0.0F, -4.0F, -16.0F);
		limbs.addChild(tentacle1);
		setRotationAngle(tentacle1, -0.1745F, 0.0F, 0.0F);
		

		t1_4 = new ModelRenderer(this);
		t1_4.setRotationPoint(0.0F, 0.0F, 0.0F);
		tentacle1.addChild(t1_4);
		t1_4.setTextureOffset(256, 256).addBox(-1.0F, 0.0F, -84.0F, 2.0F, 1.0F, -10.0F, 0.0F, false);

		t1_3 = new ModelRenderer(this);
		t1_3.setRotationPoint(0.0F, 0.0F, 0.0F);
		tentacle1.addChild(t1_3);
		t1_3.setTextureOffset(256, 256).addBox(-1.0F, -1.0F, -56.0F, 2.0F, 2.0F, -28.0F, 0.0F, false);

		t1_2 = new ModelRenderer(this);
		t1_2.setRotationPoint(0.0F, 0.0F, 0.0F);
		tentacle1.addChild(t1_2);
		t1_2.setTextureOffset(256, 256).addBox(-2.0F, -1.0F, -28.0F, 4.0F, 2.0F, -28.0F, 0.0F, false);

		t1_1 = new ModelRenderer(this);
		t1_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		tentacle1.addChild(t1_1);
		t1_1.setTextureOffset(256, 256).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 3.0F, -28.0F, 0.0F, false);

		tentacle2 = new ModelRenderer(this);
		tentacle2.setRotationPoint(5.0F, -1.0F, -16.0F);
		limbs.addChild(tentacle2);
		setRotationAngle(tentacle2, -0.0873F, 0.0873F, 1.5708F);
		

		t2_4 = new ModelRenderer(this);
		t2_4.setRotationPoint(0.0F, 0.0F, 0.0F);
		tentacle2.addChild(t2_4);
		t2_4.setTextureOffset(256, 256).addBox(-1.0F, 0.0F, -84.0F, 2.0F, 1.0F, -10.0F, 0.0F, false);

		t2_3 = new ModelRenderer(this);
		t2_3.setRotationPoint(0.0F, 0.0F, 0.0F);
		tentacle2.addChild(t2_3);
		t2_3.setTextureOffset(256, 256).addBox(-1.0F, -1.0F, -56.0F, 2.0F, 2.0F, -28.0F, 0.0F, false);

		t2_2 = new ModelRenderer(this);
		t2_2.setRotationPoint(0.0F, 0.0F, 0.0F);
		tentacle2.addChild(t2_2);
		t2_2.setTextureOffset(256, 256).addBox(-2.0F, -1.0F, -28.0F, 4.0F, 2.0F, -28.0F, 0.0F, false);

		t2_1 = new ModelRenderer(this);
		t2_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		tentacle2.addChild(t2_1);
		t2_1.setTextureOffset(256, 256).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 3.0F, -28.0F, 0.0F, false);

		arm_west = new ModelRenderer(this);
		arm_west.setRotationPoint(5.0F, 3.0F, -16.0F);
		limbs.addChild(arm_west);
		setRotationAngle(arm_west, -0.0873F, 0.0F, 1.5708F);
		

		gripper_west = new ModelRenderer(this);
		gripper_west.setRotationPoint(0.0F, 0.0F, 0.0F);
		arm_west.addChild(gripper_west);
		gripper_west.setTextureOffset(8, 512).addBox(-3.0F, -1.0F, -94.0F, 6.0F, 2.0F, -10.0F, 0.0F, false);
		gripper_west.setTextureOffset(0, 496).addBox(-2.0F, 0.0F, -107.0F, 4.0F, 2.0F, 14.0F, 0.0F, false);

		aw_4 = new ModelRenderer(this);
		aw_4.setRotationPoint(0.0F, 0.0F, 0.0F);
		arm_west.addChild(aw_4);
		aw_4.setTextureOffset(256, 256).addBox(-1.0F, 0.0F, -84.0F, 2.0F, 1.0F, -10.0F, 0.0F, false);

		aw_1 = new ModelRenderer(this);
		aw_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		arm_west.addChild(aw_1);
		aw_1.setTextureOffset(256, 256).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 3.0F, -28.0F, 0.0F, false);

		aw_2 = new ModelRenderer(this);
		aw_2.setRotationPoint(0.0F, 0.0F, 0.0F);
		arm_west.addChild(aw_2);
		aw_2.setTextureOffset(256, 256).addBox(-2.0F, -1.0F, -28.0F, 4.0F, 2.0F, -28.0F, 0.0F, false);

		aw_3 = new ModelRenderer(this);
		aw_3.setRotationPoint(0.0F, 0.0F, 0.0F);
		arm_west.addChild(aw_3);
		aw_3.setTextureOffset(256, 256).addBox(-1.0F, -1.0F, -56.0F, 2.0F, 2.0F, -28.0F, 0.0F, false);

		tentacle5 = new ModelRenderer(this);
		tentacle5.setRotationPoint(-5.0F, -1.0F, -16.0F);
		limbs.addChild(tentacle5);
		setRotationAngle(tentacle5, -0.0873F, -0.0873F, -1.5708F);
		

		t5_4 = new ModelRenderer(this);
		t5_4.setRotationPoint(0.0F, 0.0F, 0.0F);
		tentacle5.addChild(t5_4);
		t5_4.setTextureOffset(256, 256).addBox(-1.0F, 0.0F, -84.0F, 2.0F, 1.0F, -10.0F, 0.0F, false);

		t5_3 = new ModelRenderer(this);
		t5_3.setRotationPoint(0.0F, 0.0F, 0.0F);
		tentacle5.addChild(t5_3);
		t5_3.setTextureOffset(256, 256).addBox(-1.0F, -1.0F, -56.0F, 2.0F, 2.0F, -28.0F, 0.0F, false);

		t5_2 = new ModelRenderer(this);
		t5_2.setRotationPoint(0.0F, 0.0F, 0.0F);
		tentacle5.addChild(t5_2);
		t5_2.setTextureOffset(256, 256).addBox(-2.0F, -1.0F, -28.0F, 4.0F, 2.0F, -28.0F, 0.0F, false);

		t5_1 = new ModelRenderer(this);
		t5_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		tentacle5.addChild(t5_1);
		t5_1.setTextureOffset(256, 256).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 3.0F, -28.0F, 0.0F, false);

		arm_east = new ModelRenderer(this);
		arm_east.setRotationPoint(-5.0F, 3.0F, -16.0F);
		limbs.addChild(arm_east);
		setRotationAngle(arm_east, -0.0873F, 0.0F, -1.5708F);
		

		gripper_east = new ModelRenderer(this);
		gripper_east.setRotationPoint(10.0F, 0.0F, 0.0F);
		arm_east.addChild(gripper_east);
		gripper_east.setTextureOffset(384, 256).addBox(-13.0F, -1.0F, -94.0F, 6.0F, 2.0F, -10.0F, 0.0F, false);
		gripper_east.setTextureOffset(384, 256).addBox(-12.0F, 0.0F, -107.0F, 4.0F, 2.0F, 14.0F, 0.0F, false);

		ae4 = new ModelRenderer(this);
		ae4.setRotationPoint(0.0F, 0.0F, 0.0F);
		arm_east.addChild(ae4);
		ae4.setTextureOffset(256, 256).addBox(-1.0F, 0.0F, -84.0F, 2.0F, 1.0F, -10.0F, 0.0F, false);

		ae1 = new ModelRenderer(this);
		ae1.setRotationPoint(0.0F, 0.0F, 0.0F);
		arm_east.addChild(ae1);
		ae1.setTextureOffset(256, 256).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 3.0F, -28.0F, 0.0F, false);

		ae2 = new ModelRenderer(this);
		ae2.setRotationPoint(0.0F, 0.0F, 0.0F);
		arm_east.addChild(ae2);
		ae2.setTextureOffset(256, 256).addBox(-2.0F, -1.0F, -28.0F, 4.0F, 2.0F, -28.0F, 0.0F, false);

		ae3 = new ModelRenderer(this);
		ae3.setRotationPoint(0.0F, 0.0F, 0.0F);
		arm_east.addChild(ae3);
		ae3.setTextureOffset(256, 256).addBox(-1.0F, -1.0F, -56.0F, 2.0F, 2.0F, -28.0F, 0.0F, false);

		tentacle3 = new ModelRenderer(this);
		tentacle3.setRotationPoint(3.0F, 6.0F, -16.0F);
		limbs.addChild(tentacle3);
		setRotationAngle(tentacle3, -0.0873F, 0.0873F, -3.1416F);
		

		t3_1 = new ModelRenderer(this);
		t3_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		tentacle3.addChild(t3_1);
		t3_1.setTextureOffset(256, 256).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 3.0F, -28.0F, 0.0F, false);

		t3_4 = new ModelRenderer(this);
		t3_4.setRotationPoint(0.0F, 0.0F, 0.0F);
		tentacle3.addChild(t3_4);
		t3_4.setTextureOffset(256, 256).addBox(-1.0F, 0.0F, -84.0F, 2.0F, 1.0F, -10.0F, 0.0F, false);

		t3_3 = new ModelRenderer(this);
		t3_3.setRotationPoint(0.0F, 0.0F, 0.0F);
		tentacle3.addChild(t3_3);
		t3_3.setTextureOffset(256, 256).addBox(-1.0F, -1.0F, -56.0F, 2.0F, 2.0F, -28.0F, 0.0F, false);

		t3_2 = new ModelRenderer(this);
		t3_2.setRotationPoint(0.0F, 0.0F, 0.0F);
		tentacle3.addChild(t3_2);
		t3_2.setTextureOffset(256, 256).addBox(-1.0F, -1.0F, -28.0F, 2.0F, 2.0F, -28.0F, 0.0F, false);

		tentacle4 = new ModelRenderer(this);
		tentacle4.setRotationPoint(-3.0F, 6.0F, -16.0F);
		limbs.addChild(tentacle4);
		setRotationAngle(tentacle4, -0.0873F, -0.0873F, -3.1416F);
		

		t4_4 = new ModelRenderer(this);
		t4_4.setRotationPoint(0.0F, 0.0F, 0.0F);
		tentacle4.addChild(t4_4);
		t4_4.setTextureOffset(256, 256).addBox(-1.0F, 0.0F, -84.0F, 2.0F, 1.0F, -10.0F, 0.0F, false);

		t4_1 = new ModelRenderer(this);
		t4_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		tentacle4.addChild(t4_1);
		t4_1.setTextureOffset(256, 256).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 3.0F, -28.0F, 0.0F, false);

		t4_2 = new ModelRenderer(this);
		t4_2.setRotationPoint(0.0F, 0.0F, 0.0F);
		tentacle4.addChild(t4_2);
		t4_2.setTextureOffset(256, 256).addBox(-1.0F, -1.0F, -28.0F, 2.0F, 2.0F, -28.0F, 0.0F, false);

		t4_3 = new ModelRenderer(this);
		t4_3.setRotationPoint(0.0F, 0.0F, 0.0F);
		tentacle4.addChild(t4_3);
		t4_3.setTextureOffset(256, 256).addBox(-1.0F, -1.0F, -56.0F, 2.0F, 2.0F, -28.0F, 0.0F, false);

		tentacle6 = new ModelRenderer(this);
		tentacle6.setRotationPoint(0.0F, 6.0F, -16.0F);
		limbs.addChild(tentacle6);
		setRotationAngle(tentacle6, -0.1745F, 0.0F, -3.1416F);
		

		t6_4 = new ModelRenderer(this);
		t6_4.setRotationPoint(0.0F, 0.0F, 0.0F);
		tentacle6.addChild(t6_4);
		t6_4.setTextureOffset(256, 256).addBox(-1.0F, 0.0F, -84.0F, 2.0F, 1.0F, -10.0F, 0.0F, false);

		t6_1 = new ModelRenderer(this);
		t6_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		tentacle6.addChild(t6_1);
		t6_1.setTextureOffset(256, 256).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 3.0F, -28.0F, 0.0F, false);

		t6_2 = new ModelRenderer(this);
		t6_2.setRotationPoint(0.0F, 0.0F, 0.0F);
		tentacle6.addChild(t6_2);
		t6_2.setTextureOffset(256, 256).addBox(-1.0F, -1.0F, -28.0F, 2.0F, 2.0F, -28.0F, 0.0F, false);

		t6_3 = new ModelRenderer(this);
		t6_3.setRotationPoint(0.0F, 0.0F, 0.0F);
		tentacle6.addChild(t6_3);
		t6_3.setTextureOffset(256, 256).addBox(-1.0F, -1.0F, -56.0F, 2.0F, 2.0F, -28.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		kraken.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}