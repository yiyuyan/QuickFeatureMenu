package cn.ksmcbrigade.QFM.hacks;

import cn.ksmcbrigade.QFM.Hack;
import cn.ksmcbrigade.QFM.QuickFeatureMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class BoatFlyHack extends Hack {

    public BoatFlyHack() {
        super("船飞");
    }

    @Override
    public void Up(){
        Minecraft MC = Minecraft.getInstance();
        Player player = QuickFeatureMenu.player;
        if(player.getVehicle()==null){
            return;
        }
        Entity vehicle = player.getVehicle();
        Vec3 velocity = vehicle.getViewVector(0);
        double motionY = MC.options.keyJump.isDown() ? QuickFeatureMenu.BF : 0;
        vehicle.setDeltaMovement(velocity.x, motionY, velocity.z);
    }
}
