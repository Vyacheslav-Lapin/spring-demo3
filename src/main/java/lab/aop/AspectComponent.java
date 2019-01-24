package lab.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.annotation.Retention;
import java.lang.annotation.Inherited;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Aspect
@Component
@Inherited
@Retention(RUNTIME)
public @interface AspectComponent {
}
