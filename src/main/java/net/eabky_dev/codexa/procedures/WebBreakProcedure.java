package net.eabky_dev.codexa.procedures;

import net.eabky_dev.codexa.entity.custom.SpiderWebSpiderEntity;
import net.eabky_dev.codexa.CODEXA;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = CODEXA.MOD_ID)
public class WebBreakProcedure
{
    @SubscribeEvent
    public static void removeSpiderWebSpider(BlockEvent.BreakEvent event)
    {
        // Get the block being broken and its position
        BlockPos pos = event.getPos();
        Level level = event.getPlayer().level();

        // Check if the block is a cobweb
        BlockState state = level.getBlockState(pos);
        if (state.getBlock() == Blocks.COBWEB)
        {
            // Get the AABB 2 blocks below the cobweb position
            BlockPos belowPos = pos.below(2);
            AABB aabb = new AABB(belowPos.getX() - 0.5, belowPos.getY() - 0.5, belowPos.getZ() - 0.5,
                    belowPos.getX() + 0.5, belowPos.getY() + 0.5, belowPos.getZ() + 0.5);

            // Check for SpiderWebSpiderEntity within the AABB area
            List<Entity> entities = level.getEntitiesOfClass(Entity.class, aabb);
            for (Entity entity : entities)
            {
                if (entity instanceof SpiderWebSpiderEntity)
                {
                    // Remove the SpiderWebSpider entity
                    entity.remove(Entity.RemovalReason.DISCARDED);
                }
            }
        }
    }
}