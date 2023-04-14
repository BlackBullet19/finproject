package org.finproject.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date date;

    private long documentNumber;

    private double sum;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private ProgramUser uploadedBy;

    private String comment;

    private String content;

    private String status;

    private String fileName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(long documentNumber) {
        this.documentNumber = documentNumber;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public ProgramUser getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(ProgramUser uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Document() {
        //
    }

    public Document(Date date, long documentNumber, double sum, ProgramUser uploadedBy, String comment, String content, String status, String fileName) {
        this.date = date;
        this.documentNumber = documentNumber;
        this.sum = sum;
        this.uploadedBy = uploadedBy;
        this.comment = comment;
        this.content = content;
        this.status = status;
        this.fileName = fileName;
    }
}
