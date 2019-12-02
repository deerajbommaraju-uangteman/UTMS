package ut.microservices.reconcilems.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import ut.microservices.reconcilems.dao.IGenericDao;
import ut.microservices.reconcilems.models.ApplicantData;
import ut.microservices.reconcilems.models.ApplicationData;
import ut.microservices.reconcilems.models.CustomerLoanData;
import ut.microservices.reconcilems.models.CustomerLoanRepayment;
import ut.microservices.reconcilems.models.CustomerVaHistory;
import ut.microservices.reconcilems.models.GetCustomerLoanDataView;
import ut.microservices.reconcilems.models.GetIndexView;
import ut.microservices.reconcilems.models.GetViewDetailView;
import ut.microservices.reconcilems.models.MrVendorFee;
import ut.microservices.reconcilems.models.RekapPayment;
import ut.microservices.reconcilems.models.RekapPaymentDetail;
import ut.microservices.reconcilems.models.RekapReconcile;

@Service
public class ReadFileService {

    @Autowired
    ObjectMapper objectMapper;

    IGenericDao<CustomerLoanData> customerLoanDataDAO;
    @Autowired
    public void setDaoCustomerLoanData(IGenericDao<CustomerLoanData>
    customerLoanDataDAO) {
    this.customerLoanDataDAO = customerLoanDataDAO;
    customerLoanDataDAO.setClazz(CustomerLoanData.class);
    }

    IGenericDao<CustomerVaHistory> customerVaHistoryDAO;
    public void setDaoCustomerVaHistory(IGenericDao<CustomerVaHistory>
    customerVaHistoryDAO) {
    this.customerVaHistoryDAO = customerVaHistoryDAO;
    customerVaHistoryDAO.setClazz(CustomerVaHistory.class);
    }

    IGenericDao<CustomerLoanRepayment> customerLoanRepaymentDAO;
    public void setDaoCustomerLoanRepayment(IGenericDao<CustomerLoanRepayment>
    customerLoanRepaymentDAO) {
    this.customerLoanRepaymentDAO = customerLoanRepaymentDAO;
    customerLoanRepaymentDAO.setClazz(CustomerLoanRepayment.class);
    }

    IGenericDao<ApplicationData> applicationDataDAO;
    public void setDaoApplicationData(IGenericDao<ApplicationData>
    applicationDataDAO) {
    this.applicationDataDAO = applicationDataDAO;
    applicationDataDAO.setClazz(ApplicationData.class);
    }

    IGenericDao<ApplicantData> applicantDataDAO;
    public void setDaoApplicantData(IGenericDao<ApplicantData> applicantDataDAO)
    {
    this.applicantDataDAO = applicantDataDAO;
    applicantDataDAO.setClazz(ApplicantData.class);
    }

    IGenericDao<GetCustomerLoanDataView> getCustomerLoanDatadao;

    @Autowired
    public void setDaoGetCustomerLoanDataView(IGenericDao<GetCustomerLoanDataView> daoToSet) {
        getCustomerLoanDatadao = daoToSet;
        getCustomerLoanDatadao.setClazz(GetCustomerLoanDataView.class);
    }

    IGenericDao<GetIndexView> getIndexdao;

    @Autowired
    public void setDaoGetIndexView(IGenericDao<GetIndexView> daoToSet) {
        getIndexdao = daoToSet;
        getIndexdao.setClazz(GetIndexView.class);
    }

    IGenericDao<GetViewDetailView> getViewDetailViewdao;

    @Autowired
    public void setDaoGetViewDetailView(IGenericDao<GetViewDetailView> daoToSet) {
        getViewDetailViewdao = daoToSet;
        getViewDetailViewdao.setClazz(GetViewDetailView.class);
    }

    IGenericDao<RekapPayment> rekapPaymentdao;

    @Autowired
    public void setDaoRekapPayment(IGenericDao<RekapPayment> daoToSet) {
        rekapPaymentdao = daoToSet;
        rekapPaymentdao.setClazz(RekapPayment.class);
    }

    IGenericDao<RekapReconcile> rekapReconciledao;

    @Autowired
    public void setDaoRekapReconcile(IGenericDao<RekapReconcile> daoToSet) {
        rekapReconciledao = daoToSet;
        rekapReconciledao.setClazz(RekapReconcile.class);
    }

    IGenericDao<MrVendorFee> mrVendorFeeDao;

    @Autowired
    public void setDaoMrVendorFee(IGenericDao<MrVendorFee> daoToSet) {
        mrVendorFeeDao = daoToSet;
        mrVendorFeeDao.setClazz(MrVendorFee.class);
    }

    IGenericDao<RekapPaymentDetail> rekapPaymentDetaildao;

    @Autowired
    public void setDaoRekapPaymentDetail(IGenericDao<RekapPaymentDetail> daoToSet) {
        rekapPaymentDetaildao = daoToSet;
        rekapPaymentDetaildao.setClazz(RekapPaymentDetail.class);
    }

    public Sheet saveAndGetWorkSheet(MultipartFile file) throws IOException {
        File saveFile = new File("D:/uploads");
        saveFile.mkdir();
        file.transferTo(new File("D:/uploads/" + file.getOriginalFilename()));
        File xlFile = new File("D:/uploads/" + file.getOriginalFilename());
        String fileExtension = xlFile.getName().substring(xlFile.getName().indexOf("."));
        System.out.println(fileExtension);
        FileInputStream excelFile = new FileInputStream(xlFile);
        Workbook fileWorkBook = null;
        if (fileExtension.equals(".xlsx")) {
            fileWorkBook = new XSSFWorkbook(excelFile);

        } else if (fileExtension.equals(".xls")) {
            fileWorkBook = new HSSFWorkbook(excelFile);
        }
        Sheet worksheet = fileWorkBook.getSheetAt(0);
        return worksheet;
    }

    public Map<String , Map<String, Object>> loanDataFromRPYMS(String VaNumber)
    throws Exception{
    final String baseUrl =
    "http://localhost:9093/user/getRepaymentData/"+VaNumber;
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> responseEntity = restTemplate.exchange(baseUrl,
    HttpMethod.GET, null, String.class);
    JsonNode jn1 = objectMapper.readTree(responseEntity.getBody());

    JsonNode jnapdata = jn1.get("ApplicantData");
    ApplicantData rapdata = objectMapper.readValue(jnapdata.toString(),
    ApplicantData.class);
    applicantDataDAO.save(rapdata);

    JsonNode jnappdata = jn1.get("ApplicationData");
    ApplicationData rappdata = objectMapper.readValue(jnappdata.toString(),
    ApplicationData.class);
    applicationDataDAO.save(rappdata);

    JsonNode jnLoanData = jn1.get("CustomerLoanData");
    System.out.println("result:"+ jn1.get("CustomerLoanData"));
    CustomerLoanData rcld = objectMapper.readValue(jnLoanData.toString(),
    CustomerLoanData.class);
    customerLoanDataDAO.save(rcld);

    JsonNode jnVaHistory = jn1.get("CustomerVaHistory");
    CustomerVaHistory rVaHistory = objectMapper.readValue(jnVaHistory.toString(),
    CustomerVaHistory.class);
    customerVaHistoryDAO.save(rVaHistory);

    JsonNode jnrepayment = jn1.get("CustomerLoanRepayment");
    CustomerLoanRepayment rrepayment =
    objectMapper.readValue(jnrepayment.toString(), CustomerLoanRepayment.class);
    customerLoanRepaymentDAO.save(rrepayment);

    Map<String , Map<String, Object>> result = objectMapper.convertValue(jn1, new
    TypeReference<Map<String , Map<String, Object>>>(){});
    return result;
    }

    public void parseCSV(MultipartFile file) throws FileNotFoundException, IOException {
        File saveFile = new File("D:/uploads");
        saveFile.mkdir();
        file.transferTo(new File("D:/uploads/" + file.getOriginalFilename()));
        CSVParser parser = new CSVParser(new FileReader("D:/uploads/" + file.getOriginalFilename()),
                CSVFormat.DEFAULT.withHeader());
        for (CSVRecord record : parser) {
            System.out.printf("%s\t%s\t%s\n", record.get(0), record.get(1), record.get(2), record.get(3),
                    record.get(4));
        }
        parser.close();
    }

    public List<String[]> readCSVFile(MultipartFile file) throws Exception {
        File saveFile = new File("D:/uploads");
        saveFile.mkdir();
        file.transferTo(new File("D:/uploads/" + file.getOriginalFilename()));
        CSVReader csvReader = new CSVReader(new FileReader(new File("D:/uploads/" + file.getOriginalFilename())));
        List<String[]> list = new ArrayList<>();
        list = csvReader.readAll();
        csvReader.close();
        return list;
    }

    public void uploadCIMBStatement(MultipartFile file) throws Exception {
        if (file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".")).equals(".csv")) {
            // List<String[]> data = readCSVFile(file);
        } else {
            Sheet worksheet = saveAndGetWorkSheet(file);
            double totalCredit = worksheet.getRow(8).getCell(3).getNumericCellValue();
            String settlementDate = worksheet.getRow(2).getCell(0).getStringCellValue();
            Date sdate = new SimpleDateFormat("dd/MM/yyyy").parse(settlementDate);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            String settlementID = ("CIMB" + formatter.format(sdate).toString());
            if (!checkDuplicateData(settlementID, totalCredit)) {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date receiptDate = df.parse(df.format(sdate));
                int checkDuplicatePayment = checkDuplicatePayment(settlementID);
                settlementID = (checkDuplicatePayment < 10) ? settlementID + "0" + (checkDuplicatePayment + 1)
                        : (settlementID + (checkDuplicatePayment + 1));
                String description = "CIMB Payment Virtual Account Report: " + settlementID;
                saveRekapReconcile(receiptDate, description, totalCredit, settlementID, "N");
                MrVendorFee record = mrVendorFeeDao.findByColumn("VendorName", "CIMB").get(0);
                double vendorfee = record.getVendorFee();
                double adminfee = record.getAdminFee();
                int count = worksheet.getPhysicalNumberOfRows() - 3;
                double paymentfee = vendorfee * count;
                saveRekapPayment(settlementID, receiptDate, adminfee, totalCredit, paymentfee, 3);
                int idpayment = getOnePayment();
                for (int i = 2; i < worksheet.getPhysicalNumberOfRows() - 1; i++) {
                    Row row = worksheet.getRow(i);
                    Date notifDate = null;
                    Integer rekCustID = 1;
                    Integer installmentIndex = 1;
                    Integer clprPartialIndex = 1;
                    Double amount = row.getCell(3).getNumericCellValue();
                    String PaymentCode = row.getCell(1).getStringCellValue();
                    String va_transmerchant_id = row.getCell(4).getStringCellValue();
                    if (!va_transmerchant_id.isEmpty()) {
                        saveRekapPaymentDetail(notifDate, va_transmerchant_id, idpayment, PaymentCode, amount,
                                rekCustID, installmentIndex, clprPartialIndex);
                    }

                }
            }

        }
    }

    public void uploadBCAStatement(MultipartFile file) throws Exception {
        if (file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".")).equals(".csv")) {
            // String[] filenameparts = file.getOriginalFilename().split(".");
            List<String[]> data = readCSVFile(file);
            double totalCredit = 0.0;
            for (int i = 1; i < data.size(); i++) {
                totalCredit = totalCredit + Double.parseDouble(data.get(i)[3]);
            }
            String settlementDate = "2019-10-09 00:00:00";
            String settlementID = ("BCA" + settlementDate);
            if (!checkDuplicateData(settlementID, totalCredit)) {
                int checkDuplicatePayment = checkDuplicatePayment(settlementID);
                settlementID = (checkDuplicatePayment < 10) ? settlementID + "0" + (checkDuplicatePayment + 1)
                        : settlementID + (checkDuplicatePayment + 1);
                String description = "BCA Payment Virtual Account Report: " + settlementID;
                System.out.println("Date   ===" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(settlementDate));
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date receiptDate = df
                        .parse(df.format(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(settlementDate)));
                saveRekapReconcile(receiptDate, description, totalCredit, settlementID, "N");
                MrVendorFee record = mrVendorFeeDao.findByColumn("VendorName", "BCA").get(0);
                double vendorfee = record.getVendorFee();
                double adminfee = record.getAdminFee();
                int count = data.size() - 1;
                double paymentfee = vendorfee * count;
                saveRekapPayment(settlementID, receiptDate, adminfee, totalCredit, paymentfee, 4);
                int idpayment = getOnePayment();
                for (int i = 1; i < data.size() - 1; i++) {
                    Date notifDate = null;
                    Integer rekCustID = 1;
                    Integer installmentIndex = 1;
                    Integer clprPartialIndex = 1;
                    Double amount = Double.parseDouble(data.get(i)[3]);
                    System.out.println(amount);
                    String PaymentCode = data.get(i)[6];
                    String va_transmerchant_id = data.get(i)[2];
                    if (!va_transmerchant_id.isEmpty()) {
                        saveRekapPaymentDetail(notifDate, va_transmerchant_id, idpayment, PaymentCode, amount,
                                rekCustID, installmentIndex, clprPartialIndex);
                    }
                }
            }
        } else {
            Sheet worksheet = saveAndGetWorkSheet(file);
            double totalCredit = worksheet.getRow(8).getCell(3).getNumericCellValue();
            String settlementDate = worksheet.getRow(2).getCell(0).getStringCellValue();
            Date sdate = new SimpleDateFormat("dd/MM/yyyy").parse(settlementDate);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            String settlementID = ("BCA" + formatter.format(sdate).toString());
            if (!checkDuplicateData(settlementID, totalCredit)) {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date receiptDate = df.parse(df.format(sdate));
                int checkDuplicatePayment = checkDuplicatePayment(settlementID);
                settlementID = (checkDuplicatePayment < 10) ? settlementID + "0" + (checkDuplicatePayment + 1)
                        : settlementID + (checkDuplicatePayment + 1);
                String description = "BCA Payment Virtual Account Report: " + settlementID;
                saveRekapReconcile(receiptDate, description, totalCredit, settlementID, "N");
                MrVendorFee record = mrVendorFeeDao.findByColumn("VendorName", "CIMB").get(0);
                double vendorfee = record.getVendorFee();
                double adminfee = record.getAdminFee();
                int count = worksheet.getPhysicalNumberOfRows() - 1;
                double paymentfee = vendorfee * count;
                // saveRekapPayment(settlementID, receiptDate, adminfee, totalCredit,
                // paymentfee, 3);
                int idpayment = getOnePayment();
                for (int i = 2; i < worksheet.getPhysicalNumberOfRows() - 1; i++) {
                    Row row = worksheet.getRow(i);
                    Date notifDate = null;
                    Integer rekCustID = 0;
                    Integer installmentIndex = 0;
                    Integer clprPartialIndex = 0;
                    Double amount = row.getCell(3).getNumericCellValue();
                    String PaymentCode = row.getCell(6).getStringCellValue();
                    String va_transmerchant_id = row.getCell(2).getStringCellValue();
                    if (!va_transmerchant_id.isEmpty()) {
                        saveRekapPaymentDetail(notifDate, va_transmerchant_id, idpayment, PaymentCode, amount,
                                rekCustID, installmentIndex, clprPartialIndex);
                    }
                }
            }
        }
    }

    public void saveRekapReconcile(Date sdate, String description, Double totalCredit, String settlementID,
            String isReconcile) {
        RekapReconcile rekapReconcile = new RekapReconcile();
        rekapReconcile.setReceiptDate(sdate);
        rekapReconcile.setDescription(description);
        rekapReconcile.setCredit(totalCredit);
        rekapReconcile.setSettlementID(settlementID);
        rekapReconcile.setIsReconcile(isReconcile);
        rekapReconciledao.save(rekapReconcile);
    }

    public void saveRekapPayment(String settlementID, Date sdate, Double adminfee, Double totalCredit,
            Double paymentfee, int vendorFeeId) {
        RekapPayment rekapPayment = new RekapPayment();
        rekapPayment.setSettlementID(settlementID);
        rekapPayment.setSettlementDate(sdate);
        rekapPayment.setAdminFee(adminfee);
        rekapPayment.setTotalAmount(totalCredit);
        rekapPayment.setPaymentFee(paymentfee);
        rekapPayment.setTotalAmountForMerchant(totalCredit);
        rekapPayment.setTotalAmountTransferred(totalCredit);
        rekapPayment.setVendorFeeID(vendorFeeId);
        rekapPaymentdao.save(rekapPayment);
    }

    public void saveRekapPaymentDetail(Date notifDate, String va_transmerchant_id, int idpayment, String PaymentCode,
            Double amount, int rekCustID, int installmentIndex, int clprPartialIndex) {
        RekapPaymentDetail rekapPaymentDetail = new RekapPaymentDetail();
        rekapPaymentDetail.setNotifDate(notifDate);
        rekapPaymentDetail.setPaymentCode(PaymentCode);
        rekapPaymentDetail.setIDOrder(va_transmerchant_id);
        rekapPaymentDetail.setAmount(amount);
        rekapPaymentDetail.setIDPayment(idpayment);
        rekapPaymentDetail.setRekCustID(rekCustID);
        rekapPaymentDetail.setInstallmentIndex(installmentIndex);
        rekapPaymentDetail.setClprPartialIndex(clprPartialIndex);
        rekapPaymentDetaildao.save(rekapPaymentDetail);
    }

    public int checkDuplicatePayment(String settlementID) {
        return rekapPaymentdao.findCountByColumn("SettlementID", settlementID);
    }

    public boolean checkDuplicateData(String settlementID, double totalCredit) {
        if (rekapReconciledao.findByTwoColumns("SettlementID", settlementID, "Credit", "" + totalCredit + "")
                .size() != 0) {
            return true;
        } else {
            return false;
        }
    }

    public int getOnePayment() {
        return rekapPaymentdao.findLatestRecord().get(0).getID();
    }

    // public void getCustomerLoanData(String vaTransMerchantId) throws Exception {
    // List<GetCustomerLoanDataView> usersList = getcustloandata.findAll();
    // System.out.println(usersList);
    // }

    public void getCustomerLoanData(String vaTransMerchantId) {
        GetCustomerLoanDataView cld = getCustomerLoanDatadao.findByColumn("VaTransactionMerchantID", vaTransMerchantId).get(0);
        System.out.println(cld);
    }

    public String getIndex() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        List<GetIndexView> index = getIndexdao.findAll();
        System.out.println(index);
        return objectMapper.writeValueAsString(index);
    }

    public void getViewDetail(String settlementId){
        GetViewDetailView viewdetail = getViewDetailViewdao.findByColumn("SettlementID",settlementId).get(0);
        System.out.println(viewdetail);
     }

} 

