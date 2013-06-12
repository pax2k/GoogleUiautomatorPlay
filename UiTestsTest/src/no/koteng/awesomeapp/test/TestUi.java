package no.koteng.awesomeapp.test;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class TestUi extends UiAutomatorTestCase {

	public void testUi() throws UiObjectNotFoundException {
		// Initial stuff
		startFromScratch();
		findAwesomeAppAndStartIt();

		// Related to MainActivity
		insertUserDataInFirstActivity();

		// Related to FlagActivity
		selectFlagFromSecondActivity();

		// Related to SummaryActivity
		checkSummaryActivity();
	}

	private void checkSummaryActivity() {
		sys("At next activity!");
		sys("Current activity: " + getUiDevice().getCurrentActivityName());

		UiObject header = new UiObject(new UiSelector().text("Summary"));
		UiObject userName = new UiObject(new UiSelector().text("KnowItTester"));
		UiObject realName = new UiObject(
				new UiSelector().text("Raymond Koteng"));
		UiObject email = new UiObject(new UiSelector().text("rak@knowit.no"));
		UiObject selectedFlag = new UiObject(
				new UiSelector().description("Selected image"));

		assertTrue(header.exists());
		assertTrue(userName.exists());
		assertTrue(realName.exists());
		assertTrue(email.exists());
		assertTrue(selectedFlag.exists());
	}

	private void selectFlagFromSecondActivity()
			throws UiObjectNotFoundException {
		sys("At next activity!");
		sys("Current activity: " + getUiDevice().getCurrentActivityName());

		UiScrollable flagGridView = new UiScrollable(
				new UiSelector().scrollable(true));
		flagGridView.waitForExists(10000);
		flagGridView.scrollToEnd(88, 5);

		sys("Found a list of flag. Current size: "
				+ flagGridView.getChildCount());

		assertTrue("Can not find grid view", flagGridView.exists());
		assertFalse("Grid view is empty.", flagGridView.getChildCount() == 0);

		UiObject flagItem = flagGridView.getChild(new UiSelector()
				.description("Vietnam"));

		sys("Selecting flag for 'Vietnam'");
		assertTrue("Flag not found!", flagItem.exists());
		flagItem.clickAndWaitForNewWindow();
	}

	private void insertUserDataInFirstActivity()
			throws UiObjectNotFoundException {
		sys("Inside application!");
		sys("Current activity: " + getUiDevice().getCurrentActivityName());

		UiObject usernameInput = new UiObject(new UiSelector().text("Username"));
		UiObject realNameInput = new UiObject(
				new UiSelector().text("Real name"));
		UiObject emailInput = new UiObject(new UiSelector().text("Email"));
		UiObject okButton = new UiObject(new UiSelector().text("OK"));

		assertTrue("Username does not exists", usernameInput.exists());
		assertTrue("Real name does not exists", realNameInput.exists());
		assertTrue("Email does not exists", emailInput.exists());
		assertTrue("OK button does not exists", okButton.exists());

		sys("Write username, real name and email");
		usernameInput.setText("KnowItTester");
		realNameInput.setText("Raymond Koteng");
		emailInput.setText("rak@knowit.no");
		sys("Click the 'OK' button and wait for new window.");
		okButton.clickAndWaitForNewWindow();
	}

	private void findAwesomeAppAndStartIt() throws UiObjectNotFoundException {
		sys("Find 'Awesome app' from 'App' list.");
		UiScrollable appViews = new UiScrollable(
				new UiSelector().scrollable(true));
		appViews.setAsHorizontalList();

		UiObject awesomeApp = appViews.getChildByText(new UiSelector()
				.className(android.widget.TextView.class.getName()),
				"Awesome App");

		assertTrue("Can not find awesomeApp", awesomeApp.exists());

		sys("Awesome found, start it!");
		awesomeApp.clickAndWaitForNewWindow();

		UiObject awesomeAppValidation = new UiObject(
				new UiSelector().packageName("no.koteng.awesomeapp"));

		assertTrue("Unable to detect the Awesome App",
				awesomeAppValidation.exists());
	}

	private void startFromScratch() throws UiObjectNotFoundException {
		sys("Pressing home button.");
		getUiDevice().pressHome();

		sys("Clicking on the apps button");
		UiObject allAppsButton = new UiObject(
				new UiSelector().description("Apps"));
		allAppsButton.clickAndWaitForNewWindow();

		sys("App list loaded, selecting 'Apps' tab.");
		UiObject appsTab = new UiObject(new UiSelector().text("Apps"));
		appsTab.click();
	}

	private void sys(String output) {
		System.out.println("----> " + output);
	}

}
