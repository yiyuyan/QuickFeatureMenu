package cn.ksmcbrigade.QFM.hacks;

import cn.ksmcbrigade.QFM.Hack;
import cn.ksmcbrigade.QFM.QuickFeatureMenu;

public class NoWeatherHack extends Hack {

    public NoWeatherHack() {
        super("无天气");
    }

    private float rain;
    private float thu;

    @Override
    public void OnE(){
        if(QuickFeatureMenu.player!=null){
            this.rain = QuickFeatureMenu.player.level.getRainLevel(0);
            this.thu = QuickFeatureMenu.player.level.getThunderLevel(0);
        }
    }

    @Override
    public void Up(){
        QuickFeatureMenu.player.getCommandSenderWorld().setRainLevel(0);
        QuickFeatureMenu.player.getCommandSenderWorld().setThunderLevel(0);
    }

    @Override
    public void OnD(){
        if(QuickFeatureMenu.player!=null){
            QuickFeatureMenu.player.getCommandSenderWorld().setRainLevel(this.rain);
            QuickFeatureMenu.player.getCommandSenderWorld().setThunderLevel(this.thu);
        }
    }
}
