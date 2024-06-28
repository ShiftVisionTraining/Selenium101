package com.shiftvision.spree.framework.kw;

import com.shiftvision.spree.framework.pom.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class KeywordScriptBase {
    protected static DriverFactory driverFactory;
    protected static KeywordFactory keywordFactory;
    @BeforeEach
    public void setUp() {
        driverFactory = DriverFactory.getInstance();
        keywordFactory = KeywordFactory.getInstance();
    }

    @AfterEach
    public void tearDown(){
        driverFactory.quit();
    }



}
