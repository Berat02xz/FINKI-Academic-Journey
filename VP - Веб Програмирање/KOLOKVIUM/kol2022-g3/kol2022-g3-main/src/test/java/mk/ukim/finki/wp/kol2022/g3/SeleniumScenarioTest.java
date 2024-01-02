package mk.ukim.finki.wp.kol2022.g3;

import com.fasterxml.jackson.core.JsonProcessingException;
import mk.ukim.finki.wp.exam.util.CodeExtractor;
import mk.ukim.finki.wp.exam.util.ExamAssert;
import mk.ukim.finki.wp.exam.util.SubmissionHelper;
import mk.ukim.finki.wp.kol2022.g3.model.ForumUser;
import mk.ukim.finki.wp.kol2022.g3.model.ForumUserType;
import mk.ukim.finki.wp.kol2022.g3.model.Interest;
import mk.ukim.finki.wp.kol2022.g3.selenium.AbstractPage;
import mk.ukim.finki.wp.kol2022.g3.selenium.AddOrEditForm;
import mk.ukim.finki.wp.kol2022.g3.selenium.ItemsPage;
import mk.ukim.finki.wp.kol2022.g3.selenium.LoginPage;
import mk.ukim.finki.wp.kol2022.g3.service.ForumUserService;
import mk.ukim.finki.wp.kol2022.g3.service.InterestService;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumScenarioTest {

    static {
        SubmissionHelper.exam = "wp-kol-2022-g3";
        SubmissionHelper.index = "TODO";
    }

    @Autowired
    InterestService interestService;

    @Autowired
    ForumUserService service;

    @Order(1)
    @Test
    public void test_list_20pt() {
        SubmissionHelper.startTest("test-list-20");
        List<ForumUser> entities = this.service.listAll();
        int itemNum = entities.size();

        ExamAssert.assertNotEquals("Empty db", 0, itemNum);

        ItemsPage listPage = ItemsPage.to(this.driver);
        AbstractPage.assertRelativeUrl(this.driver, "/");
        listPage.assertItems(itemNum, itemNum * 2);

        SubmissionHelper.endTest();
    }

    @Order(2)
    @Test
    public void test_filter_5pt() {
        SubmissionHelper.startTest("test-filter-5");
        ItemsPage listPage = ItemsPage.to(this.driver);

        listPage.filter("", "");
        listPage.assertItems(10, 20);

        listPage.filter("15", "1");
        listPage.assertItems(3, 6);

        listPage.filter("", "1");
        listPage.assertItems(4, 8);

        listPage.filter("35", "");
        listPage.assertItems(4, 8);

        SubmissionHelper.endTest();
    }

    @Order(3)
    @Test
    public void test_filter_service_5pt() {
        SubmissionHelper.startTest("test-filter-service-5");

        ExamAssert.assertEquals("without filter", 10, this.service.filter(null, null).size());
        ExamAssert.assertEquals("by interest", 4, this.service.filter(1L, null).size());
        ExamAssert.assertEquals("by age", 4, this.service.filter(null, 35).size());
        ExamAssert.assertEquals("by all", 3, this.service.filter(1L, 15).size());

        SubmissionHelper.endTest();
    }

    @Order(4)
    @Test
    public void test_create_10pt() {
        SubmissionHelper.startTest("test-create-10");
        List<Interest> interests = this.interestService.listAll();
        List<ForumUser> entities = this.service.listAll();

        int itemNum = entities.size();
        ItemsPage listPage = null;

        try {
            LoginPage loginPage = LoginPage.openLogin(this.driver);
            listPage = LoginPage.doLogin(this.driver, loginPage, admin, adminPassword);
        } catch (Exception e) {
        }
        if (!LIST_URL.equals(this.driver.getCurrentUrl())) {
            System.err.println("Reloading items page");
            listPage = ItemsPage.to(this.driver);
        }
        listPage = AddOrEditForm.add(this.driver, listPage.getAddButton().isEmpty() ? null : listPage.getAddButton().get(0),
                "f1", "f2", "f3", ForumUserType.REGULAR.name(), interests.get(0).getId().toString(), LocalDate.now().minusYears(5).toString());
        AbstractPage.assertRelativeUrl(this.driver, LIST_URL);
        listPage.assertNoError();
        listPage.assertItems(itemNum + 1, 2 * itemNum + 1);

        SubmissionHelper.endTest();
    }

    @Order(5)
    @Test
    public void test_create_mvc_10pt() throws Exception {
        SubmissionHelper.startTest("test-create-mvc-10");
        List<Interest> interests = this.interestService.listAll();
        List<ForumUser> forumUsers = this.service.listAll();

        int itemNum = forumUsers.size();

        MockHttpServletRequestBuilder addRequest = MockMvcRequestBuilders
                .post(LIST_URL)
                .param("name", "testName")
                .param("email", "testDescription")
                .param("password", "test")
                .param("type", ForumUserType.REGULAR.name())
                .param("interestId", interests.get(0).getId().toString())
                .param("interestId", interests.get(1).getId().toString())
                .param("birthday", LocalDate.now().minusYears(5).toString());

        this.mockMvc.perform(addRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl(LIST_URL));

        forumUsers = this.service.listAll();
        ExamAssert.assertEquals("Number of items", itemNum + 1, forumUsers.size());

        addRequest = MockMvcRequestBuilders
                .get(ADD_URL);

        this.mockMvc.perform(addRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name(new ViewMatcher("form")));

        SubmissionHelper.endTest();
    }


    @Order(6)
    @Test
    public void test_delete_mvc_5pt() throws Exception {
        SubmissionHelper.startTest("test-delete-5");
        List<ForumUser> entities = this.service.listAll();
        int itemNum = entities.size();

        MockHttpServletRequestBuilder deleteRequest = MockMvcRequestBuilders
                .post("/users/" + entities.get(itemNum - 1).getId() + "/delete");

        this.mockMvc.perform(deleteRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl(LIST_URL));

        entities = this.service.listAll();
        ExamAssert.assertEquals("Number of items", itemNum - 1, entities.size());

        SubmissionHelper.endTest();
    }


    @Order(7)
    @Test
    public void test_delete_5pt() throws Exception {
        SubmissionHelper.startTest("test-delete-5");
        List<ForumUser> entities = this.service.listAll();
        int itemNum = entities.size();

        ItemsPage listPage = null;
        try {
            LoginPage loginPage = LoginPage.openLogin(this.driver);
            listPage = LoginPage.doLogin(this.driver, loginPage, admin, adminPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!LIST_URL.equals(this.driver.getCurrentUrl())) {
            System.err.println("Reloading items page");
            listPage = ItemsPage.to(this.driver);
        }

        listPage.getDeleteButtons().get(itemNum - 1).click();
        listPage.assertNoError();

        AbstractPage.assertRelativeUrl(this.driver, LIST_URL);
        listPage.assertItems(itemNum - 1, 2 * (itemNum - 1));

        SubmissionHelper.endTest();
    }

    @Order(8)
    @Test
    public void test_edit_10pt() {
        SubmissionHelper.startTest("test-edit-10");
        List<ForumUser> entities = this.service.listAll();
        int itemNum = entities.size();

        ItemsPage listPage = null;
        try {
            LoginPage loginPage = LoginPage.openLogin(this.driver);
            listPage = LoginPage.doLogin(this.driver, loginPage, admin, adminPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!LIST_URL.equals(this.driver.getCurrentUrl())) {
            System.err.println("Reloading items page");
            listPage = ItemsPage.to(this.driver);
        }

        listPage = AddOrEditForm.update(this.driver, listPage.getEditButtons().get(itemNum - 1),
                "f1u", "f2u", "f3u", ForumUserType.REGULAR.name(), "1,2,3", LocalDate.now().minusYears(5).toString());
        listPage.assertNoError();

        AbstractPage.assertRelativeUrl(this.driver, LIST_URL);
        if (listPage.assertItems(itemNum, 2 * itemNum + 1)) {
            ExamAssert.assertEquals("The updated entity name is not as expected.", "f1u",
                    listPage.getRows().get(itemNum - 1).findElements(By.tagName("td")).get(0).getText().trim());
        }

        SubmissionHelper.endTest();
    }

    @Order(9)
    @Test
    public void test_edit_mvc_10pt() throws Exception {
        SubmissionHelper.startTest("test-edit-mvc-10");
        List<Interest> interests = this.interestService.listAll();
        List<ForumUser> entities = this.service.listAll();

        int itemNum = entities.size();

        MockHttpServletRequestBuilder editRequest = MockMvcRequestBuilders
                .post(LIST_URL + "/" + entities.get(itemNum - 1).getId())
                .param("name", "testName")
                .param("email", "testDescription")
                .param("password", "test")
                .param("type", ForumUserType.REGULAR.name())
                .param("interestId", interests.get(0).getId().toString())
                .param("interestId", interests.get(1).getId().toString())
                .param("interestId", interests.get(2).getId().toString())
                .param("birthday", LocalDate.now().minusYears(5).toString());

        this.mockMvc.perform(editRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl(LIST_URL));

        entities = this.service.listAll();
        ExamAssert.assertEquals("Number of items", itemNum, entities.size());
        ExamAssert.assertEquals("The updated entity name is not as expected.", "testName", entities.get(itemNum - 1).getName());
        ExamAssert.assertEquals("The updated entity interest number is not as expected.", 3, entities.get(itemNum - 1).getInterests().size());

        editRequest = MockMvcRequestBuilders
                .get(LIST_URL + "/" + entities.get(itemNum - 1).getId() + "/edit");

        this.mockMvc.perform(editRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name(new ViewMatcher("form")));

        SubmissionHelper.endTest();
    }


    @Order(10)
    @Test
    public void test_security_urls_10pt() {
        SubmissionHelper.startTest("test-security-urls-10");
        List<ForumUser> entities = this.service.listAll();
        String editUrl = "/users/" + entities.get(0).getId() + "/edit";

        ItemsPage.to(this.driver);
        AbstractPage.assertRelativeUrl(this.driver, "/");

        AbstractPage.get(this.driver, LIST_URL);
        AbstractPage.assertRelativeUrl(this.driver, LOGIN_URL);
        AbstractPage.get(this.driver, ADD_URL);
        AbstractPage.assertRelativeUrl(this.driver, LOGIN_URL);
        AbstractPage.get(this.driver, editUrl);
        AbstractPage.assertRelativeUrl(this.driver, LOGIN_URL);
        AbstractPage.get(this.driver, "/random");
        AbstractPage.assertRelativeUrl(this.driver, LOGIN_URL);

        LoginPage loginPage = LoginPage.openLogin(this.driver);
        LoginPage.doLogin(this.driver, loginPage, admin, adminPassword);
        AbstractPage.assertRelativeUrl(this.driver, LIST_URL);

        AbstractPage.get(this.driver, LIST_URL);
        AbstractPage.assertRelativeUrl(this.driver, LIST_URL);

        AbstractPage.get(this.driver, ADD_URL);
        AbstractPage.assertRelativeUrl(this.driver, ADD_URL);

        AbstractPage.get(this.driver, editUrl);
        AbstractPage.assertRelativeUrl(this.driver, editUrl);

        LoginPage.logout(this.driver);
        AbstractPage.assertRelativeUrl(this.driver, "/");

        loginPage = LoginPage.openLogin(this.driver);
        LoginPage.doLogin(this.driver, loginPage, "invalid", "invalid");
        AbstractPage.assertRelativeUrl(this.driver, LOGIN_URL + "?error=BadCredentials");

        SubmissionHelper.endTest();
    }

    @Order(11)
    @Test
    public void test_security_buttons_10pt() {
        SubmissionHelper.startTest("test-security-buttons-10");
        List<ForumUser> entities = this.service.listAll();
        int itemNum = entities.size();

        LoginPage.logout(this.driver);

        ItemsPage listPage = ItemsPage.to(this.driver);
        AbstractPage.assertRelativeUrl(this.driver, "/");
        listPage.assertButtons(0, 0, 0);

        LoginPage loginPage1 = LoginPage.openLogin(this.driver);
        listPage = LoginPage.doLogin(this.driver, loginPage1, admin, adminPassword);
        listPage.assertButtons(itemNum, itemNum, 1);
        LoginPage.logout(this.driver);

        LoginPage loginPage2 = LoginPage.openLogin(this.driver);
        listPage = LoginPage.doLogin(this.driver, loginPage2, user, userPassword);
        listPage.assertButtons(0, 0, 0);
        LoginPage.logout(this.driver);
        SubmissionHelper.endTest();
    }

    private HtmlUnitDriver driver;
    private MockMvc mockMvc;

    private static String admin = "user.1@wp.finki.ukim.mk";
    private static String adminPassword = "forumUser1";
    private static String user = "user.2@wp.finki.ukim.mk";
    private static String userPassword = "forumUser2";

    @BeforeEach
    private void setup(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        this.driver = new HtmlUnitDriver(true);
    }

    @AfterEach
    public void destroy() {
        if (this.driver != null) {
            this.driver.close();
        }
    }

    @AfterAll
    public static void finalizeAndSubmit() throws JsonProcessingException {
        CodeExtractor.submitSourcesAndLogs();
    }

    public static final String LIST_URL = "/users";
    public static final String ADD_URL = "/users/add";
    public static final String LOGIN_URL = "/login";

    static class ViewMatcher implements Matcher<String> {

        final String baseName;

        ViewMatcher(String baseName) {
            this.baseName = baseName;
        }

        @Override
        public boolean matches(Object o) {
            if (o instanceof String) {
                String s = (String) o;
                return s.startsWith(baseName);
            }
            return false;
        }

        @Override
        public void describeMismatch(Object o, Description description) {
        }

        @Override
        public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {
        }

        @Override
        public void describeTo(Description description) {
        }
    }
}