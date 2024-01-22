package org.red.game.entity;

public enum AttributeType {
    MELEE_DAMAGE("근접 데미지"),
    RANGE_DAMAGE("원거리 데미지"),
    MAGIC_DAMAGE("마법 데미지"),
    PHYSICAL_DAMAGE("물리 데미지"),
    MAGIC_RESIST("마법 저항력"),
    PHYSICAL_RESIST("물리 저항력"),
    MAGIC_DEFENCE("마법 방어력"),
    PHYSICAL_DEFENCE("물리 방어력"),
    CRITICAL_CHANCE("치명타 확률"),
    CRITICAL_DAMAGE("치명타 데미지"),
    CRITICAL_RESIST("치명타 저항력"),
    DODGE_CHANCE("회피 확률"),
    BLOCK_CHANCE("방어 확률"),
    DAMAGE_REFLECTION("데미지 반사"),
    DAMAGE_ABSORPTION("데미지 흡수"),
    DAMAGE_REDUCTION("받는 데미지"),
    DAMAGE_INCREASE("총 피해량"),
    HEALTH_STEAL("흡혈"),
    MANA_STEAL("마나 흡혈"),
    MANA("마나"),
    MANA_REGEN("마나 재생"),
    MAX_MANA("최대 마나"),
    HEALTH("체력"),
    HEALTH_REGEN("체력 재생"),
    MAX_HEALTH("최대 체력"),
    STAMINA("스태미나"),
    STAMINA_REGEN("스태미나 재생"),
    MAX_STAMINA("최대 스태미나"),
    MOVEMENT_SPEED("이동 속도"),
    FIRE_DAMAGE("화염 데미지"),
    FIRE_RESIST("화염 저항력"),
    ATTACK_SPEED("공격 속도");

    String krName;
    AttributeType(String krName) {
        this.krName = krName;
    }
}
