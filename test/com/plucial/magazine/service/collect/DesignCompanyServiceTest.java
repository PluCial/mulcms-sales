package com.plucial.magazine.service.collect;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;

import com.plucial.mulcms.sales.service.collect.PartnerCollectService;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class DesignCompanyServiceTest extends AppEngineTestCase {

    private PartnerCollectService service = new PartnerCollectService();

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));
    }
}
