package me.houserlab.shrineSpawn.menus;

import io.papermc.paper.dialog.Dialog;
import io.papermc.paper.dialog.DialogResponseView;
import io.papermc.paper.registry.data.dialog.ActionButton;
import io.papermc.paper.registry.data.dialog.DialogBase;
import io.papermc.paper.registry.data.dialog.action.DialogAction;
import io.papermc.paper.registry.data.dialog.body.DialogBody;
import io.papermc.paper.registry.data.dialog.type.DialogType;
import me.houserlab.shrineSpawn.ShrineSpawn;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickCallback;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class ShrineDialogMenu {

    public static void openMainMenu(Player player) {
        ActionButton viewConfigBtn = ActionButton.builder(Component.text("View Config"))
                .action(DialogAction.customClick(
                        (DialogResponseView view, Audience audience) -> {
                            if (audience instanceof Player p) {
                                openConfigDialog(p);
                            }
                        },
                        ClickCallback.Options.builder().uses(1).build()
                ))
                .build();

        ActionButton futureMenuBtn = ActionButton.builder(Component.text("Future Menu"))
                .action(DialogAction.customClick(
                        (DialogResponseView view, Audience audience) -> {
                            if (audience instanceof Player p) {
                                openFutureDialog(p);
                            }
                        },
                        ClickCallback.Options.builder().uses(1).build()
                ))
                .build();

        ActionButton closeBtn = ActionButton.builder(Component.text("Close"))
                .action(null)
                .build();

        DialogBase base = DialogBase.builder(Component.text("ShrineSpawn Menu"))
                .build();

        // TODO: verify — Dialog.create() takes Consumer<RegistryBuilderFactory<Dialog, ? extends DialogRegistryEntry.Builder>>.
        // The .empty() call and exact builder chain are unconfirmed. Adjust if this does not compile.
        Dialog dialog = Dialog.create(builder -> builder
                .empty()
                .base(base)
                .type(DialogType.multiAction(List.of(viewConfigBtn, futureMenuBtn, closeBtn)).build())
        );

        // TODO: verify — player.showDialog() was not found on the Paper Player interface during API research.
        // May need to be sendDialog(), or called via a Paper-specific player extension.
        player.showDialog(dialog);
    }

    public static void openConfigDialog(Player player) {
        FileConfiguration config = ShrineSpawn.getInstance().getConfig();

        List<DialogBody> body = List.of(
                DialogBody.plainMessage(Component.text("--- World Settings ---")),
                DialogBody.plainMessage(Component.text("main-world: " + config.getString("worlds.main-world"))),
                DialogBody.plainMessage(Component.text("heaven-world: " + config.getString("worlds.heaven-world"))),
                DialogBody.plainMessage(Component.text("hell-enabled: " + config.getBoolean("worlds.hell-enabled"))),
                DialogBody.plainMessage(Component.text("hell-world: " + config.getString("worlds.hell-world"))),
                DialogBody.plainMessage(Component.text("auto-create-worlds: " + config.getBoolean("worlds.auto-create-worlds"))),
                DialogBody.plainMessage(Component.text("afterlife-world-type: " + config.getString("worlds.afterlife-world-type"))),
                DialogBody.plainMessage(Component.text("--- Respawn Settings ---")),
                DialogBody.plainMessage(Component.text("return-location: " + config.getString("respawn.return-location"))),
                DialogBody.plainMessage(Component.text("cost.enabled: " + config.getBoolean("respawn.cost.enabled"))),
                DialogBody.plainMessage(Component.text("cost.item: " + config.getString("respawn.cost.item"))),
                DialogBody.plainMessage(Component.text("cost.base-amount: " + config.getInt("respawn.cost.base-amount"))),
                DialogBody.plainMessage(Component.text("cost.amount-per-death: " + config.getInt("respawn.cost.amount-per-death"))),
                DialogBody.plainMessage(Component.text("cost.max-amount: " + config.getInt("respawn.cost.max-amount"))),
                DialogBody.plainMessage(Component.text("puzzle.enabled: " + config.getBoolean("respawn.puzzle.enabled"))),
                DialogBody.plainMessage(Component.text("puzzle.required: " + config.getBoolean("respawn.puzzle.required"))),
                DialogBody.plainMessage(Component.text("--- Debug ---")),
                DialogBody.plainMessage(Component.text("debug: " + config.getBoolean("debug")))
        );

        ActionButton backBtn = ActionButton.builder(Component.text("Back"))
                .action(DialogAction.customClick(
                        (DialogResponseView view, Audience audience) -> {
                            if (audience instanceof Player p) {
                                openMainMenu(p);
                            }
                        },
                        ClickCallback.Options.builder().uses(1).build()
                ))
                .build();

        DialogBase base = DialogBase.builder(Component.text("Plugin Configuration"))
                .body(body)
                .build();

        // TODO: verify — same Dialog.create() and player.showDialog() uncertainty as openMainMenu().
        Dialog dialog = Dialog.create(builder -> builder
                .empty()
                .base(base)
                .type(DialogType.notice(backBtn))
        );

        player.showDialog(dialog);
    }

    public static void openFutureDialog(Player player) {
        ActionButton backBtn = ActionButton.builder(Component.text("OK / Back"))
                .action(DialogAction.customClick(
                        (DialogResponseView view, Audience audience) -> {
                            if (audience instanceof Player p) {
                                openMainMenu(p);
                            }
                        },
                        ClickCallback.Options.builder().uses(1).build()
                ))
                .build();

        DialogBase base = DialogBase.builder(Component.text("Future Menu"))
                .build();

        // TODO: verify — same Dialog.create() and player.showDialog() uncertainty as openMainMenu().
        Dialog dialog = Dialog.create(builder -> builder
                .empty()
                .base(base)
                .type(DialogType.notice(backBtn))
        );

        player.showDialog(dialog);
    }
}
