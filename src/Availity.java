import java.util.Stack;

public class Availity {
    public static boolean isBalanced(String input) {
        Stack<Character> parentheses = new Stack<>();
        for (char c : input.toCharArray()) {

            if (c == '(') {
                parentheses.push(c);
                continue;
            } else if (c == ')') {
                if (parentheses.isEmpty()) {
                    return false;
                }
                parentheses.pop();
            }

        }
        return parentheses.isEmpty();
    }

    public static void main (String [] args) {
        System.out.println(isBalanced(""));
        System.out.println(isBalanced("("));
        System.out.println(isBalanced(")"));


        System.out.println(isBalanced("()()"));

        System.out.println(isBalanced("(ABDFD())"));
        System.out.println(isBalanced("(ABDFD()))"));
        System.out.println(isBalanced("((ABDFD())"));
    }

}



