package ut.microservices.investormicroservice.service;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ut.microservices.investormicroservice.model.DigisignAgreement;
import ut.microservices.investormicroservice.model.InvestorVAHistory;
import ut.microservices.investormicroservice.model.MrInvestor;
import ut.microservices.investormicroservice.repository.IGenericDAO;

@Service
@Transactional
public class BulkSignService{

    IGenericDAO<InvestorVAHistory> investorVAHistoryDAO;
    IGenericDAO<DigisignAgreement> digisignAgreementDAO;
    IGenericDAO<MrInvestor> mrInvestorDAO;

    @Autowired
    DigisignService digisignService;

    @Autowired
    public void setInvestorVAHistoryDAO(IGenericDAO<InvestorVAHistory> investorVAHistoryDAO) {
        this.investorVAHistoryDAO = investorVAHistoryDAO;
        this.investorVAHistoryDAO.setClazz(InvestorVAHistory.class);
    }

    @Autowired
    public void setDigisignAgreementDAO(IGenericDAO<DigisignAgreement> digisignAgreementDAO) {
        this.digisignAgreementDAO = digisignAgreementDAO;
        this.digisignAgreementDAO.setClazz(DigisignAgreement.class);
    }

    @Autowired
    public void setMrInvestorDAO(IGenericDAO<MrInvestor> mrInvestorDAO) {
        this.mrInvestorDAO = mrInvestorDAO;
        this.mrInvestorDAO.setClazz(MrInvestor.class);
    }

	public void lenderUTDocumentBulkSign(String vaNumber) {
        List<InvestorVAHistory> investorVAHistoryList=investorVAHistoryDAO.findBy("vaNumber", vaNumber);
        Iterator<InvestorVAHistory> iterator=investorVAHistoryList.iterator();
        List<String> documentsList=new LinkedList<String>();
        while(iterator.hasNext()){
            DigisignAgreement digisignAgreement=digisignAgreementDAO.findBy("applicationID", Integer.toString(iterator.next().getApplicationID())).get(0);
            if(digisignAgreement.getStatusLenderAgreement().equalsIgnoreCase("D")){
                if(!digisignService.isDocumentExpired(digisignAgreement)){
                    documentsList.add(digisignAgreement.getDocumentLenderID());
                } 
            }
        }
        MrInvestor investor=mrInvestorDAO.findBy("ID",Integer.toString(investorVAHistoryList.get(0).getInvestorID())).get(0);
        String investorEmailID=investor.getEmail();
        //TODO
        //Need to hit Digisign URL
        //Data: companyUserID,InvestorEmailID,documentsList
	}

	public void lenderBorrowerDocumentBulkSign(String vaNumber) {
        List<InvestorVAHistory> investorVAHistoryList=investorVAHistoryDAO.findBy("vaNumber", vaNumber);
        Iterator<InvestorVAHistory> iterator=investorVAHistoryList.iterator();
        List<String> documentsList=new LinkedList<String>();
        while(iterator.hasNext()){
            DigisignAgreement digisignAgreement=digisignAgreementDAO.findBy("applicationID", Integer.toString(iterator.next().getApplicationID())).get(0);
            if(digisignAgreement.getStatusAgreement().equalsIgnoreCase("D")){
                if(!digisignService.isDocumentExpired(digisignAgreement)){
                    documentsList.add(digisignAgreement.getDocumentID());
                } 
            }
        }
        MrInvestor investor=mrInvestorDAO.findBy("ID",Integer.toString(investorVAHistoryList.get(0).getInvestorID())).get(0);
        String investorEmailID=investor.getEmail();
        //TODO
        //Need to hit Digisign URL
        //Data: companyUserID,InvestorEmailID,documentsList
    }
}