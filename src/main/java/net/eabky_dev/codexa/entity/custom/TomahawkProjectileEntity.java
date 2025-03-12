package net.eabky_dev.codexa.entity.custom;


import net.eabky_dev.codexa.block.ModBlocks;
import net.eabky_dev.codexa.entity.CodexaEntities;
import net.eabky_dev.codexa.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec2;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class TomahawkProjectileEntity extends AbstractArrow implements ItemSupplier
{
    private float rotation;
    public Vec2 groundedOffset;

    public TomahawkProjectileEntity(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public TomahawkProjectileEntity(LivingEntity shooter, Level level)
    {
        super(CodexaEntities.TOMAHAWK.get(), shooter, level);
    }

    @Override
    protected ItemStack getPickupItem()
    {
        return new ItemStack(ModItems.TOMAHAWK.get());
    }

    public float getRenderingRotation() {
        rotation += 0.5f;
        if(rotation >= 360) {
            rotation = 0;
        }
        return rotation;
    }

    public boolean isGrounded() {
        return inGround;
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), 4);

        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result)
    {
        super.onHitBlock(result);

        if(result.getDirection() == Direction.SOUTH) {
            groundedOffset = new Vec2(215f,180f);
        }
        if(result.getDirection() == Direction.NORTH) {
            groundedOffset = new Vec2(215f, 0f);
        }
        if(result.getDirection() == Direction.EAST) {
            groundedOffset = new Vec2(215f,-90f);
        }
        if(result.getDirection() == Direction.WEST) {
            groundedOffset = new Vec2(215f,90f);
        }

        if(result.getDirection() == Direction.DOWN) {
            groundedOffset = new Vec2(115f,180f);
        }
        if(result.getDirection() == Direction.UP) {
            groundedOffset = new Vec2(285f,180f);
        }

        // Place Black Hole block
        BlockPos blockPos = result.getBlockPos().above();
        this.level().setBlockAndUpdate(blockPos, ModBlocks.BLACK_HOLE.get().defaultBlockState());

        // Schedule block removal after 5 seconds (100 ticks)
        this.level().scheduleTick(blockPos, ModBlocks.BLACK_HOLE.get(), 100);
    }

    public static void init() {
    }

    @Override
    public ItemStack getItem()
    {
        return null;
    }
}
