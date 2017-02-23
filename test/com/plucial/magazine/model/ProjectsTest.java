package com.plucial.magazine.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;

import com.plucial.mulcms.sales.model.Project;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ProjectsTest extends AppEngineTestCase {

    private Project model = new Project();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
