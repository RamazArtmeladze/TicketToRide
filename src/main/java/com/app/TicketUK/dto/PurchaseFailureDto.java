package com.app.TicketUK.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object for purchase failure information.
 */
@Getter
@Setter
@AllArgsConstructor
public class PurchaseFailureDto implements TicketPurchase {
    private String result;

    private int lackOf;

    private String currency;
}