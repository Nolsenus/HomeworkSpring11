package timer;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("timer")
public class TimerProperties {

    private boolean enabled = false;

    private String units = "seconds";

}
