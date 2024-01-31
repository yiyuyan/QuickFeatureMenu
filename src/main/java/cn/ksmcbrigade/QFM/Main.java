package cn.ksmcbrigade.QFM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main extends JDialog {
    public static boolean Init = false;
    private static Main Instance;

    public static void main(int x,int y) {
        setLookAndFeel();
        new Main(x,y);
    }

    public Main(int x,int y){
        super((JFrame)null, "Quick Feature Menu", false);
        setAlwaysOnTop(true);
        if(x==0 && y==0){
            setSize(550, 180);
            setResizable(false);
            setLocationRelativeTo(null);
        }
        else{
            setBounds(x,y,550, 180);
            setResizable(false);
        }
        setLayout(new BorderLayout());
        setExitOnClose(this);

        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        Hack[] hacks = QuickFeatureMenu.hacks;
        for (int i = hacks.length - 1; i >= 0; i--) {
            Hack hack = hacks[i];
            JCheckBox checkbox = new JCheckBox(hack.getName());
            checkbox.setSelected(hack.isEnabled());
            checkbox.addActionListener(e -> {
                hack.setEnabled(checkbox.isSelected());
                Utils.save();
            });
            jPanel.add(checkbox);
        }

        add(jPanel);
        setVisible(true);
        toFront();
        Instance = this;
        Init = true;
    }

    public static Main getInstance(){
        return Instance;
    }

    public static void setLookAndFeel()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        }catch(ReflectiveOperationException | UnsupportedLookAndFeelException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void setExitOnClose(JDialog dialog)
    {
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        dialog.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                Main.Init=false;
            }
        });
    }
}
