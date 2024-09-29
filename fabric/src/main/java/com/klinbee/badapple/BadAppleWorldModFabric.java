package com.klinbee.badapple;

import com.klinbee.badapple.densityfunctions.Frame;
import com.klinbee.badapple.densityfunctions.Grid;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

public class BadAppleWorldModFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        
        // This method is invoked by the Fabric mod loader when it is ready
        // to load your mod. You can access Fabric and Common code in this
        // project.
        Registry.register(BuiltInRegistries.DENSITY_FUNCTION_TYPE, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID,"frame"),Frame.CODEC.codec());
        Registry.register(BuiltInRegistries.DENSITY_FUNCTION_TYPE, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID,"grid_placer"),Grid.CODEC.codec());

        BadAppleWorldCommon.init();
    }
}
