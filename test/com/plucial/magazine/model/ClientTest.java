package com.plucial.magazine.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;

import com.plucial.mulcms.sales.model.Client;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ClientTest extends AppEngineTestCase {

    private Client model = new Client();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
