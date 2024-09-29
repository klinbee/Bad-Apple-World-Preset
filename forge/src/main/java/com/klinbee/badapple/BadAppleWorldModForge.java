package com.klinbee.badapple;

import com.klinbee.badapple.densityfunctions.Frame;
import com.klinbee.badapple.densityfunctions.Grid;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class BadAppleWorldModForge {

    public BadAppleWorldModForge() {

        // This method is invoked by the Forge mod loader when it is ready
        // to load your mod. You can access Forge and Common code in this
        // project.

        // Use Forge to bootstrap the Common mod.

        Registry.register(BuiltInRegistries.DENSITY_FUNCTION_TYPE, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID,"frame"), Frame.CODEC.codec());
        Registry.register(BuiltInRegistries.DENSITY_FUNCTION_TYPE, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID,"grid_placer"), Grid.CODEC.codec());

        Constants.LOG.info("Hello Forge world!");
        BadAppleWorldCommon.init();

    }
}