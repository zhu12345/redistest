package cn.hwsservice.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties
public class AccountMeta {
    private String id;

    private String accountId;

    private String userId;

    private String loginEmail;

    private String loginName;

    private String hwDomainId;

    private String hwUserId;

    private Integer postpaid;

    private Integer status;

    private Integer bundleStatus;

    private Integer createStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    private Integer isArrears;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date arrearsDate;

    private Integer orderSwitch;

    private Integer version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getLoginEmail() {
        return loginEmail;
    }

    public void setLoginEmail(String loginEmail) {
        this.loginEmail = loginEmail == null ? null : loginEmail.trim();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getHwDomainId() {
        return hwDomainId;
    }

    public void setHwDomainId(String hwDomainId) {
        this.hwDomainId = hwDomainId == null ? null : hwDomainId.trim();
    }

    public String getHwUserId() {
        return hwUserId;
    }

    public void setHwUserId(String hwUserId) {
        this.hwUserId = hwUserId == null ? null : hwUserId.trim();
    }

    public Integer getPostpaid() {
        return postpaid;
    }

    public void setPostpaid(Integer postpaid) {
        this.postpaid = postpaid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getBundleStatus() {
        return bundleStatus;
    }

    public void setBundleStatus(Integer bundleStatus) {
        this.bundleStatus = bundleStatus;
    }

    public Integer getCreateStatus() {
        return createStatus;
    }

    public void setCreateStatus(Integer createStatus) {
        this.createStatus = createStatus;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getIsArrears() {
        return isArrears;
    }

    public void setIsArrears(Integer isArrears) {
        this.isArrears = isArrears;
    }

    public Date getArrearsDate() {
        return arrearsDate;
    }

    public void setArrearsDate(Date arrearsDate) {
        this.arrearsDate = arrearsDate;
    }

    public Integer getOrderSwitch() {
        return orderSwitch;
    }

    public void setOrderSwitch(Integer orderSwitch) {
        this.orderSwitch = orderSwitch;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}