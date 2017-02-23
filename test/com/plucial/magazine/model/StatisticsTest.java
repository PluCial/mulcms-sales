package com.plucial.magazine.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;

import com.plucial.mulcms.sales.model.Statistics;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class StatisticsTest extends AppEngineTestCase {

    private Statistics model = new Statistics();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
