package ut.microservices.reconcileMicroService.Services;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ut.microservices.reconcileMicroService.Dao.IGenericDao;
import ut.microservices.reconcileMicroService.Models.RekapPayment;
import ut.microservices.reconcileMicroService.Models.RekapPaymentDetail;
import ut.microservices.reconcileMicroService.Models.RekapReconcile;

@Service
public class ReconcileCIMBService{

    static IGenericDao<RekapPaymentDetail> rekapPaymentDetaildao;
    @Autowired
    public void setDaoRekapPaymentDetail(IGenericDao<RekapPaymentDetail> daoToSet) {
      rekapPaymentDetaildao = daoToSet;
      rekapPaymentDetaildao.setClazz(RekapPaymentDetail.class);
    }

  
    static IGenericDao<RekapReconcile> rekapReconciledao;

    @Autowired
    public void setDaoRekapReconcile(IGenericDao<RekapReconcile> daoToSet) {
        rekapReconciledao = daoToSet;
        rekapReconciledao.setClazz(RekapReconcile.class);
    }

    static IGenericDao<RekapPayment> rekapPaymentdao;

    @Autowired
    public void setDaoRekapPayment(IGenericDao<RekapPayment> daoToSet) {
        rekapPaymentdao = daoToSet;
        rekapPaymentdao.setClazz(RekapPayment.class);
    }

    @Autowired
    ObjectMapper objectMapper;

    public static String uploadCIMBStatement(MultipartFile file) throws Exception {

        RekapPayment rp = rekapPaymentdao.findLatestRecord().get(0);
        int idpayment = rp.getID() + 1;

        BufferedReader br;
        Date date;
        try {
            InputStream is = file.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            Double amount = new Double(0);
            br.readLine();
            br.readLine();
            int count = 0;
            String line = null;
            while ((line = br.readLine()) != null) {
                count++;

                // System.out.println(br.readLine());

                // System.out.println("=============");
                String[] columns = line.split(",");
                System.out.println(columns);

                Double val = Double.parseDouble(columns[3].replace("\"", "").replace(",", ""));
                amount = Double.sum(amount, val);
                System.out.println(amount);

                RekapPaymentDetail rekapPaymentDetail = new RekapPaymentDetail();
                rekapPaymentDetail.setIDOrder(columns[4]);
                rekapPaymentDetail.setAmount(columns[3]);
                rekapPaymentDetail.setIDPayment(idpayment);
                rekapPaymentDetail.setPaymentCode(columns[1]); 
                rekapPaymentDetaildao.save(rekapPaymentDetail);

            }

            Date now = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            String settlementID = "CIMB" + simpleDateFormat.format(now);
            int countRecords = rekapPaymentdao.findCountByColumn("SettlementID", settlementID);

            settlementID = (countRecords < 10) ? settlementID + "0" + (countRecords + 1) : settlementID + (countRecords + 1);
            System.out.println(settlementID);

            String description = "CIMB Payment Virtual Account Report: " + settlementID;

            RekapReconcile rekapReconcile = new RekapReconcile();
            rekapReconcile.setCredit(amount);
            rekapReconcile.setDescription(description);
            rekapReconcile.setReceiptDate(now);
            rekapReconcile.setSettlementID(settlementID);
            rekapReconcile.setIsReconcile("N");
            rekapReconciledao.save(rekapReconcile);

        double vendorfee = 0;
        double adminfee = 0;

        RekapPayment rekapPayment = new RekapPayment();
        rekapPayment.setTotalAmount(amount);
        rekapPayment.setSettlementID(settlementID);
        rekapPayment.setSettlementDate(now);
        rekapPayment.setTotalAmountForMerchant(amount);
        rekapPayment.setTotalAmountTransferred(amount);
        rekapPayment.setVendorFeeID(3);
        rekapPayment.setAdminFee(vendorfee - adminfee);
        rekapPayment.setPaymentFee(vendorfee*count);
        rekapPaymentdao.save(rekapPayment);
      }catch (IOException e) {
        System.err.println(e.getMessage());       
      }
      return "success";
  }
	

}