/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.cinestar.mdb;

import static com.sun.xml.ws.security.addressing.impl.policy.Constants.logger;
import java.util.logging.Level;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author Gonza
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "jms/Topic"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/Topic"),
    @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "jms/Topic"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class SaleEmailNotificationMDBean implements MessageListener {
    
    public SaleEmailNotificationMDBean() {
    }
    
    @Override
    public void onMessage(Message message) {
        //aca enviar el email
        try{
            TextMessage textMessage=(TextMessage) message;
            System.out.println("-----------------------");
            System.out.println(textMessage.getText());
            System.out.println("-----------------------");
        }catch(JMSException ex){
            logger.getLogger(SaleEmailNotificationMDBean.class.getName()).log(Level.SEVERE,null,ex);
        }
        System.out.println("llego al mdbean");
    }
    
}
