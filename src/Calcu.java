import java.util.Stack;


public class Calcu {
    public static int precedence(char c) {
            switch (c) {
                case '+':
                case '-': return 1;
                case '*':
                case '/': return 2;
            }
            return -1;
        }

        private StringBuilder postfix = new StringBuilder();
        public void toPostfix(String infix) {
            Stack<Character> ops = new Stack<>();

            for (char ch : infix.toCharArray()) {
                if(1/infix.charAt(0)*-1==1) {
                    ops.push(ch);
                    continue;
                }
                if (Character.isWhitespace(ch)) continue;
                if (Character.isDigit(ch)) {
                    postfix.append(ch);
                }
                else if (ch == '(') {
                    ops.push(ch);
                }
                else if (ch == ')') {
                    while (!ops.isEmpty() && ops.peek() != '(') {
                        postfix.append(ops.pop());
                    }
                    if (!ops.isEmpty()) ops.pop(); // remove '('
                }
                else {
                    while (!ops.isEmpty() && precedence(ops.peek()) >= precedence(ch)) {
                        postfix.append(ops.pop());
                    }
                    ops.push(ch);
                }
            }

            while (!ops.isEmpty()) {
                postfix.append(ops.pop());
            }

            System.out.println("Postfix: " + postfix.toString());
        }

        // Evaluate postfix expression
        public int evaluate() {
            Stack<Integer> values = new Stack<>();

            for (char ch : postfix.toString().toCharArray()) {

                if (Character.isDigit(ch)) {
                    values.push(ch - '0');
                } else {
                    int b = values.pop();
                    int a = values.pop();
                    switch (ch) {
                        case '+': values.push(a + b); break;
                        case '-': values.push(a - b); break;
                        case '*': values.push(a * b); break;
                        case '/': values.push(a / b); break;
                    }
                }
            }

            return values.pop();


}
}