package org.betterx.wover.enchantment.api;

import org.betterx.wover.enchantment.impl.EnchantmentKeyImpl;
import org.betterx.wover.enchantment.impl.EnchantmentManagerImpl;
import org.betterx.wover.events.api.Event;
import org.betterx.wover.events.api.types.OnBootstrapRegistry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;

public class EnchantmentManager {
    public static final Event<OnBootstrapRegistry<Enchantment>> BOOTSTRAP_ENCHANTMENTS = EnchantmentManagerImpl.BOOTSTRAP_ENCHANTMENTS;

    public static EnchantmentKey createKey(ResourceLocation id) {
        return new EnchantmentKeyImpl(ResourceKey.create(Registries.ENCHANTMENT, id));
    }
}