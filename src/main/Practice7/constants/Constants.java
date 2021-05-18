package main.Practice7.constants;

public final class Constants {
    private Constants() {
        throw new UnsupportedOperationException();
    }
    public static final String FEATURE_TURN_VALIDATION_ON = "http://xml.org/sax/features/validation";
    public static final String FEATURE_TURN_SCHEMA_VALIDATION_ON = "http://apache.org/xml/features/validation/schema";
    public static final String DISABLE_EXTERNAL_1 = "http://apache.org/xml/features/disallow-doctype-decl";
    public static final String DISABLE_EXTERNAL_2 = "http://xml.org/sax/features/external-general-entities";
}
