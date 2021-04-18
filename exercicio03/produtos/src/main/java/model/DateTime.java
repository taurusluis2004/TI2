package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateTime {

    public static final ZoneId UTC = ZoneId.of("UTC");

    /**
     *
     * @return
     * @deprecated use {@link #getUTCDate()
     */
    @Deprecated
    public static final LocalDate getLocalDate() {
        return getLocalDateTime().toLocalDate();
    }

 
    /**
     * Gets the current time in UTC
     * <p>
     * @return
     * @deprecated use {@link #getUTCDateTime()
     */
    @Deprecated
    public static final LocalDateTime getLocalDateTime() {
        return getLocalDateTime();
    }

   
}