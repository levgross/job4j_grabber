package ru.job4j.ood.lsp;

/**
 * This class is created to demonstrate a violation of LSP.
 * Rule: Postconditions cannot be weakened in the subtype.
 * @author Grossevich Lev
 */
public class LSPExample2 {

    public class SalesManager {
        protected int sold;
        public int bonus = 10_000;

        public SalesManager(int sold) {
            this.sold = sold;
        }

        public double countSalary() {
            if (sold < 100) {
                throw new IllegalArgumentException("Manager did not sell enough!");
            }
            if (sold < 500) {
                bonus = 0;
            }
            return sold * 0.03d + bonus;
        }
    }

    public class KeyAccountManager extends SalesManager {

        public KeyAccountManager(int sold) {
            super(sold);
        }

        /**
         * Postcondition is weakened in the subtype.
         */
        @Override
        public double countSalary() {
            return sold * 0.03d + bonus;
        }
    }
}
