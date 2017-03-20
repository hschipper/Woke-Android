package woke.woke;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hana on 3/12/2017.
 */

public class Member {
    //member attributes
    @SerializedName("district")
    @Expose
    private String district;
    @SerializedName("member")
    @Expose
    private String member;
    @SerializedName("party")
    @Expose
    private String party;
    @SerializedName("served")
    @Expose
    private String served;
    @SerializedName("state")
    @Expose
    private String state;

    public Member(String member, String state) {
        this.member = member;
        this.state = state;
    }
    //Getters and Setters
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getMember() {
        return this.member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getServed() {
        return served;
    }

    public void setServed(String served) {
        this.served = served;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}