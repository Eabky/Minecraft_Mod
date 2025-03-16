package net.eabky_dev.codexa.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class RingOfDefiance extends Item implements ICurioItem
{

    public RingOfDefiance(Properties pProperties)
    {
        super(new Properties().stacksTo(1).defaultDurability(10000));
    }

    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return true;
    }
}
