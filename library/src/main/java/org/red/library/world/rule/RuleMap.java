package org.red.library.world.rule;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.HashMap;
import java.util.Map;

public final class RuleMap implements ConfigurationSerializable {
    private final Rule<?>[] rules;
    private final Object[] values;

    public RuleMap() {
        this.rules = Rule.RULES;
        this.values = new Object[rules.length];

        for (int i = 0; i < rules.length; i++) {
            values[i] = rules[i].getDefaultValue();
        }
    }

    public <T> T get(Rule<T> rule) {
        for (int i = 0; i < rules.length; i++) {
            if (rules[i].equals(rule)) {
                return (T) values[i];
            }
        }

        return null;
    }

    public <T> void set(Rule<T> rule, T value) {
        for (int i = 0; i < rules.length; i++) {
            if (rules[i].equals(rule)) {
                values[i] = value;
                return;
            }
        }
    }

    public void copy(RuleMap ruleMap) {
        System.arraycopy(ruleMap.values, 0, values, 0, rules.length);
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();

        for (int i = 0; i < rules.length; i++) {
            map.put(rules[i].getKey(), values[i]);
        }

        return map;
    }

    public static RuleMap deserialize(Map<String, Object> map) {
        RuleMap ruleMap = new RuleMap();

        for (int i = 0; i < ruleMap.rules.length; i++) {
            ruleMap.values[i] = map.get(ruleMap.rules[i].getKey());
        }

        return ruleMap;
    }
}
