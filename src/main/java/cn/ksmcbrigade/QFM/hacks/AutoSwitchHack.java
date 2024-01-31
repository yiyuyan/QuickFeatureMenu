package cn.ksmcbrigade.QFM.hacks;

import cn.ksmcbrigade.QFM.Hack;
import cn.ksmcbrigade.QFM.QuickFeatureMenu;
import net.minecraft.world.entity.player.Inventory;

public class AutoSwitchHack extends Hack {

    public AutoSwitchHack() {
        super("轮切物品栏");
    }

    @Override
    public void Up(){
        Inventory inventory = QuickFeatureMenu.player.getInventory();

        if(inventory.selected == 8)
            inventory.selected = 0;
        else
            inventory.selected++;
    }
}
