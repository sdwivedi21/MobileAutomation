package appiumFrameworkUtilities;

import org.testng.annotations.DataProvider;

public class TestData {
	
	@DataProvider(name = "InputData")
	public Object[][] getDataForEditField() {
		// a multi-dimesnsional array object
		Object[][] obj = new Object[][] { { "hello" } , { "PikaPika" }};
		return obj;

	}

}
