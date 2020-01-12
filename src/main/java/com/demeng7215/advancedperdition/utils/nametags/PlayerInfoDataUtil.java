package com.demeng7215.advancedperdition.utils.nametags;

import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.PlayerInfoData;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class PlayerInfoDataUtil {

	public static PlayerInfoData getPlayerInfoData(Player player) {
		return new PlayerInfoData(WrappedGameProfile.fromPlayer(player), ((CraftPlayer) player).getHandle().ping, EnumWrappers.NativeGameMode.fromBukkit(player.getGameMode()), WrappedChatComponent.fromText(player.getDisplayName()));
	}

	public static List<PlayerInfoData> getPlayerInfoDataList(Player player) {
		return Collections.singletonList(getPlayerInfoData(player));
	}
}
