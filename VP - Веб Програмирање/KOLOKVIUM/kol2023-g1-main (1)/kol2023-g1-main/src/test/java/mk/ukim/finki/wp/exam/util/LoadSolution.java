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
import java.util.stream.Collectors;

public class LoadSolution {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static String index = "181065";

    public static void main(String[] args) throws Exception {
        if(args.length>0) {
            index = args[0];
        }
        tryResetSrcMain();
        getAndLoadLastSolution(index);
//        commitSolution(index);
    }

    private static void commitSolution(String index) {
        try {
            ProcessBuilder createBranch = new ProcessBuilder();
            createBranch.command("git", "checkout", "-b", index);
            Process createBranchProcess = createBranch.start();
            int exitCode = createBranchProcess.waitFor();
            System.err.println(new BufferedReader(new InputStreamReader(createBranchProcess.getInputStream())).lines().collect(Collectors.joining("\n")));


            ProcessBuilder addChangesBuilder = new ProcessBuilder();
            addChangesBuilder.command("git", "add", "src/main/*");
            Process addChangesProcess = addChangesBuilder.start();
            exitCode = addChangesProcess.waitFor();
            System.err.println(new BufferedReader(new InputStreamReader(addChangesProcess.getInputStream())).lines().collect(Collectors.joining("\n")));


            ProcessBuilder commitBuilder = new ProcessBuilder();
            commitBuilder.command("git", "commit", "-m", "'" + index + "'");
            Process commitProcess = commitBuilder.start();
            exitCode = commitProcess.waitFor();
            System.err.println(new BufferedReader(new InputStreamReader(commitProcess.getInputStream())).lines().collect(Collectors.joining("\n")));

            System.out.println("git push");
            ProcessBuilder pushBuilder = new ProcessBuilder();
            pushBuilder.command("git", "push", "--set-upstream", "origin", index);
            Process pushProcess = pushBuilder.start();
            exitCode = pushProcess.waitFor();
            System.err.println(new BufferedReader(new InputStreamReader(pushProcess.getInputStream())).lines().collect(Collectors.joining("\n")));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void tryResetSrcMain() {
        try {

            ProcessBuilder createBranch = new ProcessBuilder();
            createBranch.command("git", "checkout", "master");
            Process createBranchProcess = createBranch.start();
            int exitCode = createBranchProcess.waitFor();
            System.err.println(new BufferedReader(new InputStreamReader(createBranchProcess.getInputStream())).lines().collect(Collectors.joining("\n")));

            ProcessBuilder restoreBuilder = new ProcessBuilder();
            restoreBuilder.command("git", "restore", "-s@", "-SW", "--", "src/main/");
            Process restoreProcess = restoreBuilder.start();
            BufferedReader restoreReader = new BufferedReader(new InputStreamReader(restoreProcess.getInputStream()));

            String line;
            while ((line = restoreReader.readLine()) != null) {
                System.out.println(line);
            }
            exitCode = restoreProcess.waitFor();
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
        ResponseEntity<String> response = restTemplate.exchange("http://env4health.finki.ukim.mk/eg/api/grading/" + examId + "/last_submission/" + index,
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

    private static Integer examId = 16556;
}

