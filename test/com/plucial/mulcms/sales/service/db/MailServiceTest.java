package com.plucial.mulcms.sales.service.db;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class MailServiceTest extends AppEngineTestCase {

    private MailTemplateService service = new MailTemplateService();

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));
    }
}
