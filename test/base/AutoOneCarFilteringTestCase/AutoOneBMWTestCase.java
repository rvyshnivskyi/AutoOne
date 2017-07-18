package base.AutoOneCarFilteringTestCase;

import base.BaseTest;
import com.vyshnivskyi.autoOne.helpers.AutoOneCarFilterTestHelper;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AutoOneBMWTestCase extends BaseTest {

	@Test
	@Parameters({"carFilter"})
	public void testSearchResultAddresses(@Optional("BMW") String carFilter) {
		new AutoOneCarFilterTestHelper(carFilter)
				.openOurCarsPageAndCheckCarFilter()
				.verifyExpectedFilterSelected()
				.verifyAllCarsAreFiltered()
				.verifyAllCarsHaveImages()
				.verifyAllCarsInformationIsComplete();
	}
}
