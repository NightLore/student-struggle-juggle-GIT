package test;

import javafx.stage.Stage;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import logic.SceneManager;
import logic.ScreenType;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
    	Stage stage = new Stage();
    	SceneManager manager = new SceneManager(stage, 0, 0);
    	manager.switchTo(ScreenType.MAINMENU);
        assertTrue(true);
    }
}
