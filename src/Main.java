public class Main {
    public static void main(String[] args) {

        String input = """
        let a = 4.5;
        let b = 2;
        print a / b;
        """;

        Parser p = new Parser(input.getBytes());
        p.parse();



        Interpretador i = new Interpretador(p.output());
        i.run();
    }
}