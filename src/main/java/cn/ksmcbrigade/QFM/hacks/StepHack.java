package cn.ksmcbrigade.QFM.hacks;

import cn.ksmcbrigade.QFM.Hack;
import cn.ksmcbrigade.QFM.QuickFeatureMenu;
import net.minecraft.world.entity.player.Player;

public class StepHack extends Hack {

    public StepHack() {
        super("最大升阶");
    }

    @Override
    public void Up(){
        QuickFeatureMenu.player.maxUpStep=10F;
    }

    @Override
    public void OnD(){
        QuickFeatureMenu.player.maxUpStep=0.5F;
    }
}
