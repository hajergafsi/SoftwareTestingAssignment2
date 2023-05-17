/**
*
* @author Hajer Gafsi hajer.gafsi@ogr.sakarya.edu.tr
* @since 16/05/2021
* <p>
* 	Product Service class test cases Suite
* </p>
*/

package webTester;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ProductServiceTest.class})
@IncludeTags("ProductTests")
public class ProductTests {

}
