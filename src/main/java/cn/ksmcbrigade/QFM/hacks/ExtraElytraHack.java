package cn.ksmcbrigade.QFM.hacks;

import cn.ksmcbrigade.QFM.Hack;
import cn.ksmcbrigade.QFM.QuickFeatureMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.network.protocol.game.ServerboundClientCommandPacket;
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket;
import net.minecraft.network.protocol.game.ServerboundPlayerCommandPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;

import java.util.Objects;

public class ExtraElytraHack extends Hack {

    public ExtraElytraHack() {
        super("更好的鞘翅");
    }

    private int timer;
    private final Minecraft MC = Minecraft.getInstance();

    @Override
    public void Up(){
        if (MC.player != null) {
            if(timer > 0)
                timer--;
            ItemStack chest = MC.player.getItemBySlot(EquipmentSlot.CHEST);
            if(chest.getItem() != Items.ELYTRA)
                return;
            if(MC.player.isFallFlying())
            {
                if(MC.player.isInWater())
                {
                    sendStartStopPacket();
                    return;
                }
                controlSpeed();
                controlHeight();
                return;
            }
            if(ElytraItem.isFlyEnabled(chest) && MC.options.keyJump.isDown())
                doInstantFly();
        }
    }

    private void sendStartStopPacket()
    {
        ServerboundPlayerCommandPacket packet;
        if (MC.player != null) {
            packet = new ServerboundPlayerCommandPacket(MC.player,ServerboundPlayerCommandPacket.Action.START_FALL_FLYING);
            Objects.requireNonNull(MC.getConnection()).getConnection().send(packet);
        }
    }

    private void controlHeight()
    {

        Vec3 v = null;
        if (MC.player != null) {
            v = MC.player.getViewVector(0);
        }

        if(MC.player!=null){
            if(MC.options.keyJump.isDown())
                MC.player.setDeltaMovement(v.x, v.y + 0.08, v.z);
            else if(MC.options.keyShift.isDown())
                MC.player.setDeltaMovement(v.x, v.y - 0.04, v.z);
        }
    }

    private void controlSpeed()
    {

        float yaw = 0;
        if (MC.player != null) {
            yaw = (float)Math.toRadians(MC.player.getUpVector(0).y);
        }
        Vec3 forward = new Vec3(-Mth.sin(yaw) * 0.05, 0,
                Mth.cos(yaw) * 0.05);

        Vec3 v = MC.player.getViewVector(0);

        if(MC.options.keyUp.isDown())
            MC.player.setDeltaMovement(v.add(forward));
        else if(MC.options.keyDown.isDown())
            MC.player.setDeltaMovement(v.subtract(forward));
    }

    private void doInstantFly()
    {

        if(timer <= 0)
        {
            timer = 20;
            if (MC.player != null) {
                MC.player.setJumping(false);
                MC.player.setSprinting(true);
                MC.player.jumpFromGround();
            }
        }

        sendStartStopPacket();
    }
}
