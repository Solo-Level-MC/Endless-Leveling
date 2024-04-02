package com.airijko.endlessskills.providers;

import com.airijko.endlesscore.interfaces.AttributeModifierInterface;
import com.airijko.endlessskills.managers.PlayerDataManager;
import com.airijko.endlessskills.skills.SkillAttributes;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;


public class EndlessSkillsModifierProvider implements AttributeModifierInterface {
    private final PlayerDataManager playerDataManager;
    private final SkillAttributes skillAttributes;

    public EndlessSkillsModifierProvider(PlayerDataManager playerDataManager, SkillAttributes skillAttributes) {
        this.playerDataManager = playerDataManager;
        this.skillAttributes = skillAttributes;
    }


    @Override
    public Map<String, Double> getModifiers(String attributeName, Player player) {
        int level;
        if ("Toughness".equals(attributeName) || "Knockback_Resistance".equals(attributeName)) {
            level = playerDataManager.getAttributeLevel(player.getUniqueId(), "Tenacity");
        } else if ("Attack_Speed".equals(attributeName) || "Movement_Speed".equals(attributeName)) {
            level = playerDataManager.getAttributeLevel(player.getUniqueId(), "Haste");
        } else {
            level = playerDataManager.getAttributeLevel(player.getUniqueId(), attributeName);
        }
        Map<String, Double> attributeModifiers = new HashMap<>();
        attributeModifiers.put(attributeName, skillAttributes.getModifiedValue(attributeName, level));

        return attributeModifiers;
    }

    @Override
    public Set<String> getAttributeNames() {
        return new HashSet<>(Arrays.asList("Life_Force", "Strength", "Toughness", "Knockback_Resistance", "Movement_Speed", "Attack_Speed", "Precision", "Ferocity"));
    }
}