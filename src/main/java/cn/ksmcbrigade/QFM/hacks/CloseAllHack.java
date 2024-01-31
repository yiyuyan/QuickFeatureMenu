package cn.ksmcbrigade.QFM.hacks;

import cn.ksmcbrigade.QFM.Hack;
import cn.ksmcbrigade.QFM.QuickFeatureMenu;
import cn.ksmcbrigade.QFM.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

public class CloseAllHack extends Hack {

    public CloseAllHack() {
        super("关闭所有功能");
    }

    private String nam;

    @Override
    public void OnE(){
        this.nam = getName();
        for(Hack hack : QuickFeatureMenu.hacks){
            if(!hack.getName().equalsIgnoreCase(this.nam) && hack.isEnabled()){
                Utils.SetHackEnabled(hack.getName(),false);
            }
        }
        Utils.SetHackEnabled(this.nam,false);
    }
}
