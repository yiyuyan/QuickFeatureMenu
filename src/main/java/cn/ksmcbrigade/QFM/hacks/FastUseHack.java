package cn.ksmcbrigade.QFM.hacks;

import cn.ksmcbrigade.QFM.Hack;
import cn.ksmcbrigade.QFM.QuickFeatureMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.network.protocol.game.ServerboundPlayerCommandPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;

import java.util.Objects;

public class FastUseHack extends Hack {

    public FastUseHack() {
        super("快速使用");
    }

    private int timer;
    private final Minecraft MC = Minecraft.getInstance();

    @Override
    public void Up(){
        QuickFeatureMenu.player.setTicksFrozen(0);
        QuickFeatureMenu.player.setNoActionTime(0);
    }
}
