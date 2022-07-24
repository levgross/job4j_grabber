package ru.job4j.ood.lsp;

/**
 * This class is created to demonstrate a violation of LSP.
 * Rule: Preconditions cannot be strengthened in the subtype.
 * @author Grossevich Lev
 */
public class LSPExample1 {

    public class SalesManager {
        protected int price;

        public SalesManager(int price) {
            this.price = price;
        }

        public void saleByPrice(int amount) {
            if (amount < 1000) {
                throw new IllegalArgumentException("Amount is low for the price!");
            }
            /**
             * some logic
             */
        }
    }

    public class KeyAccountManager extends SalesManager {

        public KeyAccountManager(int price) {
            super(price);
        }

        /**
         * Precondition is strengthened in the subtype.
         */
        @Override
        public void saleByPrice(int amount) {
            if (amount < 1000) {
                throw new IllegalArgumentException("Amount is low for the price!");
            }
            if (amount >= 3000) {
                throw new IllegalArgumentException("For this amount the other price should be used!");
            }
            /**
             * some logic
             */
        }
    }
}
