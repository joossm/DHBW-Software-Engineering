package Main.Employee;

import Main.Utils.AES;

import java.time.LocalDate;

public class IDCard {

    private final int id;
    private final String magnetStripe;
    private final IDCardType type;
    private final LocalDate validUntil;
    private int wrongPinCounter;

    public IDCard(int id, LocalDate validUntil, String magnetStripe, IDCardType type) {
        this.id = id;
        this.validUntil = validUntil;

        AES aes = new AES();
        this.magnetStripe = aes.encrypt(magnetStripe);
        this.type = type;
    }

    public String getMagnetStripe() {
        return magnetStripe;
    }
    public int getId() {
        return id;
    }
    public LocalDate getValidUntil() {
        return validUntil;
    }
    public boolean isLocked() {
        return wrongPinCounter >= 3;
    }
    public IDCardType getType() {
        return type;
    }
    public void wrongPinEntered()
    {
        wrongPinCounter++;
    }
    public void resetWrongPinCounter()
    {
        wrongPinCounter = 0;
    }
}
