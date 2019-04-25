package cn.hws.bo;

import java.util.Date;

public class ZoneConfig implements java.io.Serializable{

    private static final long serialVersionUID = 1822818790694831649L;

    private String id;
    /**
     * 华为节点ID
     */
    private String zoneId;
    /**
     * 华为节点名称
     */
    private String zoneName;
    /**
     * 华为regionID
     */
    private String regionId;
    /**
     * 资源池是否计费
     */
    private Integer isCharge;
    /**
     * 资源池计费的资源类型
     */
    private String chargeResourceTypes;
    /**
     * 是否蒙贵
     */
    private Integer zoneType;
    /**
     * 创建日期
     */
    private Date createDate;
    /**
     * 统计状态 0 统计中 1 统计完成
     */
    private Integer statisticStatus;
    /**
     * 是否启用
     */
    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getChargeResourceTypes() {
        return chargeResourceTypes;
    }

    public void setChargeResourceTypes(String chargeResourceTypes) {
        this.chargeResourceTypes = chargeResourceTypes;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getIsCharge() {
        return isCharge;
    }

    public void setIsCharge(Integer isCharge) {
        this.isCharge = isCharge;
    }

    public Integer getZoneType() {
        return zoneType;
    }

    public void setZoneType(Integer zoneType) {
        this.zoneType = zoneType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public Integer getStatisticStatus() {
        return statisticStatus;
    }

    public void setStatisticStatus(Integer statisticStatus) {
        this.statisticStatus = statisticStatus;
    }

}