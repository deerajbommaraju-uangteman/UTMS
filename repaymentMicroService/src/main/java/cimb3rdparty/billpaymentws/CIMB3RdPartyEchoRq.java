//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.11.27 at 04:23:10 PM IST 
//


package cimb3rdparty.billpaymentws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://CIMB3rdParty/BillPaymentWS}EchoRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "echoRequest"
})
@XmlRootElement(name = "CIMB3rdParty_EchoRq")
public class CIMB3RdPartyEchoRq {

    @XmlElement(name = "EchoRequest")
    protected String echoRequest;

    /**
     * Gets the value of the echoRequest property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEchoRequest() {
        return echoRequest;
    }

    /**
     * Sets the value of the echoRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEchoRequest(String value) {
        this.echoRequest = value;
    }

}
