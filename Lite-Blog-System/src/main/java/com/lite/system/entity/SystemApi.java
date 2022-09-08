package com.lite.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lite.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@TableName("info_sys_api")
public class SystemApi extends SystemEntity{

    /**
     * 请求类型 GET POST 等等
     */
    private String method;

    /**
     * 是否启用
     */
    private Boolean enable;

    /**
     * 可用权限
     */
    private Integer permissionId;

    public SystemApi(String simpleName,String fullName,String url, String method,Integer permissionId) {
        super(url, simpleName, fullName);
        this.method = method;
        this.enable = true;
        this.permissionId = permissionId;
    }

    public SystemApi(String simpleName,String fullName,String url, String method) {
        super(url, simpleName, fullName);
        this.method = method;
        this.enable = true;
        this.permissionId = PermissionId.USER.val();
    }

    public String str(){
        return this + ":" + super.toString();
    }

}
