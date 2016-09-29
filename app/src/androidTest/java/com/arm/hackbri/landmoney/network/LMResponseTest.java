package com.arm.hackbri.landmoney.network;

import junit.framework.TestCase;

/**
 * LMResponseTest
 * Created by anggaprasetiyo on 9/29/16.
 */
public class LMResponseTest extends TestCase {


    String jsonDummyObjectData = "{\n" +
            "  \"status\": 1,\n" +
            "  \"data\": {\n" +
            "    \"angga\": \"keren\"\n" +
            "  },\n" +
            "  \"message\": \"\"\n" +
            "}";

    String jsonDummyArrayData = "{\n" +
            "  \"status\": 1,\n" +
            "  \"data\": [{},{}],\n" +
            "  \"message\": \"\"\n" +
            "}";

    public void setUp() throws Exception {
        super.setUp();

    }

    public void testFactory() throws Exception {
        assertEquals(2, LMResponse.factory(jsonDummyArrayData).getJsonData().getAsJsonArray().size());
        assertNotNull(LMResponse.factory(jsonDummyObjectData).getJsonData().getAsJsonObject());
    }

}