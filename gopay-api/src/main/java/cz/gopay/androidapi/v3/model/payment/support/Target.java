package cz.gopay.androidapi.v3.model.payment.support;


/**
 *
 * @author Zbynek Novak novak.zbynek@gmail.com
 *
 */
public class Target {

    public enum TargetType {
        ACCOUNT,
        BANK_ACCOUNT,
        COUPON;
    }

    private TargetType type;

    private Long goid;

    public Long getGoId() {
        return goid;
    }

    public void setGoId(Long goId) {
        this.goid = goId;
    }

    public TargetType getType() {
        return type;
    }

    public void setType(TargetType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("TargetParty [type=%s, goId=%s]", type, goid);
    }

    public static Target create() {
        return new Target();
    }
    
    public static Target createEWallet(String email) {
        Target created = create();
        created.type = TargetType.ACCOUNT;
        return created;
    }

    public static Target createEShop(Long goID) {
        Target created = create();
        created.eshop(goID);
        return created;
    }
    
    public Target ewallet(String email) {
        this.type = TargetType.ACCOUNT;
        return this;
    }

    public Target eshop(Long goId) {
        this.type = TargetType.ACCOUNT;
        this.goid = goId;
        return this;
    }

}
