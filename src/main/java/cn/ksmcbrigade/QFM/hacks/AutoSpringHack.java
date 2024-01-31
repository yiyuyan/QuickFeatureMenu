package cn.ksmcbrigade.QFM.hacks;

import cn.ksmcbrigade.QFM.Hack;
import cn.ksmcbrigade.QFM.QuickFeatureMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

public class AutoSpringHack extends Hack {

    public AutoSpringHack() {
        super("强制疾跑");
    }

    @Override
    public void Up(){
        Minecraft.getInstance().options.keySprint.setDown(true);
    }
}
