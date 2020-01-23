package ut.microservice.authentication;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Base64;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin(allowedHeaders = "*")
@RequestMapping("/digisign")
public class AuthenticationController {
    private static final String JWT_HEADER = "{\"alg\":\"HS256\",\"typ\":\"JWT\"}";
    private static final String encoded_header = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9";
    private static final String encoded_signature = "jouO1S9KkaYwesErRBTf0Di10XSWD2g70neWtGUoA";
    @PostMapping(path = "/authentication")
    public String authenticate(@RequestBody String token) throws Exception{
        try{
        System.out.println(token);
        String[] parts = token.split("\\.");
        byte[] byteArray=Base64.getUrlDecoder().decode(parts[1]);
        String str = new String(byteArray);
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject json = objectMapper.readValue(str,JSONObject.class); 
        System.out.println(str); 
        System.out.println(json.toString());  
        String decode = json.toString();
        String decarray = Base64.getEncoder().encodeToString(decode.getBytes());
        System.out.println(decarray);
        byte[] byteArray2=Base64.getUrlDecoder().decode(decarray);
        String str2 = new String(byteArray2);
        System.out.println(str2);
        if(json.getUserid().equals("user") && json.getPassword().equals("pass")){
            return "Success";
            }
        }
        catch(Exception e){
            return "Failed";
        }
        return "Failed";
    }
    @PostMapping(path = "/generateToken")
    public ResponseEntity<String> generateToken(@RequestBody JSONObject data) throws Exception {
        String decode = data.toString();
        String decarray = Base64.getEncoder().encodeToString(decode.getBytes());
        if(data.getUserid().equals("user") && data.getPassword().equals("pass")){
            return new ResponseEntity<String>(encoded_header+"."+decarray+"."+encoded_signature,HttpStatus.OK);
        }

        return new ResponseEntity<String>("Invalid Username or Password", HttpStatus.UNAUTHORIZED);
    }

}