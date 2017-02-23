package com.plucial.magazine.controller.company;

import org.slim3.tester.ControllerTestCase;
import org.junit.Test;

import com.plucial.mulcms.sales.controller.company.client.AddController;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class AddControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.start("/company/add");
        AddController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is("/company/add.jsp"));
    }
}
