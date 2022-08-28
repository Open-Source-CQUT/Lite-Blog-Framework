package com.lite.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("info_sys_controller")
@NoArgsConstructor
public class SystemController extends SystemEntity {


    /**
     * 包名
     */
    private String packageName;

    /**
     * 是否启用
     */
    private Boolean enable;

    private Integer permissionId;

    public SystemController(String url, String simpleName, String packageName,String fullName,Integer permissionId) {
        super(url, simpleName, fullName);
        this.enable = true;
        this.packageName = packageName;
        this.permissionId = permissionId;
    }
}
