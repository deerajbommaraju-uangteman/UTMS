// package ut.microservices.reconcileMicroService.Services;

// import java.io.*;
// import java.text.SimpleDateFormat;
// import java.util.Date;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.web.bind.annotation.RequestPart;
// import org.springframework.web.multipart.MultipartFile;

// import ut.microservices.reconcileMicroService.Dao.IGenericDao;
// import ut.microservices.reconcileMicroService.Models.DetailModel;
// import ut.microservices.reconcileMicroService.Models.RekapPayment;
// import ut.microservices.reconcileMicroService.Models.RekapPaymentDetail;
// import ut.microservices.reconcileMicroService.Models.RekapReconcile;

// @Service
// public class DetailService{
//     IGenericDao<DetailModel> DetailModel;

//     @Autowired
//     public void setDaoDetailModel(IGenericDao<DetailModel> daoToSet) {
//         DetailModel = daoToSet;
//         DetailModel.setClazz(DetailModel.class);
//     }

//     IGenericDao<RekapPayment> RekapPayment;

//     @Autowired
//     public void setDaoRekapPayment(IGenericDao<RekapPayment> daoToSet) {
//       RekapPayment = daoToSet;
//       RekapPayment.setClazz(RekapPayment.class);
//     }
//     public String myService(MultipartFile file) throws Exception {
//         BufferedReader br;
//         try {
//             InputStream is = file.getInputStream();
//             br = new BufferedReader(new InputStreamReader(is));
//             Double amount =new Double(0);
//             br.readLine();
//             int count = 0;
//             String line = null;
//             Date now = new Date();
//             SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");   
//             String settlementID = "BCA" + simpleDateFormat.format(now);
//             int countRecords = RekapPayment.findCountByColumn("SettlementID", settlementID);
//             System.out.println("Count records"+countRecords);
//             settlementID = (countRecords<10)? settlementID+"0"+(countRecords+1):settlementID+(countRecords+1);
//             System.out.println(settlementID);
//             String description = "BCA Payment Virtual Account Report: "+settlementID;
//             System.out.println(settlementID);
//             while ((line = br.readLine()) != null) {
//                 count++;
//                 String[] columns = line.split(",");
//                 Double val=Double.parseDouble(columns[3]);
//                 amount=Double.sum(amount,val);

//                 DetailModel detailModel = new DetailModel();
//                 detailModel.setAmount(columns[3]);
//                 detailModel.setIDPayment(1);
//                 detailModel.setSettlementID(settlementID);
//                 detailModel.setTotalAmountTransferred(amount);
//                 detailModel.setVendorFeeID(4);
//                 // System.out.println(dm);
//                 DetailModel.save(detailModel);
//             }
//          } catch (IOException e) {
//             System.err.println(e.getMessage());       
//         }
//         return "success";
//     }
// }