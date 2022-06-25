package dev.nightzen.codesharing.business.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.ZoneOffset.UTC;

@Entity
@Table(indexes = {@Index(columnList = "uuid")})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Code {
    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    @Id
    @GeneratedValue
    @JsonIgnore
    Long id;

    @Column(unique = true)
    @JsonIgnore
    String uuid;

    @Column
    @NotBlank
    private String code;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime date;

    @Column
    @NotNull
    Long time;

    @Column
    @JsonIgnore
    Boolean timeRestriction;

    public Long getTime() {
        return timeRestriction ? time - LocalDateTime.now().toEpochSecond(UTC) : time;
    }

    @Column
    @NotNull
    Long views;

    @Column
    @JsonIgnore
    Boolean viewsRestriction;

    @JsonIgnore
    public String getFormattedDate() {
        return date.format(formatter);
    }

    @JsonIgnore
    public boolean isExpired() {
        return timeRestriction && getTime() <= 0 || viewsRestriction && views <= 0;
    }
}
