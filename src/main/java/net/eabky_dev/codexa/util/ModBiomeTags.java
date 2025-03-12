package net.eabky_dev.codexa.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class ModBiomeTags
{
    private static TagKey<Biome> create(String pName)
    {
        return TagKey.create(Registries.BIOME, ResourceLocation.parse(pName));
    }

    public static final TagKey<Biome> IS_MIDNIGHT_BIOME = create("is_midnight_biome");
}
