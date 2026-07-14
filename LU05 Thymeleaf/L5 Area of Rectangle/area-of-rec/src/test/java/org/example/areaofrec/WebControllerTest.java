package org.example.areaofrec;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebControllerTest {

    @LocalServerPort
    private int port;

    private static Playwright playwright;
    private static Browser browser;
    private BrowserContext context;
    private Page page;

    private String getBaseUrl() {
        return "http://localhost:" + port;
    }

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    void closeContext() {
        if (context != null)
            context.close();
    }

    @Test
    @DisplayName("Should successfully calculate area and navigate back")
    void testSuccessfulAreaCalculation() {

        page.navigate(getBaseUrl() + "/");

        page.fill("#length", "5");
        page.fill("#width", "4");
        page.click("input[value='Calculate Area']");

        assertThat(page.locator("h1")).hasText("Rectangle Area Calculator");

        Locator areaSpan = page.locator("p >> span");
        assertThat(areaSpan).hasText("20.0");

        page.click("text=Go back to form");

        // Use the helper method for the URL assertion too
        assertThat(page).hasURL(getBaseUrl() + "/");
        assertThat(page.locator("#length")).hasValue("0.0");
    }

    @Test
    @DisplayName("Should trigger validation and render errors on invalid input")
    void testValidationFailure() {

        page.navigate(getBaseUrl() + "/");

        page.fill("#length", "-5");
        page.fill("#width", "");
        page.click("input[value='Calculate Area']");

        assertThat(page.locator("h1")).not().isVisible();

        Locator errorSpans = page.locator(".error");
        assertThat(errorSpans.first()).isVisible();
    }
}