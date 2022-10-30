package stackoverflow.global.config;

import com.p6spy.engine.spy.P6SpyOptions;
import org.springframework.context.annotation.Configuration;
import stackoverflow.global.p6spy.P6spySqlFormatConfiguration;

import javax.annotation.PostConstruct;

@Configuration
public class AppConfig {

    @PostConstruct
    public void setLogMessageFormat() {
        P6SpyOptions.getActiveInstance().setLogMessageFormat(P6spySqlFormatConfiguration.class.getName());
    }

}
