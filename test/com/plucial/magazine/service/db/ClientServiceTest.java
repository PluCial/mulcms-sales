package com.plucial.magazine.service.db;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;

import com.plucial.mulcms.sales.service.db.ClientService;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ClientServiceTest extends AppEngineTestCase {

    private ClientService service = new ClientService();

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));
    }
}
