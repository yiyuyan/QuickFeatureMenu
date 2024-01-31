package cn.ksmcbrigade.QFM.hacks;

import cn.ksmcbrigade.QFM.Hack;
import cn.ksmcbrigade.QFM.QuickFeatureMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.Objects;

public class NoFallHack extends Hack {

    public NoFallHack() {
        super("无摔落伤害");
    }

    @Override
    public void Up(){
        Minecraft MC = Minecraft.getInstance();
        Player player = QuickFeatureMenu.player;
        if(MC.hasSingleplayerServer()){
            player.fallDistance=0;
        }
        else{
            if (player != null && player.fallDistance <= (player.isFallFlying() ? 1 : 2)) {
                return;
            }
            if (player != null && player.isFallFlying() && MC.options.keyShift.isDown() && player.getViewVector(0).y < -0.5) {
                return;
            }
            Objects.requireNonNull(MC.getConnection()).getConnection().send(new ServerboundMovePlayerPacket.StatusOnly(true));
        }
    }
}
