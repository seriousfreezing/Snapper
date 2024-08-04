package dev.spiritstudios.snapper.gui;

import dev.spiritstudios.snapper.Snapper;
import dev.spiritstudios.snapper.gui.widget.ScreenshotListWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Util;

import java.io.File;
import java.io.IOException;

public class ScreenshotScreen extends Screen {
    private Screen parent;
    ScreenshotListWidget screenshotList;

    public ScreenshotScreen(Screen parent) {
        super(Text.translatable("menu.snapper.screenshotmenu"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        if (client == null) return;

        try {
            screenshotList = this.addDrawableChild(new ScreenshotListWidget(client, width, height - 48 - 48, 48, 36, screenshotList, this));
        } catch (IOException e) {
            Snapper.LOGGER.error("Failed to load screenshots", e);
            client.setScreen(parent);
            return;
        }

        addDrawableChild(
                ButtonWidget.builder(Text.translatable("button.snapper.folder"), button ->
                                Util.getOperatingSystem().open(new File(client.runDirectory, "screenshots")))
                        .dimensions(width / 2 - 152, height - 32, 150, 20)
                        .build()
        );

        addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, button -> this.close())
                .dimensions(width / 2 + 2, height - 32, 150, 20)
                .build()
        );

        addDrawableChild(ButtonWidget.builder(Text.literal("TEST PANROAMA (translate too)"), button -> {
                            try {
                                this.client.setScreen(new PanoramaViewerScreen("View Panorama (translate this)", this));
                            } catch (IOException e) {
                                Snapper.LOGGER.info("Kind of curious how this would happen");
                            }
                        })
                        .dimensions(width / 2 + 2, height - 64, 150, 20)
                        .build()
        );
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 20, 0xffffff);
    }
}
