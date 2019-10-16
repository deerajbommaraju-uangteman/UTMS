package Ut.Calculator.Calculator.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculate")
public class CalculatorCtrl {

    @CrossOrigin
    @PostMapping("/sumosdod")
    public String sumofnos(@RequestBody HashMap<String,String> values){
        //System.out.println("values::"+values);
        int a=Integer.parseInt(values.get("a"));
        int b=Integer.parseInt(values.get("b"));
        int sum = a+b;
        //System.out.println(sum);
        String result = Integer.toString(sum);
        return result;
    }
    
    public Map<String,String> StringToMap(String value){
        String body = value.substring(1, value.length()-1);
        String res=body.toString().replaceAll("\\", "");
        System.out.println(res);           
        String[] keyValuePairs = body.split(",");            
        Map<String,String> map = new HashMap<>();               
        for(String pair : keyValuePairs){
            String[] entry = pair.split(":");
            System.out.println(entry[1]);          
            map.put(entry[0].trim(), entry[1].trim());
        }
    return map;
    }

}