package cn.ksmcbrigade.QFM.hacks;

import cn.ksmcbrigade.QFM.Hack;
import cn.ksmcbrigade.QFM.QuickFeatureMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

public class AutoSwimHack extends Hack {

    public AutoSwimHack() {
        super("自动游泳");
    }

    @Override
    public void Up(){
        Player player = QuickFeatureMenu.player;
        if(player.horizontalCollision || player.isShiftKeyDown())
            return;

        if(!player.isInWater())
            return;

        if(player.getSwimAmount(0) > 0)
            player.setSprinting(true);
    }
}
