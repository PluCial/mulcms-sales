package com.plucial.magazine.service.db;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;

import com.plucial.mulcms.sales.service.db.CompanyService;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class CompanyServiceTest extends AppEngineTestCase {

    private CompanyService service = new CompanyService();

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));
    }
}
