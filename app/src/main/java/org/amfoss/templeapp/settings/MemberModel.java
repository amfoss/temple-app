package org.amfoss.templeapp.settings;

/**
* @author by Chromicle (ajayprabhakar369@gmail.com)
* @since 4/4/2020
*/
public class MemberModel {

    String memberEmail;

    public MemberModel() {}

    public MemberModel(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }
}
