package com.digitalmichael.smds.springmultidatasources.augment.dto;

import com.digitalmichael.smds.springmultidatasources.common.AbstractDao;
import jakarta.persistence.*;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.annotation.Profile;
import org.springframework.lang.NonNull;

import java.util.Objects;

@Profile( {"all","augment"} )
@Entity(name = "augment_entries")
public class Entry extends AbstractDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = false, nullable = false)
    private Long logDate;

    @Column(unique = false, nullable = false)
    private String context;

    @Column(unique = false, nullable = false)
    private String entry;

    @Column(unique = false, nullable = true)
    private String note;

    public Entry() {
        logDate  = System.currentTimeMillis();
    }

    public Entry(@NonNull Object context, @NonNull Object data, Object note) {
        super();
        logDate  = System.currentTimeMillis();
        if ( !Strings.isBlank(context.toString() ) )
            this.context = context.toString();
        else this.context = "DEFAULT";
        this.entry = data.toString();
        if ( note != null ) this.note = note.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLogDate() {
        return logDate;
    }

    public void setLogDate(Long logDate) {
        this.logDate = logDate;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
