package cn.ksmcbrigade.QFM.hacks;

import cn.ksmcbrigade.QFM.Hack;
import cn.ksmcbrigade.QFM.QuickFeatureMenu;

public class NoNightHack extends Hack {

    public NoNightHack() {
        super("白天");
    }

    private long time = 0L;

    @Override
    public void OnE(){
        if(QuickFeatureMenu.player!=null){
            this.time = QuickFeatureMenu.player.getCommandSenderWorld().getDayTime();
        }
    }

    @Override
    public void Up(){
        QuickFeatureMenu.player.level.setSkyFlashTime(1000);
    }

    @Override
    public void OnD(){
        if(QuickFeatureMenu.player!=null){
            QuickFeatureMenu.player.getCommandSenderWorld().setSkyFlashTime((int)this.time);
        }
    }
}
