package org.red.library.world.rule;

public interface HasRule {
    <T> T getRuleValue(Rule<T> rule);

    <T> void setRuleValue(Rule<T> rule, T value);
}
