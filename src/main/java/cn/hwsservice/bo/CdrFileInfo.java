package cn.hwsservice.bo;

import java.util.Date;

public class CdrFileInfo {
    private Integer id;

    private String zipFilename;

    private Double fileSize;

    private Date accessDate;

    private String resourceTypes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getZipFilename() {
        return zipFilename;
    }

    public void setZipFilename(String zipFilename) {
        this.zipFilename = zipFilename == null ? null : zipFilename.trim();
    }

    public Double getFileSize() {
        return fileSize;
    }

    public void setFileSize(Double fileSize) {
        this.fileSize = fileSize;
    }

    public Date getAccessDate() {
        return accessDate;
    }

    public void setAccessDate(Date accessDate) {
        this.accessDate = accessDate;
    }

    public String getResourceTypes() {
        return resourceTypes;
    }

    public void setResourceTypes(String resourceTypes) {
        this.resourceTypes = resourceTypes == null ? null : resourceTypes.trim();
    }
}