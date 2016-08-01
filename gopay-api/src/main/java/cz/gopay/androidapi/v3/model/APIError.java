package cz.gopay.androidapi.v3.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;




//@XmlRootElement(name = "error")
public class APIError {

   // @XmlJavaTypeAdapter(GPTimestampAdapter.class)
    private Date date_issued;

    private List<ErrorElement> errors;

    public APIError() {
    }

    public APIError(Date dateIssued) {
        this.date_issued = dateIssued;
    }

    public static APIError create() {
        return new APIError(new Date());
    }

    public APIError addError(String errorName, int errorCode, String message,
            String description) {
        if (errors == null) {
            errors = new ArrayList<>();
        }

        errors.add(new ErrorElement(errorName, errorCode, message, description));

        return this;
    }

    public APIError addError(String errorName, int errorCode, String field,
            String message, String description) {
        if (errors == null) {
            errors = new ArrayList<>();
        }

        errors.add(new ErrorElement(errorName, errorCode, field, message, description));
        return this;
    }

    public Date getDateIssued() {
        return date_issued;
    }

    public void setDateIssued(Date dateOccured) {
        this.date_issued = dateOccured;
    }

    public List<ErrorElement> getErrorMessages() {
        return errors;
    }

    public void setErrorMessages(List<ErrorElement> errors) {
        this.errors = errors;
    }

}
