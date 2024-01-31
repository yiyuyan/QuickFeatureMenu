package cn.ksmcbrigade.QFM.hacks;

import cn.ksmcbrigade.QFM.Hack;
import net.minecraft.client.Minecraft;

public class NoFireOverlayHack extends Hack {

    public NoFireOverlayHack() {
        super("无火渲染");
    }

    @Override
    public void Up(){
        if (Minecraft.getInstance().cameraEntity != null) {
            Minecraft.getInstance().cameraEntity.setSharedFlagOnFire(false);
        }
    }
}
