package com.clouddeer.vo;

/**
 * Created by Maos on 2017/8/21.
 */
@SuppressWarnings("all")
public class WeChatVo {

    private String openid;

    private String nickName;

    private String headimgurl;

    private Byte thirdType;

    private String deviceNumber;

    private Integer memberId;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public Byte getThirdType() {
        return thirdType;
    }

    public void setThirdType(Byte thirdType) {
        this.thirdType = thirdType;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }
}
