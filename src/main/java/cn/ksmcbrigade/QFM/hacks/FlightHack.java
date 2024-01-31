package cn.ksmcbrigade.QFM.hacks;

import cn.ksmcbrigade.QFM.Hack;
import cn.ksmcbrigade.QFM.QuickFeatureMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.network.protocol.game.ServerboundPlayerAbilitiesPacket;
import net.minecraft.world.entity.player.Player;

import java.util.Objects;

public class FlightHack extends Hack {

    public FlightHack() {
        super("飞行");
    }

    @Override
    public void Up(){
        Player player = QuickFeatureMenu.player;
        player.getAbilities().mayfly = true;
        if(!Minecraft.getInstance().hasSingleplayerServer()){
            Objects.requireNonNull(Minecraft.getInstance().getConnection()).getConnection().send(new ServerboundPlayerAbilitiesPacket(player.getAbilities()));
        }
    }

    @Override
    public void OnD(){
        Player player = QuickFeatureMenu.player;
        if(!player.isCreative()){
            player.getAbilities().mayfly = false;
            if(!Minecraft.getInstance().hasSingleplayerServer()){
                Objects.requireNonNull(Minecraft.getInstance().getConnection()).getConnection().send(new ServerboundPlayerAbilitiesPacket(player.getAbilities()));
            }
        }
    }
}
