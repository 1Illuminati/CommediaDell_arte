package org.red.core.team;

import org.red.library.a_.entity.player.offline.A_OfflinePlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Team {
    private static final Map<String, Team> teamMap = new HashMap<>();

    public static Team getTeam(String name) {
        return teamMap.get(name);
    }


    public static Team createTeam(String name, A_OfflinePlayer leader) {
        Team team = new Team(name, leader);
        teamMap.put(name, team);
        return team;
    }

    private final String name;
    private final A_OfflinePlayer leader;
    private final List<A_OfflinePlayer> members = new ArrayList<>();

    private Team(String name, A_OfflinePlayer leader) {
        this.name = name;
        this.leader = leader;
    }

    public String name() {
        return name;
    }
}
