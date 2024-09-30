package com.klinbee.badapple;


import com.klinbee.badapple.densityfunctions.Frame;
import com.klinbee.badapple.densityfunctions.Grid;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(Constants.MOD_ID)
public class BadAppleWorldModNeo {

//    private static final DeferredRegister<MapCodec<? extends DensityFunction>> DENSITY_FUNCTIONS = DeferredRegister.create(Registries.DENSITY_FUNCTION_TYPE, Constants.MOD_ID);
//
//    public static final RegistryObject<MapCodec<? extends DensityFunction>> GRID = DENSITY_FUNCTIONS.register( "grid_placer", Grid.CODEC::codec);
//    public static final RegistryObject<MapCodec<? extends DensityFunction>> FRAME = DENSITY_FUNCTIONS.register("frame", Frame.CODEC::codec);

    public BadAppleWorldModNeo() {

//        // This method is invoked by the NeoForge mod loader when it is ready
//        // to load your mod. You can access Forge and Common code in this
//        // project.
//
//        // Use Forge to bootstrap the Common mod.
//        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
//
//        DENSITY_FUNCTIONS.register(modEventBus);
//
//        // Register ourselves for server and other game events we are interested in
//        MinecraftForge.EVENT_BUS.register(this);
//        Constants.LOG.info("Hello Forge world!");
//        BadAppleWorldCommon.init();

    }
}