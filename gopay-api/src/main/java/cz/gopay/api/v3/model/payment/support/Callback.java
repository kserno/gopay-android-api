package cz.gopay.api.v3.model.payment.support;

/**
 *
 * @author Zbynek Novak novak.zbynek@gmail.com
 *
 */
public class Callback {

    private String return_url;

    private String notification_url;

    public String getReturnUrl() {
        return return_url;
    }

    public void setReturnUrl(String returnUrl) {
        this.return_url = returnUrl;
    }

    public String getNotificationUrl() {
        return notification_url;
    }

    public void setNotificationUrl(String notificationUrl) {
        this.notification_url = notificationUrl;
    }

    @Override
    public String toString() {
        return String.format("Callback [returnUrl=%s, notificationUrl=%s]", return_url, notification_url);
    }

    public static Callback of(String returnUrl, String notificationUrl) {
        Callback result = new Callback();
        result.setReturnUrl(returnUrl);
        result.setNotificationUrl(notificationUrl);
        return result;
    }


}
