package com.plucial.magazine.dao;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;

import com.plucial.mulcms.sales.dao.ProjectDao;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ProjectsDaoTest extends AppEngineTestCase {

    private ProjectDao dao = new ProjectDao();

    @Test
    public void test() throws Exception {
        assertThat(dao, is(notNullValue()));
    }
}
