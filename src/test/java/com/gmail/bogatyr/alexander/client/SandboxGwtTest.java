package com.gmail.bogatyr.alexander.client;

import com.google.gwt.junit.client.GWTTestCase;

public class SandboxGwtTest extends GWTTestCase {
    @Override
    public String getModuleName() {
        return "com.gmail.bogatyr.alexander.TelephoneBook";
    }

    public void testSandbox() {
        assertTrue(true);
    }
}
