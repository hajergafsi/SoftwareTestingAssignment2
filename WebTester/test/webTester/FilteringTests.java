/**
*
* @author Hajer Gafsi hajer.gafsi@ogr.sakarya.edu.tr
* @since 16/05/2021
* <p>
* 	FilteringAndSorting class' Filtering test cases suite
* </p>
*/

package webTester;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({FilteringAndSortingTest.class})
@IncludeTags("FilteringTests")
public class FilteringTests {

}
