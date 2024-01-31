package cn.ksmcbrigade.QFM.hacks;

import cn.ksmcbrigade.QFM.Hack;
import net.minecraft.client.Minecraft;

public class NoOverlayHack extends Hack {

    public NoOverlayHack() {
        super("无覆盖");
    }

    @Override
    public void Up(){
        Minecraft.getInstance().setOverlay(null);
        //Use Event
    }
}
