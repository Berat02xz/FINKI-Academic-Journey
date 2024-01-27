package mk.ukim.finki.wp.kol2022.g1;

import com.fasterxml.jackson.core.JsonProcessingException;
import mk.ukim.finki.wp.exam.util.CodeExtractor;
import mk.ukim.finki.wp.exam.util.ExamAssert;
import mk.ukim.finki.wp.exam.util.SubmissionHelper;
import mk.ukim.finki.wp.kol2022.g1.model.Employee;
import mk.ukim.finki.wp.kol2022.g1.model.EmployeeType;
import mk.ukim.finki.wp.kol2022.g1.model.Skill;
import mk.ukim.finki.wp.kol2022.g1.selenium.AbstractPage;
import mk.ukim.finki.wp.kol2022.g1.selenium.AddOrEditForm;
import mk.ukim.finki.wp.kol2022.g1.selenium.ItemsPage;
import mk.ukim.finki.wp.kol2022.g1.selenium.LoginPage;
import mk.ukim.finki.wp.kol2022.g1.service.EmployeeService;
import mk.ukim.finki.wp.kol2022.g1.service.SkillService;
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
        SubmissionHelper.exam = "wp-kol-2024-g3";
        SubmissionHelper.index = "216130";
    }

    @Autowired
    SkillService skillService;

    @Autowired
    EmployeeService service;

    @Order(1)
    @Test
    public void test_list_20pt() {
        SubmissionHelper.startTest("test-list-20");
        List<Employee> entities = this.service.listAll();
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

        listPage.filter("1", "1");
        listPage.assertItems(3, 6);

        listPage.filter("", "1");
        listPage.assertItems(4, 8);

        listPage.filter("3", "");
        listPage.assertItems(4, 8);

        SubmissionHelper.endTest();
    }

    @Order(3)
    @Test
    public void test_filter_service_5pt() {
        SubmissionHelper.startTest("test-filter-service-5");

        ExamAssert.assertEquals("without filter", 10, this.service.filter(null, null).size());
        ExamAssert.assertEquals("by skill", 4, this.service.filter(1L, null).size());
        ExamAssert.assertEquals("by years of service", 4, this.service.filter(null, 3).size());
        ExamAssert.assertEquals("by all", 3, this.service.filter(1L, 1).size());

        SubmissionHelper.endTest();
    }

    @Order(4)
    @Test
    public void test_create_10pt() {
        SubmissionHelper.startTest("test-create-10");
        List<Skill> skills = this.skillService.listAll();
        List<Employee> entities = this.service.listAll();

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
                "f1", "f2", "f3", EmployeeType.CONSULTANT.name(), skills.get(0).getId().toString(), LocalDate.now().minusYears(5).toString());
        AbstractPage.assertRelativeUrl(this.driver, LIST_URL);
        listPage.assertNoError();
        listPage.assertItems(itemNum + 1, 2 * itemNum + 1);

        SubmissionHelper.endTest();
    }

    @Order(5)
    @Test
    public void test_create_mvc_10pt() throws Exception {
        SubmissionHelper.startTest("test-create-mvc-10");
        List<Skill> categories = this.skillService.listAll();
        List<Employee> employees = this.service.listAll();

        int itemNum = employees.size();

        MockHttpServletRequestBuilder addRequest = MockMvcRequestBuilders
                .post(LIST_URL)
                .param("name", "testName")
                .param("email", "testDescription")
                .param("password", "test")
                .param("type", EmployeeType.CONSULTANT.name())
                .param("skillId", categories.get(0).getId().toString())
                .param("skillId", categories.get(1).getId().toString())
                .param("employmentDate", LocalDate.now().minusYears(5).toString());

        this.mockMvc.perform(addRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl(LIST_URL));

        employees = this.service.listAll();
        ExamAssert.assertEquals("Number of items", itemNum + 1, employees.size());

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
        List<Employee> entities = this.service.listAll();
        int itemNum = entities.size();

        MockHttpServletRequestBuilder deleteRequest = MockMvcRequestBuilders
                .post("/employees/" + entities.get(itemNum - 1).getId() + "/delete");

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
        List<Employee> entities = this.service.listAll();
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
        List<Skill> skills = this.skillService.listAll();
        List<Employee> entities = this.service.listAll();

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
                "f1u", "f2u", "f3u", EmployeeType.CONSULTANT.name(), "1,2,3", LocalDate.now().minusYears(5).toString());
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
        List<Skill> categories = this.skillService.listAll();
        List<Employee> entities = this.service.listAll();

        int itemNum = entities.size();

        MockHttpServletRequestBuilder editRequest = MockMvcRequestBuilders
                .post(LIST_URL + "/" + entities.get(itemNum - 1).getId())
                .param("name", "testName")
                .param("email", "testDescription")
                .param("password", "test")
                .param("type", EmployeeType.CONSULTANT.name())
                .param("skillId", categories.get(0).getId().toString())
                .param("skillId", categories.get(1).getId().toString())
                .param("skillId", categories.get(2).getId().toString())
                .param("employmentDate", LocalDate.now().minusYears(5).toString());

        this.mockMvc.perform(editRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl(LIST_URL));

        entities = this.service.listAll();
        ExamAssert.assertEquals("Number of items", itemNum, entities.size());
        ExamAssert.assertEquals("The updated entity name is not as expected.", "testName", entities.get(itemNum - 1).getName());
        ExamAssert.assertEquals("The updated entity skill number is not as expected.", 3, entities.get(itemNum - 1).getSkills().size());

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
        List<Employee> entities = this.service.listAll();
        String editUrl = "/employees/" + entities.get(0).getId() + "/edit";

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
        List<Employee> entities = this.service.listAll();
        int itemNum = entities.size();

        LoginPage.logout(this.driver);

        ItemsPage listPage = ItemsPage.to(this.driver);
        AbstractPage.assertRelativeUrl(this.driver, "/");
        listPage.assertButtons(0, 0, 0, 0);

        LoginPage loginPage1 = LoginPage.openLogin(this.driver);
        listPage = LoginPage.doLogin(this.driver, loginPage1, admin, adminPassword);
        listPage.assertButtons(itemNum, itemNum, 1, 0);
        LoginPage.logout(this.driver);

        LoginPage loginPage2 = LoginPage.openLogin(this.driver);
        listPage = LoginPage.doLogin(this.driver, loginPage2, user, userPassword);
        listPage.assertButtons(0, 0, 0, itemNum);
        LoginPage.logout(this.driver);
        SubmissionHelper.endTest();
    }

    private HtmlUnitDriver driver;
    private MockMvc mockMvc;

    private static String admin = "employee.1@wp.finki.ukim.mk";
    private static String adminPassword = "emp1";
    private static String user = "employee.2@wp.finki.ukim.mk";
    private static String userPassword = "emp2";

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
    public static void finializeAndSubmit() throws JsonProcessingException {
        CodeExtractor.submitSourcesAndLogs();
    }

    public static final String LIST_URL = "/employees";
    public static final String ADD_URL = "/employees/add";
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

