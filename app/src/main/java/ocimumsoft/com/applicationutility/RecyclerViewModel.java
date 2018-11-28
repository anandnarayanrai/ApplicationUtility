package ocimumsoft.com.applicationutility;

public class RecyclerViewModel {
    private String dName;
    private String dDescriptions;
    private int dImage;

    public RecyclerViewModel(String dName, String dDescriptions, int dImage) {
        this.dName = dName;
        this.dDescriptions = dDescriptions;
        this.dImage = dImage;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getdDescriptions() {
        return dDescriptions;
    }

    public void setdDescriptions(String dDescriptions) {
        this.dDescriptions = dDescriptions;
    }

    public int getdImage() {
        return dImage;
    }

    public void setdImage(int dImage) {
        this.dImage = dImage;
    }
}
