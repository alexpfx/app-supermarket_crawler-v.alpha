    package br.com.alexpfx.supermarket.crawler.model.exception;

    /**
     * Created by alexandre on 02/01/2016.
     */
    public class InvalidBarCodeException extends Exception {
        private String code;
        private static final String INVALID_EAN = "INVALID EAN CODE";

        public InvalidBarCodeException(String code) {
            super(INVALID_EAN + " "+code);
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }
