package ru.job4j.ood.lsp;
/**
 * This class is created to demonstrate a violation of LSP.
 * Rule: Invariants must be preserved in the subtype.
 * @author Grossevich Lev
 */
public class LSPExample3 {

    public class Account {
        private double amount;

        public Account(double amount) {
            this.amount = amount;
        }

        public double getAmount() {
            return amount;
        }
    }

    public class Client {
        protected Account account;

        public Client(Account account) {
            validate(account);
            this.account = account;
        }

        protected void validate(Account account) {
            if (account.getAmount() < 0) {
                throw new IllegalArgumentException("Wrong account size!");
            }
        }

        public Account getAccount() {
            return account;
        }

        public void setAccount(Account account) {
            validate(account);
            this.account = account;
        }
    }

    public class VIPClient extends Client {

        public VIPClient(Account account) {
            super(account);
        }

        @Override
        public Account getAccount() {
            return super.getAccount();
        }

        /**
         * The validation is missed in the subtype. Invalid state is possible.
         */
        @Override
        public void setAccount(Account account) {
            this.account = account;
        }
    }
}
