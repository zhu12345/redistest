package cn.vmsservice.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@JsonIgnoreProperties
public class Host {
    private String id;

    private String offerId;

    private String resVmId;

    private String vmName;

    private String vmGroup;

    private String vmUser;

    private String vmPassword;

    private String vmStatus;

    private String osStyle;

    private String accountId;

    private String userId;

    private String privateIp;

    private String orderId;

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date applyDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date inputDate;

    private String vlanId;

    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date statusChangeDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date dueDate;

    private String payPattern;

    private String zoneName;

    private String zoneId;

    private String platformAccountId;

    private Integer isPackaged;

    private String displayName;

    private String resTemplateId;

    private String tags;

    private String workOrderResourceId;

    private String description;

    private Integer cpuNum;

    private Float memSize;

    private Integer isPm;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId == null ? null : offerId.trim();
    }

    public String getResVmId() {
        return resVmId;
    }

    public void setResVmId(String resVmId) {
        this.resVmId = resVmId == null ? null : resVmId.trim();
    }

    public String getVmName() {
        return vmName;
    }

    public void setVmName(String vmName) {
        this.vmName = vmName == null ? null : vmName.trim();
    }

    public String getVmGroup() {
        return vmGroup;
    }

    public void setVmGroup(String vmGroup) {
        this.vmGroup = vmGroup == null ? null : vmGroup.trim();
    }

    public String getVmUser() {
        return vmUser;
    }

    public void setVmUser(String vmUser) {
        this.vmUser = vmUser == null ? null : vmUser.trim();
    }

    public String getVmPassword() {
        return vmPassword;
    }

    public void setVmPassword(String vmPassword) {
        this.vmPassword = vmPassword == null ? null : vmPassword.trim();
    }

    public String getVmStatus() {
        return vmStatus;
    }

    public void setVmStatus(String vmStatus) {
        this.vmStatus = vmStatus == null ? null : vmStatus.trim();
    }

    public String getOsStyle() {
        return osStyle;
    }

    public void setOsStyle(String osStyle) {
        this.osStyle = osStyle == null ? null : osStyle.trim();
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

    public String getPrivateIp() {
        return privateIp;
    }

    public void setPrivateIp(String privateIp) {
        this.privateIp = privateIp == null ? null : privateIp.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    public String getVlanId() {
        return vlanId;
    }

    public void setVlanId(String vlanId) {
        this.vlanId = vlanId == null ? null : vlanId.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getStatusChangeDate() {
        return statusChangeDate;
    }

    public void setStatusChangeDate(Date statusChangeDate) {
        this.statusChangeDate = statusChangeDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getPayPattern() {
        return payPattern;
    }

    public void setPayPattern(String payPattern) {
        this.payPattern = payPattern == null ? null : payPattern.trim();
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName == null ? null : zoneName.trim();
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId == null ? null : zoneId.trim();
    }

    public String getPlatformAccountId() {
        return platformAccountId;
    }

    public void setPlatformAccountId(String platformAccountId) {
        this.platformAccountId = platformAccountId == null ? null : platformAccountId.trim();
    }

    public Integer getIsPackaged() {
        return isPackaged;
    }

    public void setIsPackaged(Integer isPackaged) {
        this.isPackaged = isPackaged;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName == null ? null : displayName.trim();
    }

    public String getResTemplateId() {
        return resTemplateId;
    }

    public void setResTemplateId(String resTemplateId) {
        this.resTemplateId = resTemplateId == null ? null : resTemplateId.trim();
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    public String getWorkOrderResourceId() {
        return workOrderResourceId;
    }

    public void setWorkOrderResourceId(String workOrderResourceId) {
        this.workOrderResourceId = workOrderResourceId == null ? null : workOrderResourceId.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getCpuNum() {
        return cpuNum;
    }

    public void setCpuNum(Integer cpuNum) {
        this.cpuNum = cpuNum;
    }

    public Float getMemSize() {
        return memSize;
    }

    public void setMemSize(Float memSize) {
        this.memSize = memSize;
    }

    public Integer getIsPm() {
        return isPm;
    }

    public void setIsPm(Integer isPm) {
        this.isPm = isPm;
    }

	@Override
	public String toString() {
		return "Host [id=" + id + ", offerId=" + offerId + ", resVmId="
				+ resVmId + ", vmName=" + vmName + ", vmGroup=" + vmGroup
				+ ", vmUser=" + vmUser + ", vmPassword=" + vmPassword
				+ ", vmStatus=" + vmStatus + ", osStyle=" + osStyle
				+ ", accountId=" + accountId + ", userId=" + userId
				+ ", privateIp=" + privateIp + ", orderId=" + orderId
				+ ", applyDate=" + applyDate + ", inputDate=" + inputDate
				+ ", vlanId=" + vlanId + ", status=" + status
				+ ", statusChangeDate=" + statusChangeDate + ", dueDate="
				+ dueDate + ", payPattern=" + payPattern + ", zoneName="
				+ zoneName + ", zoneId=" + zoneId + ", platformAccountId="
				+ platformAccountId + ", isPackaged=" + isPackaged
				+ ", displayName=" + displayName + ", resTemplateId="
				+ resTemplateId + ", tags=" + tags + ", workOrderResourceId="
				+ workOrderResourceId + ", description=" + description
				+ ", cpuNum=" + cpuNum + ", memSize=" + memSize + ", isPm="
				+ isPm + "]";
	}
    
}