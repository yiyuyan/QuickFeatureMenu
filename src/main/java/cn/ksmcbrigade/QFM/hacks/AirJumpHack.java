package cn.ksmcbrigade.QFM.hacks;

import cn.ksmcbrigade.QFM.Hack;
import cn.ksmcbrigade.QFM.QuickFeatureMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.network.protocol.game.ServerboundPlayerAbilitiesPacket;
import net.minecraft.world.entity.player.Player;

import java.util.Objects;

public class AirJumpHack extends Hack {

    public AirJumpHack() {
        super("空气跳");
    }

    @Override
    public void Up(){
        Player player = QuickFeatureMenu.player;
        if(Minecraft.getInstance().options.keyJump.isDown()){
            player.jumpFromGround();
        }
    }
}
