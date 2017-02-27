package com.plucial.mulcms.sales.dao;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class MailDaoTest extends AppEngineTestCase {

    private MailTemplateDao dao = new MailTemplateDao();

    @Test
    public void test() throws Exception {
        assertThat(dao, is(notNullValue()));
    }
}
