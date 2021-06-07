package home.zyp.starter.zypspringbootstarterautoconfigurer;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 朱雨鹏
 * @create 2021-06-07 11:15
 */
@ConfigurationProperties(prefix = "zyp.hello")
public class HelloProperties {
    private String prefix;
    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
