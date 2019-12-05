package ut.microservices.reconcilems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ut.microservices.reconcilems.services.ReadFileService;


@RestController
@RequestMapping(path = "/reconcile")

public class UploadStatementsController
{   
    @Autowired
    ReadFileService readFileService;
    
    @CrossOrigin
    @PostMapping(path="/uploadstatement", consumes = "multipart/form-data")
    public void uploadCIMBStatement(@RequestPart("upload_cimb_statement") MultipartFile file) throws Exception {
        if (!file.isEmpty()){
            readFileService.uploadCIMBStatement(file);
        }else{
            System.out.println("File cannot be empty");
        }
    }

    @CrossOrigin
    @PostMapping(path="/uploadbcastatement", consumes = "multipart/form-data")
    public void uploadBCAStatement(@RequestPart("upload_bca_statement") MultipartFile file) throws Exception {
        if (!file.isEmpty()){
            readFileService.uploadBCAStatement(file);
        }else{
            System.out.println("File cannot be empty");
        }
    }

    @CrossOrigin
    @PostMapping(path="/test")
    public void test() throws Exception {
        readFileService.getCustomerLoanData("2RzhHddpYYLla");
    }

    
    @CrossOrigin
    @PostMapping(path="/testgetindex")
    public String GetIndex() throws Exception {
       return readFileService.getIndex();
    }

    @CrossOrigin
    @PostMapping(path="/testgetviewdetail")
    public void GetViewDetail() throws Exception {
        readFileService.getViewDetail("BCA2019110101");
    }
}
        

