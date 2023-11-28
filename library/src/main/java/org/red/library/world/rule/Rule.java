package org.red.library.world.rule;

public final class Rule<T> {
    public static final Rule<Boolean> SWAP_HAND = new Rule<>("swap_hand", true);
    public static final Rule<Boolean> BREED = new Rule<>("breed", true);
    public static final Rule<Boolean> RIDING = new Rule<>("riding", true);
    public static final Rule<Boolean> DROP = new Rule<>("drop", true);
    public static final Rule<Boolean> MOVE = new Rule<>("move", true);
    public static final Rule<Boolean> INTERACT = new Rule<>("interact", true);
    public static final Rule<Boolean> COMMAND = new Rule<>("command", true);
    public static final Rule<Boolean> PLACE = new Rule<>("place", true);
    public static final Rule<Boolean> BREAK = new Rule<>("break", true);
    public static final Rule<Boolean> PVP = new Rule<>("pvp", true);
    public static final Rule<Boolean> ATTACK = new Rule<>("attack", true);
    public static final Rule<Boolean> FISHING = new Rule<>("fishing", true);
    public static final Rule<Boolean> CHAT = new Rule<>("chat", true);
    public static final Rule<Boolean> FALL_DAMAGE = new Rule<>("fall_damage", true);
    public static final Rule<Boolean> SPAWN_MOB = new Rule<>("spawn_mob", true);
    public static final Rule<Boolean> SPAWN_ENTITY = new Rule<>("spawn_entity", true);
    public static final Rule<?>[] RULES = {
            SWAP_HAND,
            BREED,
            RIDING,
            DROP,
            MOVE,
            INTERACT,
            COMMAND,
            PLACE,
            BREAK,
            PVP,
            ATTACK,
            FISHING,
            CHAT,
            SPAWN_MOB,
            SPAWN_ENTITY,
            FALL_DAMAGE
    };

    public static Rule<?> getRuleByKey(String key) {
        for (Rule<?> rule : RULES) {
            if (rule.getKey().equals(key)) return rule;
        }

        return null;
    }

    private final String key;
    private final T defaultValue;
    private Rule(String key, T defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }

    public String getKey() {
        return key;
    }

    public T getDefaultValue() {
        return defaultValue;
    }
}
