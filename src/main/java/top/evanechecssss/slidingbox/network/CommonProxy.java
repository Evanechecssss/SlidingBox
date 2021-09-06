package top.evanechecssss.slidingbox.network;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import top.evanechecssss.slidingbox.SlidingBox;
import top.evanechecssss.slidingbox.handler.GUIHandler;
import top.evanechecssss.slidingbox.init.ABlocksInit;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Evanechecssss
 * \* https://bio.link/evanechecssss
 * \* Description:
 * \
 */
public class CommonProxy {

    public void preinit(FMLPreInitializationEvent event) {
       ABlocksInit.registerBlock();
       NetFantomBox.register(0);
    }

    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(SlidingBox.INSTANCE, new GUIHandler());
    }

    public void postinit(FMLPostInitializationEvent event) {

    }
}