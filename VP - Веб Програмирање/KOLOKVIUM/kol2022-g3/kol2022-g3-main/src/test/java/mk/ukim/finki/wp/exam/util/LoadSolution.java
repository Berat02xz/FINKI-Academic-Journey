package mk.ukim.finki.wp.exam.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;

public class LoadSolution {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws Exception {
          tryResetSrcMain();
          getAndLoadLastSolution("");
    }

    private static void tryResetSrcMain() {
        try {
            ProcessBuilder restoreBuilder = new ProcessBuilder();
            restoreBuilder.command("git", "restore", "-s@", "-SW", "--", "src/main/");
            Process restoreProcess = restoreBuilder.start();
            BufferedReader restoreReader = new BufferedReader(new InputStreamReader(restoreProcess.getInputStream()));

            String line;
            while ((line = restoreReader.readLine()) != null) {
                System.out.println(line);
            }
            int exitCode = restoreProcess.waitFor();
            System.out.println("\nExited with error code : " + exitCode);

            ProcessBuilder cleanBuilder = new ProcessBuilder();
            cleanBuilder.command("git", "clean", "-fdx", "src/main/");
            Process cleanProcess = cleanBuilder.start();
            BufferedReader cleanReader = new BufferedReader(new InputStreamReader(cleanProcess.getInputStream()));

            while ((line = cleanReader.readLine()) != null) {
                System.out.println(line);
            }
            exitCode = cleanProcess.waitFor();
            System.out.println("\nExited with error code : " + exitCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getAndLoadLastSolution(String index) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange("http://env4health.finki.ukim.mk/eg/api/grading/student_last/" + index,
                HttpMethod.GET,
                new HttpEntity<>(createHeaders(System.getenv("WP_USER"), System.getenv("WP_PASS"))),
                String.class);
        loadSolution(new ByteArrayInputStream(response.getBody().getBytes()));
    }

    public static void getSolutionById(Integer id) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange("http://env4health.finki.ukim.mk/eg/api/grading/submission/" + id,
                HttpMethod.GET,
                new HttpEntity<>(createHeaders(System.getenv("WP_USER"), System.getenv("WP_PASS"))),
                String.class);
        loadSolution(new ByteArrayInputStream(response.getBody().getBytes()));
    }

    public static void loadSolution(InputStream response) throws IOException {
        HashMap<String, String> solution = objectMapper
                .reader()
                .forType(new TypeReference<HashMap<String, String>>() {
                })
                .readValue(response);
        if (solution.isEmpty()) {
            System.err.println("EMPTY SOLUTION!");
        } else {
            System.err.println(solution.keySet());
        }

        solution.keySet().forEach(k -> {
            new File("." + k.substring(0, k.lastIndexOf("/"))).mkdirs();
            try (BufferedWriter br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("." + k)))) {
                br.write(solution.get(k));
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }

    static HttpHeaders createHeaders(String username, String password) {
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
            String authHeader = "Basic " + new String(encodedAuth);
            set("Authorization", authHeader);
        }};
    }
}
