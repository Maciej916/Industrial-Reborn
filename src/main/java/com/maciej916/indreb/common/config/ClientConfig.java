package com.maciej916.indreb.common.config;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientConfig {

	public static ForgeConfigSpec initClient() {
		ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

		builder.push("general");

		builder.pop();

		return builder.build();
	}
}
