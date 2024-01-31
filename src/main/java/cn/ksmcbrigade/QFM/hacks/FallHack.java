package cn.ksmcbrigade.QFM.hacks;

import cn.ksmcbrigade.QFM.Hack;
import cn.ksmcbrigade.QFM.QuickFeatureMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraft.world.entity.player.Player;

import java.util.Objects;

public class FallHack extends Hack {

    public FallHack() {
        super("Fall");
    }

    @Override
    public void Up(){
        QuickFeatureMenu.player.startFallFlying();
        QuickFeatureMenu.player.startFallFlying();
    }
}
