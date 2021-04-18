package stringboot.app;

public class AppInfo {

    public AppInfo(String name){
        this.name = name;
    }

    public String name;

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}