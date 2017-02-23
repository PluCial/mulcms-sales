package com.plucial.magazine.dao;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;

import com.plucial.mulcms.sales.dao.PartnerDao;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class PartnerDaoTest extends AppEngineTestCase {

    private PartnerDao dao = new PartnerDao();

    @Test
    public void test() throws Exception {
        assertThat(dao, is(notNullValue()));
    }
}
