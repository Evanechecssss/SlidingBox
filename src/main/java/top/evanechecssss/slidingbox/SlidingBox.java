package top.evanechecssss.slidingbox;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import top.evanechecssss.slidingbox.init.ABlocksInit;
import top.evanechecssss.slidingbox.network.CommonProxy;

@Mod(
        modid = SlidingBox.MOD_ID,
        name = SlidingBox.MOD_NAME,
        version = SlidingBox.VERSION
)
public class SlidingBox {

    public static final String MOD_ID = "slidingbox";
    public static final String MOD_NAME = "SlidingBox";
    public static final String VERSION = "1.0-SNAPSHOT";

    @SidedProxy(clientSide = "top.evanechecssss.slidingbox.network.ClientProxy",serverSide = "top.evanechecssss.slidingbox.network.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance(MOD_ID)
    public static SlidingBox INSTANCE;

    private static final SimpleNetworkWrapper NETWORK = new SimpleNetworkWrapper(MOD_ID);

    private static final CreativeTabs TAB = new CreativeTabs("sliding_box"){
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Item.getItemFromBlock(ABlocksInit.BOX));
        }

        @Override
        public boolean hasScrollbar() {
            return true;
        }

        @Override
        public boolean hasSearchBar() {
            return true;
        }
    };
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        proxy.preinit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {
        proxy.postinit(event);
    }

    public static CreativeTabs getTab(){
        return TAB;
    }
    public static SimpleNetworkWrapper getWrapper(){
        return NETWORK;
    }
}
