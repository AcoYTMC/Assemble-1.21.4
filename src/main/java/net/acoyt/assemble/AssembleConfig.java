package net.acoyt.assemble;

import eu.midnightdust.lib.config.MidnightConfig;

public class AssembleConfig extends MidnightConfig {
    /* TECHNICAL CATEGORY */
    public static final String technical = "technical";

    @Entry(category = technical, min = 1, max = 1000)
    public static float brickDamageMultiplier = 1.5f;

    public static int encode() {
        StringBuilder builder = new StringBuilder();
        String encoding = builder.toString() + brickDamageMultiplier;
        return encoding.hashCode();
    }
}
