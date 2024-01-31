package cn.ksmcbrigade.QFM.hacks;

import cn.ksmcbrigade.QFM.Hack;
import net.minecraft.client.Minecraft;

public class FullNightHack extends Hack {

    public FullNightHack() {
        super("全亮");
    }

    private double gamma = 0.0D;

    @Override
    public void OnE(){
        this.gamma = Minecraft.getInstance().options.gamma;
    }

    @Override
    public void Up(){
        Minecraft.getInstance().options.gamma=3000.0D;
    }

    @Override
    public void OnD(){
        Minecraft.getInstance().options.gamma=this.gamma;
    }
}
