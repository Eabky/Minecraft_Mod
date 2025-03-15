package net.eabky_dev.codexa.procedures;

import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.entity.custom.SpiderWebSpiderEntity;
import net.eabky_dev.codexa.init.CodexaModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;

import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber(modid = CODEXA.MOD_ID)
public class SpiderWebSpiderSpawnProcedure {
    private static final Random random = new Random();
    private static final float SPAWN_CHANCE = 0.2f; // 20% chance to spawn

    @SubscribeEvent
    public static void checkForSpiderWebs(TickEvent.PlayerTickEvent event)
    {
        if (event.phase == TickEvent.Phase.END)
        { // Runs once per tick
            Player player = event.player;
            Level world = player.level();

            if (world instanceof ServerLevel serverLevel)
            {
                BlockPos playerPos = player.blockPosition();

                for (int x = -20; x <= 20; x++)
                {
                    for (int y = -5; y <= 5; y++)
                    {
                        for (int z = -20; z <= 20; z++)
                        {
                            BlockPos checkPos = playerPos.offset(x, y, z);
                            BlockState blockState = world.getBlockState(checkPos);

                            if (blockState.is(Blocks.COBWEB) && world.getBlockState(checkPos.below()).isAir() && world.getBlockState(checkPos.below(2)).isAir())
                            {
                                if (random.nextFloat() < SPAWN_CHANCE && !isSpiderNearby(serverLevel, checkPos))
                                {
                                    spawnSpider(serverLevel, checkPos);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static boolean isSpiderNearby(ServerLevel world, BlockPos pos)
    {
        // Check for nearby spiders within a 3 block radius
        List<SpiderWebSpiderEntity> nearbySpiders = world.getEntitiesOfClass(SpiderWebSpiderEntity.class, new net.minecraft.world.phys.AABB(pos.offset(-3, -3, -3), pos.offset(3, 3, 3)));

        return !nearbySpiders.isEmpty();
    }

    private static void spawnSpider(ServerLevel world, BlockPos pos)
    {
        SpiderWebSpiderEntity spider = new SpiderWebSpiderEntity(CodexaModEntities.SPIDER_WEB_SPIDER.get(), world);
        spider.moveTo(pos.getX() + 0.5, pos.getY() - 2, pos.getZ() + 0.5);
        world.addFreshEntity(spider);
    }
}
