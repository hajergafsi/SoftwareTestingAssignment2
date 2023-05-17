/**
*
* @author Hajer Gafsi hajer.gafsi@ogr.sakarya.edu.tr
* @since 16/05/2021
* <p>
* 	This class is the main class, it allows the user to run test by category with the help of a menu
* </p>
*/

package webTester;

import java.util.Scanner;

import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.*;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.*;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.openqa.selenium.WebDriver;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import com.github.javafaker.Faker;

import junit.framework.Assert;

import static org.junit.Assert.*;

public class Program {
	public static void main(String[] args) throws InterruptedException {
	    Scanner scanner = new Scanner(System.in);
	    int choice;
	    do {
	      System.out.println("Choose a category:");
	      System.out.println("1. Auth Tests Category");
	      System.out.println("2. Cart Tests Category");
	      System.out.println("3. Filtering Tests Category");
	      System.out.println("4. Sorting Tests Category");
	      System.out.println("5. Navigator Tests Category");
	      System.out.println("6. Payment Tests Category");
	      System.out.println("7. Products Tests Category");
	      System.out.println("8. Update User Tests Category");
	      System.out.println("9. Website Tester class Tests Category");
	      System.out.println("10. Exit");

	      choice = scanner.nextInt();

	      switch (choice) {
	        case 1:
	          runTests(AuthServiceTest.class);
	          break;
	        case 2:
	          runTests(CartTests.class);
	          break;
	        case 3:
	        	runTests(FilteringTests.class);
		        break;
		    case 4:
		    	runTests(SortingTests.class);
		        break;
		    case 5:
		    	runTests(NavigatorTests.class);
		    	break;
		    case 6:
		    	runTests(PaymentTests.class);
		    	break;  
		    case 7:
		    	runTests(ProductTests.class);
		    	break;
		    case 8:
		    	runTests(UpdateUserTests.class);
		    	break;
		    case 9:
		    	runTests(WebsiteTesterTests.class);
		    	break;		    	
	        case 10:
	          System.out.println("Exiting...");
	          break;
	        default:
	          System.out.println("Invalid choice");
	      }
	    } while (choice != 10);
	}
	
	  private static void runTests(Class<?> category) {
	        Launcher launcher = LauncherFactory.create();

	        // Create a SummaryGeneratingListener to capture the results
	        SummaryGeneratingListener listener = new SummaryGeneratingListener();

	        // Create a LauncherDiscoveryRequest to specify which tests to run
	        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
	            .selectors(
	                // Select the tests you want to run
	                DiscoverySelectors.selectClass(category)
	            )
	            .build();

	        // Run the tests and capture the results
	        launcher.execute(request, listener);

	        // Print the test results summary
	        TestExecutionSummary summary = listener.getSummary();
	        
	        System.out.println("Tests run: " + summary.getTestsFoundCount());
	        System.out.println("Tests passed: " + summary.getTestsSucceededCount());
	        System.out.println("Tests failed: " + summary.getTestsFailedCount());
	        
	        
	        summary.getFailures().forEach(item -> {
	        	System.out.println("Failed Tests: ");
	        	System.out.println(item.getTestIdentifier().getDisplayName()+"   " + item.getException() );
	        });
		  }
}

