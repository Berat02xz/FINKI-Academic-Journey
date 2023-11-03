import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

interface Filter<T> {
    public T execute(T input);
}

class Pipe<T> implements Filter<T>{

    private List< Filter<T> > filters = new ArrayList< Filter<T> >();

    public void addFilter(Filter<T> filter){
        filters.add(filter);
    }

    public T runFilters(T input){
        for ( Filter<T> filter: filters ){
            input = filter.execute(input);
        }
        return input;
    }

    @Override
    public T execute(T input) {
        return input;
    }
}

class LowerCaseInput<T> implements Filter<String>{
    @Override
    public String execute(String input) {
        return input.toLowerCase();
    }
}

class StudentsNameRemoval<T> implements Filter<String> {
    @Override
    public String execute(String input) {

        String[] fields = input.split(",");
        String[] noNames = Arrays.copyOfRange(fields, 2, fields.length);

        String res = "";
        for (String field : noNames){
            res += field + ",";
        }

        return res;
    }
}

class LowerCaseCourseNames<T> implements Filter<Map<String, String>> {
    @Override
    public Map<String, String> execute(Map<String, String> input) {
        Map<String, String> courseGroups = new HashMap<String, String>();
        courseGroups.put("calculus", "math");
        courseGroups.put("discrete mathematics", "math");
        courseGroups.put("probability and statistics", "math");
        courseGroups.put("software architecture and design", "software engineering");
        courseGroups.put("software requirements analysis", "software engineering");
        courseGroups.put("structural programming", "programming");
        courseGroups.put("object oriented programming", "programming");
        courseGroups.put("algorithms and data structures", "programming");

        return courseGroups;
    }
}

public class PipeAndFilterProblem {

    public static void main(String[] args) throws FileNotFoundException {
        ClassLoader loader = PipeAndFilterProblem.class.getClassLoader();
        Scanner scanner = new Scanner(new File(loader.getResource("student_grades.csv").getFile()));

        Map<String, Integer> gradesByCourseGroup = new HashMap<String, Integer>();
        Map<String, Double> numberPassing = new HashMap<String, Double>();


        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            Pipe<String> firstPipe = new Pipe<>();
            Pipe<String> secondPipe = new Pipe<>();

            LowerCaseInput<String> lowerCaseInput = new LowerCaseInput<>();
            StudentsNameRemoval<String> studentsNameRemoval = new StudentsNameRemoval<>();


            firstPipe.addFilter(lowerCaseInput);
            firstPipe.addFilter(studentsNameRemoval);

            String line1 = lowerCaseInput.execute(line);
            String[] fields = line1.split(",");
            String[] noNames = Arrays.copyOfRange(fields, 2, fields.length);
            String res = "";
            for (String field : noNames){
                res += field + ",";
            }
            line1 = res.substring(0,res.length()-1);

            Map<String, String> courseGroups = new HashMap<String, String>();
            courseGroups.put("calculus", "math");
            courseGroups.put("discrete mathematics", "math");
            courseGroups.put("probability and statistics", "math");
            courseGroups.put("software architecture and design", "software engineering");
            courseGroups.put("software requirements analysis", "software engineering");
            courseGroups.put("structural programming", "programming");
            courseGroups.put("object oriented programming", "programming");
            courseGroups.put("algorithms and data structures", "programming");

            res = "";
            fields = line1.split(",");
            for (String field : fields){
                if (courseGroups.containsKey(field)){
                    res += courseGroups.get(field) + ",";
                }
                else{
                    res += field + ",";
                }
            }
            line1 = res.substring(0,res.length()-1);

            fields = line1.split(",");

            if (fields[2].length() < 3 && Integer.parseInt(fields[2]) > 5){
                if (gradesByCourseGroup.containsKey(fields[1])){
                    gradesByCourseGroup.put(fields[1], gradesByCourseGroup.get(fields[1]) + Integer.valueOf(fields[2]));
                }
                else {
                    gradesByCourseGroup.put(fields[1], Integer.valueOf(fields[2]));
                }

                if (numberPassing.containsKey(fields[1])){
                    numberPassing.put(fields[1], numberPassing.get(fields[1]) + 1);
                }
                else {
                    numberPassing.put(fields[1], 1.0);
                }

                System.out.println(line1);
            }


            fields = line.split(",");
            String[] noGrade = Arrays.copyOfRange(fields, 0, fields.length-1);
            res = "";
            for (String field : noGrade){
                res += field + ",";
            }
            String grade = fields[fields.length-1].toLowerCase();
            if (grade.length()<3) {
                int newGrade = Integer.parseInt(grade) - 5;
                res += newGrade;
            }
            String line2 = res;
            fields = line2.split(",");
            noNames = Arrays.copyOfRange(fields, 2, fields.length);
            res = "";
            for (String field : noNames){
                res += field + ",";
            }
            line2 = res.substring(0,res.length()-1);

            System.out.println(line2);



        }

        for (Map.Entry<String, Integer> grade: gradesByCourseGroup.entrySet()){
            System.out.println(grade.getKey() + " " + grade.getValue() / numberPassing.get(grade.getKey()));
        }

    }
}
