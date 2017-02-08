package org.tempuri;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.0.7
 * 2016-09-19T18:11:58.048+08:00
 * Generated source version: 3.0.7
 * 
 */
@WebServiceClient(name = "SrvWebchat", 
                  wsdlLocation = "http://172.168.0.200:8088/SOAP?service=SrvWebchat",
                  targetNamespace = "http://tempuri.org/") 
public class SrvWebchat_Service extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://tempuri.org/", "SrvWebchat");
    public final static QName SrvWebchatPort = new QName("http://tempuri.org/", "SrvWebchatPort");
    static {
        URL url = null;
        try {
            url = new URL("http://172.168.0.200:8088/SOAP?service=SrvWebchat");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(SrvWebchat_Service.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://172.168.0.200:8088/SOAP?service=SrvWebchat");
        }
        WSDL_LOCATION = url;
    }

    public SrvWebchat_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public SrvWebchat_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SrvWebchat_Service() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public SrvWebchat_Service(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public SrvWebchat_Service(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public SrvWebchat_Service(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    

    /**
     *
     * @return
     *     returns SrvWebchat
     */
    @WebEndpoint(name = "SrvWebchatPort")
    public SrvWebchat getSrvWebchatPort() {
        return super.getPort(SrvWebchatPort, SrvWebchat.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SrvWebchat
     */
    @WebEndpoint(name = "SrvWebchatPort")
    public SrvWebchat getSrvWebchatPort(WebServiceFeature... features) {
        return super.getPort(SrvWebchatPort, SrvWebchat.class, features);
    }

}