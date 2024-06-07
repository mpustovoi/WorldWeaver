package org.betterx.wover.complex.api.tool;

import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ToolTier {

    public static final ToolSlot.PropertiesBuilder DIGGER_ITEM_PROPERTIES = (slot, tier) -> {
        var values = tier.getValues(slot);
        if (values == null)
            throw new IllegalArgumentException("No values for slot " + slot + " in tier " + tier);
        return new Item.Properties().attributes(DiggerItem.createAttributes(tier.toolTier, values.attackDamage, values.attackSpeed));
    };

    public static final ToolSlot.PropertiesBuilder SWORD_ITEM_PROPERTIES = (slot, tier) -> {
        var values = tier.getValues(slot);
        if (values == null)
            throw new IllegalArgumentException("No values for slot " + slot + " in tier " + tier);
        return new Item.Properties().attributes(SwordItem.createAttributes(tier.toolTier, (int) values.attackDamage, values.attackSpeed));
    };

    public record ToolValues(float attackDamage, float attackSpeed) {
    }

    public final String name;
    public final Tier toolTier;

    private final ToolValues[] toolValues;

    private ToolTier(
            String name,
            Tier toolTier,
            ToolValues[] toolValues
    ) {
        this.toolTier = toolTier;
        this.toolValues = toolValues;
        this.name = name;
    }

    @Nullable
    public ToolValues getValues(ToolSlot slot) {
        return toolValues[slot.slotIndex];
    }

    public static ToolTier.Builder builder(String name) {
        return new ToolTier.Builder(name);
    }

    //a Builder class
    public static class Builder {
        private Tier toolTier;
        private final ToolValues[] toolValues = new ToolValues[ToolSlot.values().length];
        private final String name;

        Builder(String name) {
            this.name = name;
        }

        Builder toolTier(Tier toolTier) {
            this.toolTier = toolTier;
            return this;
        }

        public Builder toolValues(ToolSlot slot, ToolValues toolValues) {
            this.toolValues[slot.slotIndex] = toolValues;
            return this;
        }

        public ToolTier build() {
            return new ToolTier(name, toolTier, toolValues);
        }
    }

    @Override
    public String toString() {
        return "ToolTier - " + this.name;
    }

    /**
     * Create a new ToolTier for the specified Tier where all Values are offset by the given amount
     *
     * @param newName New Name to use for the copy
     * @param newTier New Tier to use or null if the one from this ToolTier should be used
     * @param offset  Offset to apply to all values
     * @return New ToolTier with the specified offset
     */
    public ToolTier copyWithOffset(@NotNull String newName, @Nullable Tier newTier, ToolValues offset) {
        ToolValues[] newValues = new ToolValues[toolValues.length];
        for (int i = 0; i < toolValues.length; i++) {
            newValues[i] = new ToolValues(
                    toolValues[i].attackDamage + offset.attackDamage,
                    toolValues[i].attackSpeed + offset.attackSpeed
            );
        }
        return new ToolTier(newName, newTier == null ? this.toolTier : newTier, newValues);
    }
}