package net.eabky_dev.codexa.item;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import net.minecraft.world.item.Item;

/* This item grants creative flight and allows the player to phase through block*/
public class MantleOfTheUniverse extends Item implements ICurioItem
{
    public MantleOfTheUniverse(Properties pProperties)
    {
        super(pProperties);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack)
    {
        Player player = (Player) slotContext.entity();
        activateMantle(player);
        if(player.getAbilities().flying)
        {
            player.noPhysics = true;
        }
    }

    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return true;
    }

    public static void activateMantle(Player player)
    {
        player.getAbilities().flying = true;
        player.onUpdateAbilities();
    }

    public static void deactivateMantle(Player player)
    {
        player.getAbilities().flying = false;
        player.onUpdateAbilities();
    }
}
