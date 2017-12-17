package rnd.com.technodhaka.android.myapplication.connect.agranibankapp.Activities.Map;

/**
 * Created by TD-Android on 12/17/2017.
 */
public class BankLocationDM {
    private String palaceName;
    private double lat;
    private double log;

    public BankLocationDM() {
    }

    public BankLocationDM(String palaceName) {
        this.palaceName = palaceName;
    }

    public BankLocationDM(String palaceName, double lat, double log) {
        this.palaceName = palaceName;
        this.lat = lat;
        this.log = log;
    }

    public String getPalaceName() {
        return palaceName;
    }

    public void setPalaceName(String palaceName) {
        this.palaceName = palaceName;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLog() {
        return log;
    }

    public void setLog(double log) {
        this.log = log;
    }
}
