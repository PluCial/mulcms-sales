package com.plucial.mulcms.sales.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class MailTest extends AppEngineTestCase {

    private MailTemplate model = new MailTemplate();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
