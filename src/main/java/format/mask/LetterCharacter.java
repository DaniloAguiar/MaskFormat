package format.mask;

public class LetterCharacter extends _MaskCharacter {

    public LetterCharacter(char value) {
        super(value);
    }

    public boolean accept(char value) {
        return Character.isLetter(value);
    }

}