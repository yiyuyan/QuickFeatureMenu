package cn.ksmcbrigade.QFM.hacks;

import cn.ksmcbrigade.QFM.Hack;
import net.minecraft.client.Minecraft;

public class SneakHack extends Hack {

    public SneakHack() {
        super("强制潜行");
    }

    @Override
    public void Up(){
        Minecraft.getInstance().options.keyShift.setDown(true);
    }
}
