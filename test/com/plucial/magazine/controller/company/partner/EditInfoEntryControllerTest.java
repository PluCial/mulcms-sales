package com.plucial.magazine.controller.company.partner;

import org.slim3.tester.ControllerTestCase;
import org.junit.Test;

import com.plucial.mulcms.sales.controller.company.partner.EditInfoEntryController;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class EditInfoEntryControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.start("/company/partner/editInfoEntry");
        EditInfoEntryController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is(nullValue()));
    }
}
