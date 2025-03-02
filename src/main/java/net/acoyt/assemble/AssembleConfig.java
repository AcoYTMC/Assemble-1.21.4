package net.acoyt.assemble;

import eu.midnightdust.lib.config.MidnightConfig;

public class AssembleConfig extends MidnightConfig {
    /* TECHNICAL CATEGORY */
    public static final String technical = "technical";

    @Comment(category = technical, centered = true)
    public static Comment seats;
    @Entry(category = technical, min = 0, max = 1000)
    public static int seatReach = 4;
}
