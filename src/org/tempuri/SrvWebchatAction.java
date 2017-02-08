
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="nFunName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="nData" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "nFunName",
    "nData"
})
@XmlRootElement(name = "SrvWebchat___Action")
public class SrvWebchatAction {

    @XmlElement(required = true)
    protected String nFunName;
    @XmlElement(required = true)
    protected String nData;

    /**
     * 获取nFunName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNFunName() {
        return nFunName;
    }

    /**
     * 设置nFunName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNFunName(String value) {
        this.nFunName = value;
    }

    /**
     * 获取nData属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNData() {
        return nData;
    }

    /**
     * 设置nData属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNData(String value) {
        this.nData = value;
    }

}
