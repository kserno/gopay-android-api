package cz.gopay.api.v3.model.payment.support;



/**
 *
 * @author Zbynek Novak novak.zbynek@gmail.com
 *
 */
public class Preauthorization {

    public enum PreAuthState {
        REQUESTED,
        AUTHORIZED,
        CAPTURED,
        CANCELED
    }

    private Boolean requested;

    private PreAuthState state;

    public Boolean getRequested() {
        return requested;
    }

    public void setRequested(Boolean requested) {
        this.requested = requested;
    }

    public PreAuthState getPreAuthState() {
        return state;
    }

    public void setPreAuthState(PreAuthState preAuthState) {
        this.state = preAuthState;
    }

    @Override
    public String toString() {
        return String.format("Preauthorization [requested=%s, preAuthState=%s]", requested, state);
    }

    public static Preauthorization build() {
        Preauthorization result = new Preauthorization();
        return result;
    }

    public Preauthorization requested(Boolean requested) {
        this.requested = requested;
        return this;
    }

    public Preauthorization inState(PreAuthState state) {
        this.state = state;
        return this;
    }
}
