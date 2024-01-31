package cn.ksmcbrigade.QFM;

public class Hack {
    private final String name;
    private boolean enabled;

    public Hack(String name){
        this.name = name;
    }

    public void OnE(){
    }
    public void Up(){}
    public void OnD(){
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if(enabled){
            this.OnE();
        }
        else{
            this.OnD();
        }
    }
    public boolean isEnabled(){
        return this.enabled;
    }

    public String getName(){
        return this.name;
    }
}
