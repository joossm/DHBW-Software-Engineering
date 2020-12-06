package Main.Components;

import Main.Employee.EmployeeProfileType;
import Main.Employee.IDCard;
import Main.Employee.Pin;
import Main.Utils.AES;

public class Reader {

    private static final int PROFILE_TYPE_POSITION = 3;

    public boolean checkInspectorCard(IDCard idCard, Pin pin) {
        return checkCard(idCard,pin,EmployeeProfileType.I);
    }

    public boolean checkCard(IDCard idCard, Pin pin, EmployeeProfileType type) {
        return checkCard(idCard,type) && checkPin(idCard, pin);
    }

    public boolean checkCard(IDCard idCard, EmployeeProfileType type) {
        return !checkIDCardExpired(idCard) && !idCard.isLocked() &&
                checkProfileType(idCard,type);
    }

    private static String decryptMagnetStripe(String magnetStripe) {
        AES aes = new AES();
        return aes.decrypt(magnetStripe);
    }

    private static boolean checkPin(IDCard card, Pin expected)
    {
        var stripe = decryptMagnetStripe(card.getMagnetStripe());
        var pin = stripe.substring(7,stripe.length() - 3);

        if(pin.equals(expected.getPin()))
        {
            //card.resetWrongPinCounter();
            return true;
        }
        else
        {
            //card.wrongPinEntered();
            return false;
        }
    }

    private static boolean checkIDCardExpired(IDCard card)
    {
        return card.getValidUntil().toEpochDay() < java.time.LocalDate.now().toEpochDay();
    }

    private static boolean checkProfileType(IDCard card, EmployeeProfileType expected)
    {
        var encrypted = decryptMagnetStripe(card.getMagnetStripe());
        try {
            var ret = extractProfileTypeFromMagnetStripe(encrypted) == expected;
            return ret;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }


    private static EmployeeProfileType extractProfileTypeFromMagnetStripe(final String magnetStripe) {
        String profileType = String.valueOf(magnetStripe.charAt(PROFILE_TYPE_POSITION));
        return EmployeeProfileType.valueOf(profileType);
    }


}
