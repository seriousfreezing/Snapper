package dev.spiritstudios.snapper;

import dev.spiritstudios.specter.api.config.Config;
import dev.spiritstudios.specter.api.config.ConfigHolder;
import dev.spiritstudios.specter.api.config.Value;

public class SnapperConfig extends Config<SnapperConfig> {
    public static final ConfigHolder<SnapperConfig, ?> HOLDER = ConfigHolder.builder(
            Snapper.id("snapper"), SnapperConfig.class
    ).build();
    public static final SnapperConfig INSTANCE = HOLDER.get();

    public final Value<Boolean> copyTakenScreenshot = booleanValue(false)
            .comment("Whether to copy screenshots to clipboard when taken.")
            //PREFERRED TOOLTIP: Copy screenshot to clipboard when taken
            .build();

    public final Value<Boolean> showSnapperTitleScreen = booleanValue(true)
            .comment("Whether to show Snapper button on title screen.")
            //PREFERRED TOOLTIP: Show Snapper button on title screen
            .build();

    public final Value<Boolean> showSnapperGameMenu = booleanValue(true)
            .comment("Whether to show Snapper button in game menu.")
            //PREFERRED TOOLTIP: Show Snapper button in game menu
            .build();
}