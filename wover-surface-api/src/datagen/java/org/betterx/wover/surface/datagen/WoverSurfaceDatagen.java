package org.betterx.wover.surface.datagen;

import org.betterx.wover.core.api.ModCore;
import org.betterx.wover.datagen.api.WoverDataGenEntryPoint;
import org.betterx.wover.entrypoint.WoverSurface;

public class WoverSurfaceDatagen extends WoverDataGenEntryPoint {
    @Override
    protected void onInitializeProviders() {
        addRegistryProvider(new NoiseRegistryProvider());
    }

    @Override
    protected ModCore modCore() {
        return WoverSurface.C;
    }

}
