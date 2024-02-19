package timer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class TimerAspect {

    private final TimerProperties properties;

    @Pointcut("@within(timer.Timer)")
    public void withinTimer(){}

    @Pointcut("@annotation(timer.Timer)")
    public void annotationTimer(){}

    @Around("withinTimer() || annotationTimer()")
    public Object time(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        Object result = joinPoint.proceed();
        long end = System.nanoTime();
        double time;
        String unitString;
        String units = properties.getUnits();
        double timeDiff = (end - start);
        if (units.equalsIgnoreCase("seconds")) {
            time = timeDiff / 1_000_000_000;
            unitString = "Seconds";
        } else if (units.equalsIgnoreCase("millis")) {
            time = timeDiff / 1_000_000;
            unitString = "Milliseconds";
        } else {
            time = 0;
            unitString = "Unknown unit:" + units;
        }
        log.info("{} - {} #{} {}", joinPoint.getTarget().getClass(), joinPoint.getSignature().getName(), time, unitString);
        return result;
    }
}
