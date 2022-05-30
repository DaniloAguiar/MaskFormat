package format;

import format.mask.*;

public class MaskFactory {

    public static final char MASK_ANYTHING = '*';
    public static final char MASK_CHAR_OR_NUM = 'A';
    public static final char MASK_CHARACTER = '?';
    public static final char MASK_ESCAPE = '\'';
    public static final char MASK_HEXADECIMAL = 'H';
    public static final char MASK_NUMBER = '#';
    public static final char MASK_UPPER_CHARACTER = 'U';
    public static final char MASK_LOWER_CHARACTER = 'L';

    _MaskCharacter createMask(char mask, char value) {
        switch (mask) {
            case MASK_ANYTHING:
                return new AnythingCharacter(value);
            case MASK_CHAR_OR_NUM:
                return new AlphaNumericCharacter(value);
            case MASK_CHARACTER:
                return new LetterCharacter(value);
            case MASK_HEXADECIMAL:
                return new HexCharacter(value);
            case MASK_NUMBER:
                return new NumericCharacter(value);
            case MASK_UPPER_CHARACTER:
                return new UpperCaseCharacter(value);
            case MASK_LOWER_CHARACTER:
                return new LowerCaseCharacter(value);
            default:
                return new LiteralCharacter(value);
        }
    }

    static boolean isLiteral(char c) {
        return (c != MaskFactory.MASK_ANYTHING &&
                c != MaskFactory.MASK_CHAR_OR_NUM &&
                c != MaskFactory.MASK_CHARACTER &&
                c != MaskFactory.MASK_ESCAPE &&
                c != MaskFactory.MASK_HEXADECIMAL &&
                c != MaskFactory.MASK_NUMBER &&
                c != MaskFactory.MASK_UPPER_CHARACTER &&
                c != MaskFactory.MASK_LOWER_CHARACTER);
    }

}
