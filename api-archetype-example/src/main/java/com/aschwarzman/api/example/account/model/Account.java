package com.aschwarzman.api.example.account.model;

import com.aschwarzman.api.infrastructure.model.Element;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class Account extends Element<Long> {

	private String accountNumber;
	private BigDecimal balance;
	private OffsetDateTime openningDate;

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public OffsetDateTime getOpenningDate() {
		return openningDate;
	}

	public void setOpenningDate(OffsetDateTime openningDate) {
		this.openningDate = openningDate;
	}

}
