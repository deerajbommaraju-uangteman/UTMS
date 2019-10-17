package Ut.Calculator.Calculator.Controller;

import java.util.HashMap;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculate")
public class CalculatorCtrl {

    @CrossOrigin
    @PostMapping("/sumofnos")
    public String sumofnos(@RequestBody HashMap<String,String> values){
        //System.out.println("values::"+values);
        int a=Integer.parseInt(values.get("a"));
        int b=Integer.parseInt(values.get("b"));
        int sum = a+b;
        //System.out.println(sum);
        String result = Integer.toString(sum);
        return result;
    }

    @PostMapping("/subtractofnos")
    public String subtractofnos(@RequestBody HashMap<String,String> values){
        //System.out.println("values::"+values);
        int a=Integer.parseInt(values.get("a"));
        int b=Integer.parseInt(values.get("b"));
        int sum = a-b;
        //System.out.println(sum);
        String result = Integer.toString(sum);
        return result;
    }

    @PostMapping("/multiplyofnos")
    public String multiplyofnos(@RequestBody HashMap<String,String> values){
        //System.out.println("values::"+values);
        int a=Integer.parseInt(values.get("a"));
        int b=Integer.parseInt(values.get("b"));
        int sum = a*b;
        //System.out.println(sum);
        String result = Integer.toString(sum);
        return result;
    }

    @PostMapping("/divideofnos")
    public String divideofnos(@RequestBody HashMap<String,String> values){
        //System.out.println("values::"+values);
        int a=Integer.parseInt(values.get("a"));
        int b=Integer.parseInt(values.get("b"));
        int sum = a/b;
        //System.out.println(sum);
        String result = Integer.toString(sum);
        return result;
    }
}