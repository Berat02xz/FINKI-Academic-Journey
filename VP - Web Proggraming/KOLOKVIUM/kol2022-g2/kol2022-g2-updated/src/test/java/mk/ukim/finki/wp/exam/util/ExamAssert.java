package mk.ukim.finki.wp.exam.util;

public class ExamAssert {

    public static boolean assertNotEmpty(String message, String actual) {
        if (actual == null || actual.trim().isEmpty()) {
            fail(message, "not empty", actual);
            return false;
        }
        success(message, "not empty", actual);
        return true;
    }

    public static boolean assertEquals(String message, Object expected, Object actual) {
        if (expected == null && actual == null) {
            success(message, expected, actual);
            return true;
        } else if (expected == null || actual == null) {
            fail(message, expected, actual);
            return false;
        } else if (expected.equals(actual)) {
            success(message, expected, actual);
            return true;
        } else {
            fail(message, expected, actual);
            return false;
        }
    }

    public static boolean assertNotEquals(String message, Object expected, Object actual) {
        if (expected == null && actual == null) {
            fail("(NOT EQUAL) " + message, expected, actual);
            return false;
        } else if (expected == null || actual == null) {
            success("(NOT EQUAL) " + message, expected, actual);
            return true;
        } else if (expected.equals(actual)) {
            fail("(NOT EQUAL) " + message, expected, actual);
            return false;
        } else {
            success("(NOT EQUAL) " + message, expected, actual);
            return true;
        }
    }

    public static boolean assertUrlEquals(String message, String expected, String actual) {
        if (expected == null && actual == null) {
            success(message, expected, actual);
            return true;
        } else if (expected == null || actual == null) {
            fail(message, expected, actual);
            return false;
        } else if (expected.equals(actual)) {
            success(message, expected, actual);
            return true;
        } else if (actual.contains(expected) && actual.length() == expected.length() + 1) {
            success(message, expected, actual);
            return true;
        } else {
            fail(message, expected, actual);
            return false;
        }
    }

    private static void fail(String message, Object expected, Object actual) {
        SubmissionHelper.submitFailedAssert(message, expected, actual);
    }

    private static void success(String message, Object expected, Object actual) {
        SubmissionHelper.submitSuccessAssert(message, expected, actual);
    }
}
