package Main.Employee;

import Main.Utils.AdvancedEncryptionStandard;

import java.time.LocalDate;

public class IDCard {

    private final int id;
    private final String magnetStripe;
    private final LocalDate validUntil;
    private int wrongPinCounter;

    public IDCard(int id, LocalDate validUntil, String magnetStripe, IDCardType type) throws Exception {
        this.id = id;
        this.validUntil = validUntil;

        AdvancedEncryptionStandard advancedEncryptionStandard = new AdvancedEncryptionStandard();
        this.magnetStripe = advancedEncryptionStandard.encrypt(magnetStripe);
    }

    public String getMagnetStripe()
    {
        return magnetStripe;
    }

    public int getId()
    {
        return id;
    }

    public LocalDate getValidUntil()
    {
        return validUntil;
    }

    public boolean isLocked()
    {
        return wrongPinCounter >= 3;
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
