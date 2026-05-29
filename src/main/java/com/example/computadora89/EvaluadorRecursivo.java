package com.example.computadora89;

import java.util.Stack;

public class EvaluadorRecursivo {

    private static int obtenerPrioridad(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return -1;
    }

    public static String convertirApostfix(String infix) {
        StringBuilder resultado = new StringBuilder();
        Stack<Character> pila = new Stack<>();

        infix = infix.replaceAll("\\s+", "");

        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                resultado.append(c).append(" ");
            }
            else if (c == '(') {
                pila.push(c);
            }
            else if (c == ')') {
                while (!pila.isEmpty() && pila.peek() != '(') {
                    resultado.append(pila.pop()).append(" ");
                }
                pila.pop();
            }
            else {
                while (!pila.isEmpty() && obtenerPrioridad(c) <= obtenerPrioridad(pila.peek())) {
                    resultado.append(pila.pop()).append(" ");
                }
                pila.push(c);
            }
        }

        while (!pila.isEmpty()) {
            resultado.append(pila.pop()).append(" ");
        }

        return resultado.toString().trim();
    }

    public static String resolverExpresionPostfix(String postfix) {
        Stack<Double> pila = new Stack<>();
        String[] tokens = postfix.split("\\s+");

        try {
            for (String token : tokens) {
                if (token.isEmpty()) continue;

                if (Character.isDigit(token.charAt(0))) {
                    pila.push(Double.parseDouble(token));
                }
                else {
                    double val2 = pila.pop();
                    double val1 = pila.pop();
                    char op = token.charAt(0);

                    switch (op) {
                        case '+': pila.push(val1 + val2); break;
                        case '-': pila.push(val1 - val2); break;
                        case '*': pila.push(val1 * val2); break;
                        case '/': pila.push(val1 / val2); break;
                    }
                }
            }
            return String.valueOf(pila.pop());
        } catch (Exception e) {
            return "Error al evaluar: " + e.getMessage();
        }
    }
}