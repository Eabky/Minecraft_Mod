package net.eabky_dev.codexa.item;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import net.minecraft.world.item.Item;

    /**
        This item grants creative flight and allows the player to phase through block
    **/

    /*
        TODO: FIX ->
            If I activate the mantle while on the ground I get the normal movement speed and fly slow in x and y axis and normal in y but if I activate it in the air I fly as normal.
            Needs to save, load and unload data so the player doesn't fall.
            or suffocate when logging back in and it doesn't keep its "effect" even if it is removed.
            Might need to deal with capabilities.
     */

public class MantleOfTheUniverse extends Item implements ICurioItem
{
    private static boolean isMantleActive = false;

    public MantleOfTheUniverse(Properties pProperties)
    {
        super(pProperties);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack)
    {
        Player player = (Player) slotContext.entity();
        player.getAbilities().flying  = isMantleActive();
        player.getAbilities().mayfly = isMantleActive();
        //check how to access capabilities and make player not drown and be fire and lava immune
        player.noPhysics = isMantleActive();
    }

    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return true;
    }

    public static boolean isMantleActive()
    {
        return isMantleActive;
    }

    public static void setMantleActive(boolean active)
    {
        isMantleActive = active;
    }
}
