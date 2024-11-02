package lotto.controller;

import java.util.List;

import lotto.model.LottoTicket;
import lotto.validator.InputValidator;
import lotto.validator.LottoTicketValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

	public void run() {
		int purchasePrice = getValidatedPurchasePriceWithRetry();

		List<LottoTicket> tickets = LottoTicketGenerator.generateTickets(purchasePrice);
		int ticketsCount = tickets.size();
		OutputView.displayPurchaseCount(ticketsCount);
	}

	private int getValidatedPurchasePriceWithRetry() {
		while (true) {
			String purchasePriceInput = InputView.inputPurchasePrice();
			try {
				return getValidatedPurchasePrice(purchasePriceInput);
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public int getValidatedPurchasePrice(String purchasePriceInput) {
		InputValidator.validateInteger(purchasePriceInput);
		int parsedPrice = Integer.parseInt(purchasePriceInput);
		LottoTicketValidator.validatePurchasePrice(parsedPrice);
		return parsedPrice;
	}
}
