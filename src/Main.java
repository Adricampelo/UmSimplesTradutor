public class Main {
    public static void main(String[] args) {

        String input = """
        let a = (45.5 + 5) / 2;
        print a + 2;
        """;

        Parser p = new Parser(input.getBytes());
        p.parse();

        Interpretador i = new Interpretador(p.output());
        i.run();
    }
}