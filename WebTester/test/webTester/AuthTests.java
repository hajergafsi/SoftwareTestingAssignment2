package webTester;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;



@Suite
@SelectClasses({AuthServiceTest.class})
@IncludeTags("AuthTests")
public class AuthTests {

}
