package com.plucial.magazine.service.db;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;

import com.plucial.mulcms.sales.service.db.StatisticsService;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class StatisticsServiceTest extends AppEngineTestCase {

    private StatisticsService service = new StatisticsService();

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));
    }
}
