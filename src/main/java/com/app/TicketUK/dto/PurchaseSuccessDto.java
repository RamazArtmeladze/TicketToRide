package com.app.TicketUK.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object for purchase success information.
 */
@Getter
@Setter
@AllArgsConstructor
public class PurchaseSuccessDto implements TicketPurchase {
    private String result;

    private int change;

    private String currency;
}
