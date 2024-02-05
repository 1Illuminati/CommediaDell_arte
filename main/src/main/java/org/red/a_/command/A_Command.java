package org.red.a_.command;

import org.apache.commons.lang.NotImplementedException;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.BoundingBox;
import org.jetbrains.annotations.NotNull;
import org.red.CommediaDell_arte;
import org.red.a_.admin.AdminAxe;
import org.red.a_.world.A_Area;
import org.red.a_.world.setting.AreaSettingMainGui;
import org.red.library.A_;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.command.AbstractPlayerCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class A_Command extends AbstractPlayerCommand {
    @Override
    public @NotNull String getName() {
        return "a_";
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull String label, String[] args) {
        A_Player player = A_.getAPlayer((Player) sender);
        switch (args[0]) {
            case "economy":
                A_Player target = A_.getAPlayer(args[1]);

                switch (args[2]) {
                    case "get":
                        sender.sendMessage(target.getName() + ": " + target.getEconomyAccount().getBalance());
                    break;
                    case "set":
                        target.getEconomyAccount().setBalance(Integer.parseInt(args[3]));
                        sender.sendMessage("설정 완료");
                    break;
                    case "add":
                        target.getEconomyAccount().addBalance(Integer.parseInt(args[3]));
                        sender.sendMessage("설정 완료");
                    break;
                    default: return false;
                }
            return true;
            case "area":
                String name = args[2];
                switch (args[1]) {
                    case "setting":
                        A_Area area = CommediaDell_arte.getDell_arteManager().getArea(name);
                        if (area == null) {
                            player.sendMessage("존재하지 않는 Area 입니다.");
                            return false;
                        }

                        player.openInventory(new AreaSettingMainGui(area));
                    break;
                    case "create":
                        Location start = player.getDataMap().getLocation("admin_second_location");
                        World startWorld = start.getWorld();
                        Location end = player.getDataMap().getLocation("admin_first_location");

                        if (!startWorld.equals(end.getWorld())) {
                            throw new IllegalArgumentException("다른 월드입니다.");
                        }

                        CommediaDell_arte.getDell_arteManager().createAArea(startWorld, BoundingBox.of(start, end), name);
                        sender.sendMessage("생성 완료");
                    break;
                    case "delete":
                        CommediaDell_arte.getDell_arteManager().removeArea(name);
                        sender.sendMessage("삭제 완료");
                    break;
                }
            return true;
            case "axe":
                player.getInventory().addItem(AdminAxe.ADMIN_AXE);
            return true;
            case "data":
                throw new NotImplementedException(); // TODO
            default: return false;
        }
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull String label, String[] args) {
        if (args.length == 1) return Arrays.asList("economy", "data", "area", "axe");
        if (args[0].equals("economy")) {
            if (args.length == 2) return null;
            if (args.length == 3) return Arrays.asList("get", "set", "add");
            if (args.length == 4) return Collections.singletonList("[INT]");
        } else if (args[0].equals("area")) {
            if (args.length == 2) return Arrays.asList("create", "setting", "delete");
            if (args.length == 3) return CommediaDell_arte.getDell_arteManager().getAreas().stream().map(A_Area::getName).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }
}
