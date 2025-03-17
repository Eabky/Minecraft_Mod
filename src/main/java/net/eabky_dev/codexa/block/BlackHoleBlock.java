package net.eabky_dev.codexa.block;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlackHoleBlock extends Block
{

    public BlackHoleBlock(BlockBehaviour.Properties properties)
    {
        super(properties);
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pMovedByPiston) //will it work when generating it in custom structures?
    {
        pLevel.scheduleTick(pPos,this,1);
        super.onPlace(pState, pLevel, pPos, pOldState, pMovedByPiston);
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom)
    {
        eventHorizon(pLevel,pPos);

        pLevel.scheduleTick(pPos,this,1);
        super.tick(pState, pLevel, pPos, pRandom);
    }

    protected void eventHorizon(ServerLevel plevel, BlockPos pos)
    {
        // Range of the black hole's pull effect
        double range = 20.0;
        List<Entity> entities = plevel.getEntitiesOfClass(Entity.class, new AABB(pos).inflate(range));

        for (Entity entity : entities) {
            if (entity instanceof LivingEntity) {
                // Calculate direction and strength of the pull
                double pullStrength = 2; // Pull strength of the black hole
                double xDiff = pos.getX() - entity.getX();
                double yDiff = pos.getY() - entity.getY();
                double zDiff = pos.getZ() - entity.getZ();
                double distance = Math.sqrt(xDiff * xDiff + yDiff * yDiff + zDiff * zDiff);

                if (distance > 0.1) {
                    // Normalize the vector and apply the force to pull the entity
                    double pullX = xDiff / distance * pullStrength;
                    double pullY = yDiff / distance * pullStrength;
                    double pullZ = zDiff / distance * pullStrength;

                    entity.setDeltaMovement(entity.getDeltaMovement().add(pullX, pullY, pullZ));
                }

                // Apply damage if the entity is very close to the black hole
                if (distance < 10.0) {
                    entity.hurt(plevel.damageSources().magic(), 5.0F); // Adjust damage here
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag)
    {
        pTooltip.add(Component.translatable("tooltip.codexa.black_hole.tooltip"));
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
    }
}

