package us.spaceclouds42.disableportals.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.Formatting;
import net.minecraft.text.Text;
import us.spaceclouds42.disableportals.Config;
import us.spaceclouds42.disableportals.DisablePortals;

import java.io.File;

import static net.minecraft.server.command.CommandManager.literal;

public class DisablePortalsCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                literal("disableportals")
                        .requires(CommandManager.requirePermissionLevel(CommandManager.GAMEMASTERS_CHECK))
                        .then(
                                CommandManager.literal("allowNether")
                                        .then(
                                                CommandManager
                                                        .argument("enable", BoolArgumentType.bool())
                                                        .executes(ctx -> setNetherPortals(ctx.getSource(), BoolArgumentType.getBool(ctx, "enable")))
                                        ).executes(ctx -> getNetherPortals(ctx.getSource()))
                        )
                        .then(
                                CommandManager.literal("allowEnd")
                                        .then(
                                                CommandManager
                                                        .argument("enable", BoolArgumentType.bool())
                                                        .executes(ctx -> setEndPortals(ctx.getSource(), BoolArgumentType.getBool(ctx, "enable")))
                                        ).executes(ctx -> getEndPortals(ctx.getSource()))
                        ).then(
                                CommandManager.literal("allowEndGateways")
                                        .then(
                                                CommandManager
                                                        .argument("enable", BoolArgumentType.bool())
                                                        .executes(ctx -> setEndGateways(ctx.getSource(), BoolArgumentType.getBool(ctx, "enable")))
                                        ).executes(ctx -> getEndGateways(ctx.getSource()))
                        ).then(
                                CommandManager
                                        .literal("reloadConfig")
                                        .executes(ctx -> reloadConfig(ctx.getSource()))
                        ).then(
                                CommandManager
                                        .literal("state")
                                        .executes(ctx -> {
                                            getNetherPortals(ctx.getSource());
                                            getEndPortals(ctx.getSource());
                                            getEndGateways(ctx.getSource());
                                            return 1;
                                        })
                        )

        );
    }

    public static int setNetherPortals(ServerCommandSource source, boolean enable) {
        DisablePortals.CONF.main.disableNetherPortals = !enable;
        DisablePortals.CONF.saveConfig(new File(FabricLoader.getInstance().getConfigDir() + "/DisablePortals.json"));

        getNetherPortals(source);
        return 1;
    }

    public static int getNetherPortals(ServerCommandSource source) {
        if (DisablePortals.CONF.main.disableNetherPortals) {
            source.sendFeedback(() ->
                    Text.literal("Nether portals are disabled").formatted(Formatting.RED), false
            );
        } else {
            source.sendFeedback(() ->
                    Text.literal("Nether portals are enabled").formatted(Formatting.GREEN), false
            );
        }
        return 1;
    }

    public static int setEndPortals(ServerCommandSource source, boolean enable) {
        DisablePortals.CONF.main.disableEndPortals = !enable;
        DisablePortals.CONF.saveConfig(new File(FabricLoader.getInstance().getConfigDir() + "/DisablePortals.json"));

        getEndPortals(source);

        return 1;
    }

    public static int getEndPortals(ServerCommandSource source) {
        if (DisablePortals.CONF.main.disableEndPortals) {
            source.sendFeedback(() ->
                    Text.literal("End portals are disabled").formatted(Formatting.RED), false
            );
        } else {
            source.sendFeedback(() ->
                    Text.literal("End portals are enabled").formatted(Formatting.GREEN), false
            );
        }
        return 1;
    }

    public static int setEndGateways(ServerCommandSource source, boolean enable) {
        DisablePortals.CONF.main.disableEndGateways = !enable;
        DisablePortals.CONF.saveConfig(new File(FabricLoader.getInstance().getConfigDir() + "/DisablePortals.json"));

        getEndGateways(source);

        return 1;
    }

    public static int getEndGateways(ServerCommandSource source) {
        if (DisablePortals.CONF.main.disableEndGateways) {
            source.sendFeedback(() ->
                    Text.literal("End Gateway Portals are disabled").formatted(Formatting.RED), false
            );
        } else {
            source.sendFeedback(() ->
                    Text.literal("End Gateway Portals are enabled").formatted(Formatting.GREEN), false
            );
        }
        return 1;
    }

    public static int reloadConfig(ServerCommandSource source) {
        DisablePortals.CONF = Config.loadConfig(new File(FabricLoader.getInstance().getConfigDir() + "/DisablePortals.json"));

        source.sendFeedback(() ->
                Text.translatable("Configuration Reloaded!").formatted(Formatting.GREEN), false
        );

        return 1;
    }
}
