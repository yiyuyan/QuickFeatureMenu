package cn.ksmcbrigade.QFM.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.Timer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Minecraft.class)
public interface MinecraftMixin {
    @Accessor("timer")
    Timer getTimer();

    @Accessor("timer")
    void setTimer(Timer timer);
}
