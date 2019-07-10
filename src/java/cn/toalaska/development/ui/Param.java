package cn.toalaska.development.ui;

import lombok.AllArgsConstructor;
import lombok.Data;

//"属性(中文)", "属性(中文)", "类型", "是否必须", "说明"
@Data
@AllArgsConstructor
public class Param {
    private String name;
    private String field;
    private String type;
    private Boolean isNotNull;
    private String description;




}
