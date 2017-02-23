package com.plucial.magazine.dao;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;

import com.plucial.mulcms.sales.dao.StatisticsDao;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class StatisticsDaoTest extends AppEngineTestCase {

    private StatisticsDao dao = new StatisticsDao();

    @Test
    public void test() throws Exception {
        assertThat(dao, is(notNullValue()));
    }
}
