package cz.gopay.androidapi.v3.model.payment.support;

import java.util.Date;

/**
 *
 * @author Zbynek Novak novak.zbynek@gmail.com
 *
 */
public class Recurrence {

    public enum RecurrenceState {
        REQUESTED,
        STARTED,
        STOPPED
    }

    private RecurrenceCycle recurrence_cycle;

    private Integer recurrence_period;

    private Date recurrence_date_to;

    private RecurrenceState recurrence_state;

    public RecurrenceCycle getRecurrenceCycle() {
        return recurrence_cycle;
    }

    public void setRecurrenceCycle(RecurrenceCycle recurrenceCycle) {
        this.recurrence_cycle = recurrenceCycle;
    }

    public Integer getRecurrencePeriod() {
        return recurrence_period;
    }

    public void setRecurrencePeriod(Integer recurrencePeriod) {
        this.recurrence_period = recurrencePeriod;
    }

    public Date getRecurrenceDateTo() {
        return recurrence_date_to;
    }

    public void setRecurrenceDateTo(Date recurrenceDateTo) {
        this.recurrence_date_to = recurrenceDateTo;
    }

    public RecurrenceState getRecurrenceState() {
        return recurrence_state;
    }

    public void setRecurrenceState(RecurrenceState recurrenceState) {
        this.recurrence_state = recurrenceState;
    }

    @Override
    public String toString() {
        return String.format(
                "Recurrence [recurrenceCycle=%s, recurrencePeriod=%s, recurrenceDateTo=%s, recurrencState=%s]",
                recurrence_cycle, recurrence_period, recurrence_date_to, recurrence_state);
    }

    public static Recurrence build(Date recurrenceDateTo) {
        Recurrence result = new Recurrence();
        result.setRecurrenceDateTo(recurrenceDateTo);
        return result;
    }

    public Recurrence onDemand() {
        this.recurrence_cycle = RecurrenceCycle.ON_DEMAND;
        return this;
    }

    public Recurrence withTimeInterval(RecurrenceCycle recurrenceCycle, Integer recurrencePeriod) {
        this.recurrence_cycle = recurrenceCycle;
        this.recurrence_period = recurrencePeriod;
        return this;
    }

    public Recurrence inState(RecurrenceState state) {
        this.recurrence_state = state;
        return this;
    }

}
