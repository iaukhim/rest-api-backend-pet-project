package com.unknown.supportapp.dto.ticket;

import java.util.Objects;

public class TicketDto {

    private Long id;

    private Long starterId;

    private Long managerId;

    private Long productId;

    private String theme;

    private String text;

    private boolean status;

    public TicketDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStarterId() {
        return starterId;
    }

    public void setStarterId(Long starterId) {
        this.starterId = starterId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TicketDto{" +
                "id=" + id +
                ", starterId=" + starterId +
                ", managerId=" + managerId +
                ", productId=" + productId +
                ", theme='" + theme + '\'' +
                ", text='" + text + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketDto ticketDto = (TicketDto) o;
        return status == ticketDto.status && Objects.equals(id, ticketDto.id) && Objects.equals(starterId, ticketDto.starterId) && Objects.equals(managerId, ticketDto.managerId) && Objects.equals(productId, ticketDto.productId) && Objects.equals(theme, ticketDto.theme) && Objects.equals(text, ticketDto.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, starterId, managerId, productId, theme, text, status);
    }
}
