package com.app.TicketUK.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PurchaseFailure implements TicketPurchase {
    private String result;
    private int lackOf;
    private String currency;
}