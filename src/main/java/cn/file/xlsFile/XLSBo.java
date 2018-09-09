package cn.file.xlsFile;

import com.alibaba.fastjson.JSON;
import jxl.write.Label;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class XLSBo {
    private int column;
    private List<Map<String, List<String>>> mapDATA = new ArrayList<Map<String, List<String>>>();
    private List<Label> labelList = new ArrayList<Label>();
    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public List<Map<String, List<String>>> getMapDATA() {
        return mapDATA;
    }

    public void setMapDATA(List<Map<String, List<String>>> mapDATA) {
        this.mapDATA = mapDATA;
    }

    public List<Label> getLabelList() {
        return labelList;
    }

    public void setLabelList(List<Label> labelList) {
        this.labelList = labelList;
    }

    @Override
    public String toString() {
        return "column:" +
                column +
                ", mapDATA:" +
                JSON.toJSONString(this.mapDATA);
    }
}
