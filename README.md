This project was created similar the https://github.com/gbfragoso/MaskedTextField

# What's this
It's a mask format for java. Now you can use a simple mask for your string.

# How to install
//todo. add maven project

# How to use

``` java
        MaskFormat maskFormat = new MaskFormat("###.###.###-##");

        System.out.println(maskFormat.format("00000000000"));
        //#result_format: 000.000.000-00

        System.out.println(maskFormat.parse("000.000.000-00"));
        //#result_parse: 00000000000
```

You can bind with a text Field as well

``` java
        StringConverter<String> stringConverter = new StringConverter<String>() {
            @Override
            public String toString(String object) {
                if (object == null) return "";
                return maskFormat.format(object);
            }

            @Override
            public String fromString(String string) {
                if (string == null || string.length() == 0) return null;
                return maskFormat.parse(string);
            }
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(stringConverter);

        TextField textField = new TextField();
        textField.setTextFormatter(textFormatter);
```

if you want bindBidirectional with Property 

``` java
        StringProperty stringProperty = new SimpleStringProperty();
        stringProperty.bindBidirectional(textFormatter.valueProperty());
```