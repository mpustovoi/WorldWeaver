package org.betterx.wover.surface.impl.conditions;

import org.betterx.wover.entrypoint.WoverSurface;
import org.betterx.wover.legacy.api.LegacyHelper;
import org.betterx.wover.surface.api.conditions.ConditionRegistry;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.SurfaceRules;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

public class MaterialConditionRegistryImpl {
    public static ResourceKey<Codec<? extends SurfaceRules.ConditionSource>> register(
            ResourceKey<Codec<? extends SurfaceRules.ConditionSource>> key,
            Codec<? extends SurfaceRules.ConditionSource> codec,
            boolean withBCLibLegacy
    ) {
        Registry.register(BuiltInRegistries.MATERIAL_CONDITION, key, codec);
        if (withBCLibLegacy) {
            Registry.register(
                    BuiltInRegistries.MATERIAL_CONDITION,
                    WoverSurface.C.legacyBCLibId(key.location()),
                    LegacyHelper.wrap(codec)
            );
        }

        return key;
    }

    @NotNull
    public static ResourceKey<Codec<? extends SurfaceRules.ConditionSource>> createKey(ResourceLocation location) {
        return ResourceKey.create(
                BuiltInRegistries.MATERIAL_CONDITION.key(),
                location
        );
    }

    @ApiStatus.Internal
    public static void bootstrap() {
        register(ConditionRegistry.THRESHOLD_CONDITION, ThresholdConditionImpl.CODEC, true);
        register(ConditionRegistry.VOLUME_THRESHOLD_CONDITION, VolumeThresholdConditionImpl.CODEC, true);
        register(ConditionRegistry.ROUGH_NOISE_CONDITION, RoughNoiseConditionImpl.CODEC, true);
    }
}
