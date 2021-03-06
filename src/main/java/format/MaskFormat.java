package format;

import format.mask._MaskCharacter;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;

public class MaskFormat extends Format {


    private final List<_MaskCharacter> semanticMask = new ArrayList<>();


    public MaskFormat(String mask) {
        buildSemanticMask(mask);
        updateSemanticMask("");
    }


    private void buildSemanticMask(String mask) {
        char[] newMask = mask.toCharArray();
        int i = 0;
        int length = newMask.length;

        semanticMask.clear();

        MaskFactory factory = new MaskFactory();
        while (i < length) {
            char maskValue = newMask[i];

            if (maskValue == MaskFactory.MASK_ESCAPE) {
                semanticMask.add(factory.createMask(maskValue, newMask[i + 1]));
                i++;
            } else {
                char value = MaskFactory.isLiteral(maskValue) ? maskValue : '\u0000';
                semanticMask.add(factory.createMask(maskValue, value));
            }

            i++;
        }
    }

    private void updateSemanticMask(String newText) {
        resetSemanticMask();
        StringBuilder inputText = new StringBuilder(newText);

        int maskPosition = 0;
        int textLength = newText.length();
        int textPosition = 0;

        while (textPosition < textLength) {
            if (maskPosition < semanticMask.size()) {
                _MaskCharacter maskCharacter = semanticMask.get(maskPosition);

                if (!maskCharacter.isLiteral()) {
                    char ch = inputText.charAt(textPosition);

                    if (maskCharacter.accept(ch)) {
                        maskCharacter.setValue(ch);
                        maskPosition++;
                    }

                    textPosition++;
                } else {
                    maskPosition++;
                }
            } else {
                break;
            }
        }
    }

    private void resetSemanticMask() {
        semanticMask.forEach(_MaskCharacter::setNull);
    }


    private StringBuffer formatToString(StringBuffer value) {
        for (int i = 0; i < semanticMask.size(); i++) {
            _MaskCharacter characterAtPrevious = i > 0 ? semanticMask.get(i - 1) : null;
            _MaskCharacter characterAt = semanticMask.get(i);
            _MaskCharacter characterAtNext = i + 1 < semanticMask.size() ? semanticMask.get(i + 1) : null;

            if ((characterAtPrevious != null && characterAtPrevious.isNull() && characterAt.isLiteral()) ||
                    (characterAtNext != null && characterAtNext.isNull() && characterAt.isLiteral())) break;

            if (!characterAt.isNull()) value.append(characterAt.getValue());
        }
        return value;
    }

    private String parseToPlain() {
        StringBuilder value = new StringBuilder();

        for (_MaskCharacter characterAt : semanticMask) {
            if (!characterAt.isLiteral() && !characterAt.isNull()) {
                value.append(characterAt.getValue());
            }
        }

        return value.toString();
    }


    private StringBuffer format(String text, StringBuffer toAppendTo) {
        updateSemanticMask(text);
        return formatToString(toAppendTo);
    }

    private String parse(String text) {
        updateSemanticMask(text);
        return parseToPlain();
    }


    @Override
    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
        String text = (String) obj;


        return format(text, toAppendTo);
    }

    @Override
    public String parseObject(String source, ParsePosition pos) {
        pos.setIndex(source.length());
        return parse(source);
    }
}
