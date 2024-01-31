package cn.ksmcbrigade.QFM.hacks;

import cn.ksmcbrigade.QFM.Hack;
import cn.ksmcbrigade.QFM.QuickFeatureMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

public class FastAttackHack extends Hack {

    public FastAttackHack() {
        super("快速攻击");
    }

    @Override
    public void Up(){
        QuickFeatureMenu.player.resetAttackStrengthTicker();
    }
}
