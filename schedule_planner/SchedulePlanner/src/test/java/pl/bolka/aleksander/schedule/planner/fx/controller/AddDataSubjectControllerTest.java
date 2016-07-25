package pl.bolka.aleksander.schedule.planner.fx.controller;

import org.codehaus.groovy.runtime.ScriptReference;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.bolka.aleksander.schedule.planner.config.AppConfig;
import pl.bolka.aleksander.schedule.planner.config.ScreensConfig;

/**
 * Created by abolka on 11.07.16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class AddDataSubjectControllerTest {

    @Autowired
    ScreensConfig screensConfig;

    private AddDataSubjectController addDataSubjectController;

    @Before
    public void initialize() {
        addDataSubjectController = screensConfig.getAddDataSubjectController();
    }

    @Test
    public void getPath(){
        String expected = "/pl/bolka/aleksander/schedule/planner/fx/fxml/AddDataSubject.fxml";
        String path = addDataSubjectController.getPath();
        Assert.assertEquals(expected,path);
    }

    @Test
    public void givenNoSubjectWhenAddNewSubjectThenShowAllTypes(){

    }

}

