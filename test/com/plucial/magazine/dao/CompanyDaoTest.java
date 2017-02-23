package com.plucial.magazine.dao;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;

import com.plucial.mulcms.sales.dao.CompanyDao;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class CompanyDaoTest extends AppEngineTestCase {

    private CompanyDao dao = new CompanyDao();

    @Test
    public void test() throws Exception {
        assertThat(dao, is(notNullValue()));
    }
}
