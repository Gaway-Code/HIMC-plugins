package pl.himc.bungee.configuration;


import pl.himc.bungee.configuration.parser.ConfigValueType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ConfigurationSource {
    String path();
    String comment() default "";
    ConfigValueType valueType() default ConfigValueType.DEFAULT;
}
