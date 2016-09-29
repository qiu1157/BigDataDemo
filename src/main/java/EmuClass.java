/**
 * Created by qiuxiangu on 2016/9/29.
 */
public enum EmuClass {
    AllkwKey("AllkwKey", 1), AppkwKey("AppkwKey", 2), PCkwKey("PCkwKey", 3) ;
    private String name;
    private int type;

    EmuClass(String name, int type) {
        this.name = name;
        this.type = type;
    }

    //
    public static String getName(int type) {
        for (EmuClass r : EmuClass.values()) {
            if (r.getType() == type) {
                return r.getName();
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
