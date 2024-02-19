package showcase;

import org.springframework.stereotype.Component;
import timer.Timer;

@Component
public class OnMethod {

    @Timer
    public void test(){
        int j = 0;
        for (int i = 0; i < 10_000_000; i++) {
            j += i % 5;
        }
        System.out.println(j);
    }
}
