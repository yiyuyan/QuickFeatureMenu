package cn.ksmcbrigade.QFM.events;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.LogicalSide;

public class TimerEvent extends TickEvent {

    public float time;

    public TimerEvent(Phase phase,float time) {
        super(Type.RENDER, LogicalSide.CLIENT, phase);
        this.time = time;
    }
}
