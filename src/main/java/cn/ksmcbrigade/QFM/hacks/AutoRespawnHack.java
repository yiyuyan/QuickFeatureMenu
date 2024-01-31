package cn.ksmcbrigade.QFM.hacks;

import cn.ksmcbrigade.QFM.Hack;
import cn.ksmcbrigade.QFM.QuickFeatureMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.network.protocol.game.ServerboundClientCommandPacket;

import java.util.Objects;

public class AutoRespawnHack extends Hack {

    public AutoRespawnHack() {
        super("自动重生");
    }

    @Override
    public void Up(){
        if(!QuickFeatureMenu.player.isAlive()){
            if(Minecraft.getInstance().hasSingleplayerServer()){
                QuickFeatureMenu.player.respawn();
            }
            else{
                Objects.requireNonNull(Minecraft.getInstance().getConnection()).getConnection().send(new ServerboundClientCommandPacket(ServerboundClientCommandPacket.Action.PERFORM_RESPAWN));
            }
        }
    }
}
