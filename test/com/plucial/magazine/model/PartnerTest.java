package com.plucial.magazine.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;

import com.plucial.mulcms.sales.model.Partner;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class PartnerTest extends AppEngineTestCase {

    private Partner model = new Partner();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
