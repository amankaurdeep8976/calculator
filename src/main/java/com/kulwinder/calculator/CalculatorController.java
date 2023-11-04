package com.kulwinder.calculator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * HomeController
 */
@Controller
public class CalculatorController {

    String history = "No history yet!";

    public void addHistory(Double op1, Double op2, String operator, String result) {
        if (history.equals("No history yet!"))
            history = "";
        history += op1 + " " + operator + " " + op2 + " = " + result + "<br>";
    }

    @RequestMapping("/calculator/add")
    @ResponseBody
    public Double add(@RequestParam Double op1, @RequestParam Double op2) {
        double sum = op1 + op2;
        addHistory(op1, op2, "+", Double.valueOf(sum).toString());
        return op1 + op2;
    }

    @RequestMapping("/calculator/minus")
    @ResponseBody
    public Double minus(@RequestParam Double op1, @RequestParam Double op2) {
        double ans = op1 - op2;
        addHistory(op1, op2, "-", Double.valueOf(ans).toString());
        return op1 - op2;
    }

    @RequestMapping("/calculator/product")
    @ResponseBody
    public Double product(@RequestParam Double op1, @RequestParam Double op2) {
        double ans = op1 * op2;
        addHistory(op1, op2, "*", Double.valueOf(ans).toString());
        return op1 * op2;
    }

    @RequestMapping("/calculator/divide")
    @ResponseBody
    public ResponseEntity<String> divide(@RequestParam Double op1, @RequestParam Double op2) {
        if (op2 == 0) {
            addHistory(op1, op2, "/", "Infinity`");
            return new ResponseEntity<>("Cannot divide by zero!", HttpStatus.BAD_REQUEST);
        }
        Double ans = op1 / op2;
        addHistory(op1, op2, "/", ans.toString());
        return new ResponseEntity<>(ans.toString(), HttpStatus.OK);
    }

    @RequestMapping("/calculator/history")
    @ResponseBody
    public String history() {
        return history;
    }

    @RequestMapping("/calculator/clear")
    @ResponseBody
    public String clear() {
        history = "No history yet!";
        return "History cleared!";
    }
}