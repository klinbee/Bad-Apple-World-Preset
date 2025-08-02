package com.klinbee.badapple;

import com.klinbee.badapple.densityfunctions.Frame;
import com.klinbee.badapple.densityfunctions.Grid;
import com.klinbee.badapple.platform.Services;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.levelgen.DensityFunction;

// This class is part of the common project meaning it is shared between all supported loaders. Code written here can only
// import and access the vanilla codebase, libraries used by vanilla, and optionally third party libraries that provide
// common compatible binaries. This means common code can not directly use loader specific concepts such as Forge events
// however it will be compatible with all supported mod loaders.
public class BadAppleWorldCommon {

    // For Frames only
    public static SimpleCache<DensityFunction, byte[]> frameDfCache = new SimpleCache<>(5);

    public static void init() {

        // Easier user and developer management.
        Constants.LOG.info("Hello from Common init of {}! We are currently on {} in a {} environment!",
                            Constants.MOD_NAME,
                            Services.PLATFORM.getPlatformName(),
                            Services.PLATFORM.getEnvironmentName());
    }
}