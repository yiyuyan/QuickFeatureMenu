package cn.ksmcbrigade.QFM.hacks;

import cn.ksmcbrigade.QFM.Hack;
import cn.ksmcbrigade.QFM.QuickFeatureMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class OnlyMeHack extends Hack {

    public OnlyMeHack() {
        super("伪单人模式");
    }

    @Override
    public void Up(){
        Vec3 vec3 = new Vec3(QuickFeatureMenu.player.getX(),QuickFeatureMenu.player.getY(),QuickFeatureMenu.player.getZ());
        for(Player player: QuickFeatureMenu.player.getCommandSenderWorld().getEntitiesOfClass(Player.class,new AABB(vec3,vec3).inflate(5000))){
            if(!player.equals(QuickFeatureMenu.player)){
                player.setInvisible(true);
            }
        }
    }

    @Override
    public void OnD(){
        Vec3 vec3 = new Vec3(QuickFeatureMenu.player.getX(),QuickFeatureMenu.player.getY(),QuickFeatureMenu.player.getZ());
        for(Player player: QuickFeatureMenu.player.getCommandSenderWorld().getEntitiesOfClass(Player.class,new AABB(vec3,vec3).inflate(5000))){
            player.setInvisible(false);
        }
    }
}
