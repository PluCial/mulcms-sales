package com.plucial.magazine.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;

import com.plucial.mulcms.sales.model.Company;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class CompanyTest extends AppEngineTestCase {

    private Company model = new Company();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
