package woke.woke;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hana on 3/12/2017.
 */

//Not using this class yet.
public class Bill {
    //Bill attributes
    @SerializedName("billTitle")
    @Expose
    private String billTitle;
    @SerializedName("billHeading")
    @Expose
    private String billHeading;
    @SerializedName("committees")
    @Expose
    private String committees;
    @SerializedName("latestAction")
    @Expose
    private String latestAction;
    @SerializedName("sponser")
    @Expose
    private String sponser;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("billText")
    @Expose
    private String billText;

    //Getters and Setters
    public String getTitle() {
        return billTitle;
    }

    public void setTitle(String title) {
        this.billTitle  = title;
    }

    public String getHeading() {
        return billHeading;
    }

    public void setHeading(String heading) {
        this.billHeading = heading;
    }

    public String getCommittees() {
        return committees;
    }

    public void setCommittees(String committees) {
        this.committees = committees;
    }

    public String getLatestAction() {
        return latestAction;
    }

    public void setLatestAction(String action) {
        this.latestAction = action;
    }

    public String getSponser() {
        return sponser;
    }

    public void setSponser(String sponser) {
        this.sponser = sponser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getText() {
        return billText;
    }

    public void setText(String text) {
        this.billText = text;
    }


}