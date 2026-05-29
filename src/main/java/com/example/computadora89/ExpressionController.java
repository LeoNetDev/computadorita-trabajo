package com.example.computadora89;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExpressionController {

    @PostMapping("/expresion")
    public String calcularInfix(@RequestBody ExpressionRequest request) {

        String infix = request.getInfix();

        String postfix = EvaluadorRecursivo.convertirApostfix(infix);

        String resultado = EvaluadorRecursivo.resolverExpresionPostfix(postfix);

        return resultado;
    }
}