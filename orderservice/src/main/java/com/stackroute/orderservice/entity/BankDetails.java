package com.stackroute.orderservice.entity;

public class BankDetails {
        private String bankName;
        private String bankAccountNumber;
        private String ifscCode;

        public BankDetails() {
            super();
        }

        public BankDetails(String bankName, String bankAccountNumber, String ifscCode) {
            super();
            this.bankName = bankName;
            this.bankAccountNumber = bankAccountNumber;
            this.ifscCode = ifscCode;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getBankAccountNumber() {
            return bankAccountNumber;
        }

        public void setBankAccountNumber(String bankAccountNumber) {
            this.bankAccountNumber = bankAccountNumber;
        }

        public String getIfscCode() {
            return ifscCode;
        }

        public void setIfscCode(String ifscCode) {
            this.ifscCode = ifscCode;
        }

}
