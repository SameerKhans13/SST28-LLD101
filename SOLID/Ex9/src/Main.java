public class Main {
    public static void main(String[] args) {
        System.out.println("=== Evaluation Pipeline ===");
        Submission sub = new Submission("23BCS1007", "public class A{}", "A.java");

        // concrete components, could be swapped in tests
        Checker checker = new PlagiarismChecker();
        Grader grader = new CodeGrader();
        Writer writer = new ReportWriter();
        Rubric rubric = new Rubric();

        EvaluationPipeline pipeline = new EvaluationPipeline(checker, grader, writer, rubric);
        pipeline.evaluate(sub);
    }
}
