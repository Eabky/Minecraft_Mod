package net.eabky_dev.codexa.item.custom;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

import java.util.UUID;

public class BossKillerItem extends SwordItem
{
    private static final UUID ATTACK_DAMAGE_MODIFIER = UUID.randomUUID();

    public BossKillerItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Item.Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();

        if (slot == EquipmentSlot.MAINHAND)
        {
            float attackDamage = 3.0f;

            if (stack.hasTag() && stack.getTag().contains("BossKillerDamage"))
            {
                attackDamage += stack.getTag().getFloat("BossKillerDamage");
            }

            builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Boss Killer bonus", attackDamage, AttributeModifier.Operation.ADDITION));
        }

        return builder.build();
    }

    public void setBonusDamage(ItemStack stack, float damage)
    {
        stack.getOrCreateTag().putFloat("BossKillerDamage", damage);
    }
}

