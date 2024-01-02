package org.red.core.player;

import org.red.core.team.Team;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.a_.entity.player.offline.A_OfflinePlayer;
import org.red.library.util.map.DataMap;

public class GamePlayer {
    private final A_OfflinePlayer player;
    private final DataMap dataMap;
    public GamePlayer(A_Player player) {
        this.player = player.getAOfflinePlayer();
        this.dataMap = player.getDataMap().getDataMap("CoreGame");
    }

    public GamePlayer(A_OfflinePlayer player) {
        this.player = player;
        this.dataMap = player.getDataMap().getDataMap("CoreGame");
    }

    public boolean isDead() {
        return dataMap.getBoolean("isDead");
    }

    public int getStr() {
        return dataMap.getInt("str");
    }

    public int getAgi() {
        return dataMap.getInt("agi");
    }

    public int getHel() {
        return dataMap.getInt("hel");
    }

    public void setStr(int str) {
        dataMap.set("str", str);
    }

    public void setAgi(int agi) {
        dataMap.set("agi", agi);
    }

    public void setHel(int hel) {
        dataMap.set("hel", hel);
    }

    public int getStatPoint() {
        return dataMap.getInt("statPoint");
    }

    public void setStatPoint(int statPoint) {
        dataMap.set("statPoint", statPoint);
    }

    public DataMap getDataMap() {
        return dataMap;
    }

    public void setTeam(Team team) {
        dataMap.set("team", team.name());
    }

    public void resetTeam() {
        dataMap.remove("team");
    }

    public boolean isJoinTeam() {
        return dataMap.getString("team").isEmpty();
    }

    public Team getTeam() {
        return Team.getTeam(dataMap.getString("team"));
    }
}
