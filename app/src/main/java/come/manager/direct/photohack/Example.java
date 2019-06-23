package come.manager.direct.photohack;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {

    @SerializedName("url1")
    @Expose
    private String url1;
    @SerializedName("url2")
    @Expose
    private String url2;
    @SerializedName("url3")
    @Expose
    private String url3;
    @SerializedName("url4")
    @Expose
    private String url4;

    public String getUrl1() {
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
    }

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }

    public String getUrl3() {
        return url3;
    }

    public void setUrl3(String url3) {
        this.url3 = url3;
    }

    public String getUrl4() {
        return url4;
    }

    public void setUrl4(String url4) {
        this.url4 = url4;
    }

}