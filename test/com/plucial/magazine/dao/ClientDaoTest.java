package com.plucial.magazine.dao;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;

import com.plucial.mulcms.sales.dao.ClientDao;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ClientDaoTest extends AppEngineTestCase {

    private ClientDao dao = new ClientDao();

    @Test
    public void test() throws Exception {
        assertThat(dao, is(notNullValue()));
    }
}
