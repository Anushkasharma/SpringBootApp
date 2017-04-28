package com.anushka.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by rxd2095 on 4/14/17.
 */
@Configuration
@ComponentScan
@Profile({"default", "test"})
public class AnushkaConfiguration extends AbstractAnushkaDataSetup {
}
