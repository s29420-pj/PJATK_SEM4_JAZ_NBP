package pl.pjatk.marlew.jazs29420nbp.exceptions;

public class GatewayTimeout extends RuntimeException {
    public GatewayTimeout() {
        super("Gateway Timeout Exception");
    }
}