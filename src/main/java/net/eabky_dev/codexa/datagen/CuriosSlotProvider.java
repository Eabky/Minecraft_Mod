package net.eabky_dev.codexa.datagen;

import net.eabky_dev.codexa.CODEXA;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import top.theillusivec4.curios.api.CuriosDataProvider;

import java.util.concurrent.CompletableFuture;

public class CuriosSlotProvider extends CuriosDataProvider
{
    public CuriosSlotProvider(String modId, PackOutput output, ExistingFileHelper fileHelper, CompletableFuture<HolderLookup.Provider> registries)
    {
        super(modId, output, fileHelper, registries);
    }

    @SubscribeEvent
    public void gatherData(GatherDataEvent event)
    {
        event.getGenerator().addProvider(
                event.includeServer(),
                new CuriosSlotProvider(
                        CODEXA.MOD_ID,
                        event.getGenerator().getPackOutput(),
                        event.getExistingFileHelper(),
                        event.getLookupProvider()
                )
        );
    }


    @Override
    public void generate(HolderLookup.Provider registries, ExistingFileHelper fileHelper)
    {
        this.createEntities("player")
                .addPlayer()
                .addSlots("head", "necklace", "ring", "ring", "bracelet", "bracelet", "belt", "back", "body", "charm", "glove", "feet");
    }
}
