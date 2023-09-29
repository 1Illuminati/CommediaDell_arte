package org.red.library.util.map;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class CoolTime implements ConfigurationSerializable {
    private Map<String, Long> map = new HashMap<>();

    public static CoolTime deserialize(Map<String, Object> map) {
        CoolTime coolTime = new CoolTime();
        map.remove("==");

        for (Map.Entry<String, Object> entry : map.entrySet())
            coolTime.map.put(entry.getKey(), Long.valueOf((String) entry.getValue()));

        return coolTime;
    }

    public void setCoolTime(String name, double time) {
        this.setCoolTime(name, time, TimeType.SECOND);
    }

    public void setCoolTime(String name, double time, TimeType type) {
        this.map.put(name, timeToType(time, type) + System.currentTimeMillis());
    }

    public void removeCoolTime(String name) {
        this.map.remove(name);
    }

    public void reduceCoolTime(String name, double reduceSecond) {
        this.reduceCoolTime(name, reduceSecond, TimeType.SECOND);
    }

    public void reduceCoolTime(String name, double reduceSecond, TimeType type) {
        this.map.put(name, this.getCoolTime(name) - timeToType(reduceSecond, type));
    }

    public long getCoolTime(String name) {
        return this.map.computeIfAbsent(name, k -> 0L);
    }

    public double getLessCoolTime(String name) {
        return this.getLessCoolTime(name, TimeType.SECOND);
    }

    public double getLessCoolTime(String name, TimeType type) {
        long lessTime = this.getCoolTime(name) - System.currentTimeMillis();

        return lessTime / (double) timeToType(1, type);
    }

    public boolean checkCoolTime(String name) {
        if (!map.containsKey(name))
            return true;

        if (this.getCoolTime(name) <= System.currentTimeMillis()) {
            this.removeCoolTime(name);
            return true;
        }

        return false;
    }

    public void copy(CoolTime coolTime) {
        this.map = coolTime.map;
    }

    public void copy(Map<String, Long> map) {
        this.map = map;
    }

    public void clear() {
        this.map.clear();
    }

    private int timeToType(double time, TimeType type) {
        switch (type) {
            case YEAR:
                time *= 365;
            case WEEK:
                time *= 7;
            case DAY:
                time *= 24;
            case HOUR:
                time *= 60;
            case MINUTE:
                time *= 60;
            case SECOND:
                time *= 1000;
            case MILLISECOND:
                break;
        }

        return (int) time;
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();

        for (Map.Entry<String, Long> entry : this.map.entrySet())
            map.put(entry.getKey(), String.valueOf(entry.getValue()));

        return map;
    }

    public enum TimeType {
        SECOND, MINUTE, HOUR, DAY, YEAR, WEEK, MILLISECOND
    }
}
