package mk.ukim.finki.wp.exam.util;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CodeExtractor {

    public static void submitSourcesAndLogs() throws JsonProcessingException {
        File root = new File(".");
        System.out.println(root.getAbsolutePath());

        File basePackage = new File(root, "src");
        System.out.println(basePackage.getAbsolutePath());
        List<File> javaFiles = findJavaFiles(basePackage, ".java");
        Map<String, String> content = readFilesContent(javaFiles);

        File resources = new File(root, "src/main/resources");
        List<File> properties = findJavaFiles(resources, ".properties");
        List<File> templates = findJavaFiles(resources, ".html");
        templates.addAll(properties);
        Map<String, String> htmlAndTemplates = readFilesContent(templates);

        content.putAll(htmlAndTemplates);

        SubmissionHelper.submitSource(content);
    }

    public static List<File> findJavaFiles(File root, String extension) {
        List<File> javaFiles = new ArrayList<>();
        File[] files = root.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                javaFiles.addAll(findJavaFiles(f, extension));
            } else if (f.getName().endsWith(extension)) {
                javaFiles.add(f);
            }
        }
        return javaFiles;
    }

    public static Map<String, String> readFilesContent(List<File> javaFiles) {
        Map<String, String> fileContent = new TreeMap<>();
        for (File f : javaFiles) {
            try {
                String content = readString(f.getAbsolutePath());
                fileContent.put(f.getAbsolutePath(), content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileContent;
    }

    private static String readString(String path) throws IOException {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"))) {
            String line = null;
            while ((line = br.readLine()) != null)
                builder.append(line).append("\n");
        }
        return builder.toString();
    }
}
