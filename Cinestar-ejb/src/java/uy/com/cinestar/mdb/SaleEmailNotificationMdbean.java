package uy.com.cinestar.mdb;

import static com.sun.xml.ws.security.addressing.impl.policy.Constants.logger;

import java.util.logging.Level;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
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
public class SaleEmailNotificationMdbean implements MessageListener {

    @EJB
    private MailBean mailBean;

    public SaleEmailNotificationMdbean() {
    }

    @Override
    public void onMessage(Message message) {
        //aca enviar el email
        try {
            TextMessage textMessage = (TextMessage) message;
            mailBean.sendMail(textMessage.getText());
        } catch (Exception ex) {
            System.out.println("Error al llamar al Mail Bean - " + ex.getMessage());
            logger.getLogger(SaleEmailNotificationMdbean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
