package dev.spiritstudios.snapper.mixin;

import dev.spiritstudios.snapper.Snapper;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Keyboard.class)
public class KeyboardMixin {
    @Shadow @Final private MinecraftClient client;

    /**
     * @author CallMeEcho
     * @reason Change message logic to show an overlay instead of chat message
     */
    @Overwrite
    private void method_1464(Text text) {
        MutableText message = Text.translatable("text.snapper.screenshot_instructions", text, Snapper.RECENT_SCREENSHOT_KEY.getBoundKeyLocalizedText());
        this.client.inGameHud.setOverlayMessage(message, false);
    }
}
