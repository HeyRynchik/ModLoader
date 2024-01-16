package com.heyrynchik;

import com.google.errorprone.annotations.Var;
import com.heyrynchik.downloader.core.Downloader;
import com.heyrynchik.downloader.core.Variables;
import net.minecraftforge.eventbus.EventBus;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jline.utils.Log;

import java.io.*;
import java.util.Objects;
import java.util.stream.Collectors;

@Mod(modloader.MODID)
public class modloader {
    public static final String MODID = "modloader";
    public static final Logger LOGGER = LogManager.getLogger(modloader.MODID);
    public static final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

    public modloader() {
        modEventBus.register(new OnClientStart());
    }

    public class OnClientStart {
        @SubscribeEvent
        public void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.info(MODID + " Has started");
            try{
                Downloader downloader = new Downloader(Variables.urlList);
                downloader.download();
            } catch (IOException e) {
                LOGGER.error(e);
            }
        }
    }



}
