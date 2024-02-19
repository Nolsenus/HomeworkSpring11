package showcase;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import timer.Timer;

@Component
@RequiredArgsConstructor
public class TimerShowcase {


    private final OnClass onClass;
    private final OnMethod onMethod;

    @EventListener(ApplicationReadyEvent.class)
    public void showcase() {
        onClass.test();
        onMethod.test();
    }

}
