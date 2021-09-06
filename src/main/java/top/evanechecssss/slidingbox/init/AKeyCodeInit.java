package top.evanechecssss.slidingbox.init;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;
import top.evanechecssss.slidingbox.SlidingBox;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Evanechecssss
 * \* https://bio.link/evanechecssss
 * \* Description:
 * \
 */
public class AKeyCodeInit {

    public static final KeyBinding PUSH = new KeyBinding(SlidingBox.MOD_ID + ".push", Keyboard.KEY_LSHIFT,"SLIDING BOX");

    public static class KeybindsRegister {

        public static void register() {
            setRegister(PUSH);
        }

        private static void setRegister(KeyBinding binding) {
            ClientRegistry.registerKeyBinding(binding);
        }
    }
}