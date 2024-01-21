package mk.ukim.finki.wp.exam.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Map;

public class SubmissionHelper {

    public static String index;

    public static String exam;

    public static ObjectMapper objectMapper = new ObjectMapper();
    public static ArrayList<String> log = new ArrayList<>();
    public static ArrayList<Exception> errors = new ArrayList<>();
    public static boolean hasError = false;
    public static String test;

    public static void submitSource(Map<String, String> content) throws JsonProcessingException {

        String solution = objectMapper.writeValueAsString(content);
        String logString = objectMapper.writeValueAsString(log);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("examName", exam);
        map.add("index", index);
        map.add("solution", solution);
        map.add("log", logString);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("http://env4health.finki.ukim.mk/submit", request, String.class);

        System.err.println("SUCCESS SUBMIT");
    }

    public static void startTest(String testName) {
        test = testName;
        hasError = false;
        errors.clear();
        log.add(String.format("S;%s;Started", testName));
    }

    public static void endTest() {
        log.add(String.format("E;%s;%s", test, hasError ? "FAILED" : "PASSED"));
        showTestLog();
        test = null;
        if (hasError) {
            logErrors();
            throw new ExamAssertionException(test + " failed", "PASSED", "FAILED");
        }
    }

    public static void submitSuccessAssert(String message, Object expected, Object actual) {
        log.add(String.format("O;%s;%s", test, message));
    }

    public static void submitFailedAssert(String message, Object expected, Object actual) {
        log.add(String.format("X;%s;%s:\texpected: <%s>\tactual:\t<%s>", test, message, expected.toString(), actual.toString()));
        errors.add(new ExamAssertionException(message, expected, actual));
        hasError = true;
    }

    public static void logErrors() {
        for (Exception error : errors) {
            error.printStackTrace();
        }
    }

    private static void showTestLog() {
        for (String s : SubmissionHelper.log) {
            if (!s.contains(test))
                continue;
            if (s.startsWith("X")) {
                System.err.println("----" + s);
            } else if (s.startsWith("S")) {
                System.err.println("\n====================================\n" + s);
            } else {
                System.err.println("    " + s);
            }
        }
        System.err.println("\n====================================\n\n");
    }
}