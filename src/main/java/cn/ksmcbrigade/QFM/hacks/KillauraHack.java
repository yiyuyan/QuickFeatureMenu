package cn.ksmcbrigade.QFM.hacks;

import cn.ksmcbrigade.QFM.Hack;
import cn.ksmcbrigade.QFM.QuickFeatureMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.protocol.game.ServerboundInteractPacket;
import net.minecraft.network.protocol.game.ServerboundSwingPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.Objects;

public class KillauraHack extends Hack {

    public KillauraHack() {
        super("杀戮光环");
    }

    @Override
    public void Up(){
        Minecraft MC = Minecraft.getInstance();
        Player player = QuickFeatureMenu.player;
        Level world = player.getLevel();
        Vec3 vec3 = new Vec3(player.getX(),player.getY(),player.getZ());
        for(LivingEntity entity:world.getEntitiesOfClass(LivingEntity.class, new AABB(vec3,vec3).inflate(10))){
            if(entity!=player && player.canAttack(entity)){
                if(MC.hasSingleplayerServer()){
                    player.attack(entity);
                    player.swing(player.getUsedItemHand());
                }
                else{
                    Objects.requireNonNull(MC.getConnection()).getConnection().send(ServerboundInteractPacket.createAttackPacket(entity,false));
                    Objects.requireNonNull(MC.getConnection()).getConnection().send(new ServerboundSwingPacket(player.getUsedItemHand()));
                }
            }
        }
    }
}
