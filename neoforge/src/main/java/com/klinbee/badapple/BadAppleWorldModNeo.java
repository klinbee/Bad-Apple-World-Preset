package com.klinbee.badapple;


import com.klinbee.badapple.densityfunctions.Frame;
import com.klinbee.badapple.densityfunctions.Grid;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class BadAppleWorldModNeo {

    public BadAppleWorldModNeo(IEventBus eventBus) {

        // This method is invoked by the NeoForge mod loader when it is ready
        // to load your mod. You can access NeoForge and Common code in this
        // project.

        // Use NeoForge to bootstrap the Common mod.
        Registry.register(BuiltInRegistries.DENSITY_FUNCTION_TYPE, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID,"frame"), Frame.CODEC.codec());
        Registry.register(BuiltInRegistries.DENSITY_FUNCTION_TYPE, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID,"grid_placer"), Grid.CODEC.codec());

        Constants.LOG.info("Hello NeoForge world!");
        BadAppleWorldCommon.init();

    }
}