package com.smartmetro.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class MetroCardTO{
    int cardId;
    double balance;
    String cardNumber;
    String status;
    Date issueDate;
}
