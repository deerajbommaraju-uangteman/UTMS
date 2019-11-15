package ut.microservices.reconcileMicroService.Controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ut.microservices.reconcileMicroService.Services.ReconcileService;

@RestController
@RequestMapping(path = "/reconcile")

public class ReconcileController
{

        @Autowired
        private ReconcileService reconcileservice;
        
        @CrossOrigin
        @PostMapping(path="/uploadstatement", consumes = "multipart/form-data")
        public void uploadBCAStatement(@RequestPart("upload_bca_statement") MultipartFile file) throws Exception {
            reconcileservice.uploadBCAStatement(file);
            // detailService.myService(file);
        }

        @CrossOrigin
        @PostMapping("/getDetails")
        public @ResponseBody String getDetails(@RequestBody String settlementID) throws Exception {
            return reconcileservice.getDetails(settlementID);
        }
        
        @CrossOrigin
        @GetMapping("/joindata")
        public String displayBankStatements() throws Exception{
            return reconcileservice.displayBankStatements();
        }
        
        
        @CrossOrigin
        @PostMapping("/detaildata")
        public String detailDataTop(@RequestBody String settlementID) throws Exception{
            return reconcileservice.detailDataTop(settlementID);
        }
        
        @CrossOrigin
        @GetMapping("/repay")
        @ResponseBody
        public Map<String, Map<String, Object>> repaydata() throws Exception {
            return reconcileservice.loanDataFromRPYMS("39991983745");        
        }

        @CrossOrigin
        @PostMapping("/update-reconcile-status")
        public boolean updateReconcileStatus(@RequestBody String settlementID) throws Exception {
            return reconcileservice.updateReconcileStatus(settlementID);            
        } 
        
        

}
        

