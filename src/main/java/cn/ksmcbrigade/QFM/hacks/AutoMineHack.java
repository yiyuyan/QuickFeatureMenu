package cn.ksmcbrigade.QFM.hacks;

import cn.ksmcbrigade.QFM.Hack;
import net.minecraft.client.Minecraft;

public class AutoMineHack extends Hack {

    public AutoMineHack() {
        super("自动挖");
    }

    @Override
    public void Up(){
        Minecraft.getInstance().options.keyAttack.setDown(true);
    }

    @Override
    public void OnD(){
        Minecraft.getInstance().options.keyAttack.setDown(false);
    }
}
