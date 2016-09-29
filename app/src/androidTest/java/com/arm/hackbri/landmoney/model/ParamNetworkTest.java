package com.arm.hackbri.landmoney.model;

import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

/**
 * ParamNetworkTest
 * Created by anggaprasetiyo on 9/29/16.
 */
public class ParamNetworkTest extends TestCase {
    private ParamNetwork.Builder builder;

    public void setUp() throws Exception {
        super.setUp();
        builder = new ParamNetwork.Builder();
    }

    public void tearDown() throws Exception {

    }

    public void testGetParamMap() throws Exception {
        Map<String, String> result = new HashMap<>();
        result.put("angga", "ganteng");
      //  assertEquals(result, builder.put("angga", "ganteng").build().getParamMap());
        assertNull(builder.put("tot","jing").build().getParamMap().get("angga"));
    }

}