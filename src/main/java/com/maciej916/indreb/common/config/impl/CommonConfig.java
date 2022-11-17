package com.maciej916.indreb.common.config.impl;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig {

	public static ForgeConfigSpec register(ForgeConfigSpec.Builder builder) {
		builder.push("general");

		builder.pop();


		return builder.build();
	}
}
