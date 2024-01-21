package mk.ukim.finki.wp.exam.util;

public class ExamAssertionException extends RuntimeException {

    public ExamAssertionException(String message, Object expected, Object actual) {
        super(String.format("%s:\texpected: <%s>\tactual:\t<%s>", message, expected.toString(), actual.toString()));
    }
}
