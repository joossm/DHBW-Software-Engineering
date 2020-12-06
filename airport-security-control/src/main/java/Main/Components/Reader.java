package Main.Components;

import Main.Employee.EmployeeProfileType;
import Main.Employee.IDCard;
import Main.Employee.Pin;
import Main.Utils.AdvancedEncryptionStandard;

public class Reader {

    private static final int PROFILE_TYPE_POSITION = 3;

    public boolean checkCard(IDCard idCard, Pin pin, EmployeeProfileType type) throws Exception {
        return checkCard(idCard, type) && checkPin(idCard, pin);
    }

    public boolean checkCard(IDCard idCard, EmployeeProfileType type) throws Exception {
        return !checkIDCardExpired(idCard) && !idCard.isLocked() &&
                checkProfileType(idCard, type);
    }

    private static String decryptMagnetStripe(String magnetStripe) throws Exception {
        AdvancedEncryptionStandard aes = new AdvancedEncryptionStandard();
        return aes.decrypt(magnetStripe);
    }

    private static boolean checkPin(IDCard card, Pin expected) throws Exception {
        var stripe = decryptMagnetStripe(card.getMagnetStripe());
        var pin = stripe.substring(7, stripe.length() - 3);

        return pin.equals(expected.getPin());
    }

    private static boolean checkIDCardExpired(IDCard card) {
        return card.getValidUntil().toEpochDay() < java.time.LocalDate.now().toEpochDay();
    }

    private static boolean checkProfileType(IDCard card, EmployeeProfileType expected) throws Exception {
        var encrypted = decryptMagnetStripe(card.getMagnetStripe());
        try {
            return extractProfileTypeFromMagnetStripe(encrypted) == expected;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }


    private static EmployeeProfileType extractProfileTypeFromMagnetStripe(final String magnetStripe) {
        String profileType = String.valueOf(magnetStripe.charAt(PROFILE_TYPE_POSITION));
        return EmployeeProfileType.valueOf(profileType);
    }


}
