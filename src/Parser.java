public class Parser {
    private Scanner scan;
    private Token currentToken;
    private StringBuilder output = new StringBuilder();

    public Parser(byte[] input) {
        scan = new Scanner(input);
        currentToken = scan.nextToken();
    }

    void letStatement() {
        match(TokenType.LET);
        var id = currentToken.lexeme;
        match(TokenType.IDENT);
        match(TokenType.EQ);
        expr();

        output.append("pop ").append(id)
                .append(System.lineSeparator());

        match(TokenType.SEMICOLON);
    }

    void printStatement() {
        match(TokenType.PRINT);
        expr();

        output.append("print")
                .append(System.lineSeparator());

        match(TokenType.SEMICOLON);
    }

    void statement() {
        if (currentToken.type == TokenType.PRINT) {
            printStatement();
        } else if (currentToken.type == TokenType.LET) {
            letStatement();
        } else {
            throw new Error("syntax error");
        }
    }

    void statements() {
        while (currentToken.type != TokenType.EOF) {
            statement();
        }
    }

    public void parse() {
        statements();
    }

    public String output() {
        return output.toString();
    }

    private void nextToken() {
        currentToken = scan.nextToken();
    }

    private void match(TokenType t) {
        if (currentToken.type == t) {
            nextToken();
        } else {
            throw new Error("syntax error");
        }
    }


    void expr() {
        term();
        oper();
    }

    void term() {
        fator();
        multDiv();
    }

    void fator() {
        if (currentToken.type == TokenType.NUMBER) {
            number();

        } else if (currentToken.type == TokenType.IDENT) {
            output.append("push ")
                    .append(currentToken.lexeme)
                    .append(System.lineSeparator());

            match(TokenType.IDENT);

        } else if (currentToken.type == TokenType.LPAREN) {
            match(TokenType.LPAREN);

            expr();

            match(TokenType.RPAREN);

        } else {
            throw new Error("syntax error");
        }
    }

    void number() {
        output.append("push ")
                .append(currentToken.lexeme)
                .append(System.lineSeparator());

        match(TokenType.NUMBER);
    }

    void oper() {
        if (currentToken.type == TokenType.PLUS) {
            match(TokenType.PLUS);
            term();

            output.append("add")
                    .append(System.lineSeparator());

            oper();

        } else if (currentToken.type == TokenType.MINUS) {
            match(TokenType.MINUS);
            term();

            output.append("sub")
                    .append(System.lineSeparator());

            oper();
        }

    }
    void multDiv() {
        if (currentToken.type == TokenType.MULT) {
            match(TokenType.MULT);
            fator();

            output.append("mul")
                    .append(System.lineSeparator());

            multDiv();

        } else if (currentToken.type == TokenType.DIV) {
            match(TokenType.DIV);
            fator();

            output.append("div")
                    .append(System.lineSeparator());

            multDiv();
        }
    }

}