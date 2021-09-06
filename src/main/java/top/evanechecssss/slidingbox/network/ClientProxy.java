package top.evanechecssss.slidingbox.network;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import top.evanechecssss.slidingbox.client.TESRFantomBox;
import top.evanechecssss.slidingbox.common.TEFantomBlock;
import top.evanechecssss.slidingbox.init.ABlocksInit;
import top.evanechecssss.slidingbox.init.AKeyCodeInit;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Evanechecssss
 * \* https://bio.link/evanechecssss
 * \* Description:
 * \
 */
public class ClientProxy extends CommonProxy{
    @Override
    public void preinit(FMLPreInitializationEvent event) {
        AKeyCodeInit.KeybindsRegister.register();
        super.preinit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        ABlocksInit.renderBlock();
        ClientRegistry.bindTileEntitySpecialRenderer(TEFantomBlock.class, new TESRFantomBox());
        super.init(event);
    }

    @Override
    public void postinit(FMLPostInitializationEvent event) {
        super.postinit(event);
    }
}