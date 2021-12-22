package org.wordpress.android.e2e.pages;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;

import org.wordpress.android.R;
import org.wordpress.android.e2e.flows.LoginFlow;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.wordpress.android.support.WPSupportUtils.*;

import static org.hamcrest.Matchers.allOf;


public class HelpAndSupportScreen {
    static ViewInteraction contactUsButton = onView(withId(R.id.contact_us_button));
    static ViewInteraction FAQButton = onView(withId(R.id.faq_button));
    static ViewInteraction myTicketsbutton = onView(withId(R.id.my_tickets_button));
    static ViewInteraction applicationLogButton = onView(withId(R.id.application_log_button));
    static ViewInteraction applicationVersionText = onView(withId(R.id.applicationVersion));
    static ViewInteraction emailAddressText = onView(withId(R.id.contactEmailAddress));


    public HelpAndSupportScreen assertHelpAndSupportScreenLoaded() {
        contactUsButton.check(matches(isCompletelyDisplayed()));
        FAQButton.check(matches(isCompletelyDisplayed()));
        myTicketsbutton.check(matches(isCompletelyDisplayed()));
        applicationLogButton.check(matches(isCompletelyDisplayed()));
        applicationVersionText.check(matches(isCompletelyDisplayed()));
        emailAddressText.check(matches(isCompletelyDisplayed()));
        return this;
    }

    public HelpAndSupportScreen setEmailIfNeeded(String emailAddress) {
        ViewInteraction emailNotSet = onView(allOf(withId(R.id.contactEmailAddress), withText("Not set")));

        if (isElementCompletelyDisplayed(emailNotSet)) {
            emailNotSet.perform(ViewActions.click());
            populateTextField(R.id.support_identity_input_dialog_email_edit_text, emailAddress);

            onView(withText("OK")).perform(ViewActions.click());
        }

        return this;
    }

    public ContactSupportScreen openContactUs() {
        clickOn(contactUsButton);
        return new ContactSupportScreen();
    }

    public LoginFlow navigateBack() {
        Espresso.pressBack();
        return new LoginFlow();
    }
}


