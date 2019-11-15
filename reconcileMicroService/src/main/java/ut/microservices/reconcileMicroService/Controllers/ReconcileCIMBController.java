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

import ut.microservices.reconcileMicroService.Services.ReconcileCIMBService;
import ut.microservices.reconcileMicroService.Services.ReconcileService;

@RestController
@RequestMapping(path = "/reconcile")

public class ReconcileCIMBController
{

        @Autowired
        private ReconcileService reconcileservice;
        
        @CrossOrigin
        @PostMapping(path="/upload-cimb-statement", consumes = "multipart/form-data")
        public String uploadCIMBStatement(@RequestPart("upload_cimb_statement") MultipartFile file) throws Exception {
           return ReconcileCIMBService.uploadCIMBStatement(file);
            // detailService.myService(file);
        }
}
        
       
        

