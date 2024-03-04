package guo.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Header {
    private static final long serialVersionUID = -1567595368978498076L;

    private String username;// 登录名

    private String password;// 密码

    private String userid;// 用户ID

    private String timestamp;// 时间戳

    private long  id ;//id
}
