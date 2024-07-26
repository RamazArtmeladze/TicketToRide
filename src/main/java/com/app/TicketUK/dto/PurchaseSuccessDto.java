package com.app.TicketUK.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PurchaseSuccessDto implements TicketPurchase {
    private String result;
    private int change;
    private String currency;
}
