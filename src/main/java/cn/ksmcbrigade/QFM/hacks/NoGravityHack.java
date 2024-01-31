package cn.ksmcbrigade.QFM.hacks;

import cn.ksmcbrigade.QFM.Hack;
import cn.ksmcbrigade.QFM.QuickFeatureMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

public class NoGravityHack extends Hack {

    public NoGravityHack() {
        super("无重力");
    }

    @Override
    public void Up(){
        if (Minecraft.getInstance().cameraEntity != null) {
            Minecraft.getInstance().cameraEntity.setNoGravity(true);
        }
    }

    @Override
    public void OnD(){
        if (Minecraft.getInstance().cameraEntity != null) {
            Minecraft.getInstance().cameraEntity.setNoGravity(false);
        }
    }
}
