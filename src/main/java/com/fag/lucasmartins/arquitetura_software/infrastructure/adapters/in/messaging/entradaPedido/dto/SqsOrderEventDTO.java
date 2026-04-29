package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.entradaPedido.dto;

import java.time.OffsetDateTime;
import java.util.List;

public class SqsOrderEventDTO {

    private String zipCode;
    private Integer customerId;
    private List<SqsOrderItemDTO> orderItems;
    private String origin;
    private OffsetDateTime occurredAt;

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public List<SqsOrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<SqsOrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public OffsetDateTime getOccurredAt() {
        return occurredAt;
    }

    public void setOccurredAt(OffsetDateTime occurredAt) {
        this.occurredAt = occurredAt;
    }

    public static class SqsOrderItemDTO {

        private Integer sku;
        private Integer amount;

        public Integer getSku() {
            return sku;
        }

        public void setSku(Integer sku) {
            this.sku = sku;
        }

        public Integer getAmount() {
            return amount;
        }

        public void setAmount(Integer amount) {
            this.amount = amount;
        }
    }
}