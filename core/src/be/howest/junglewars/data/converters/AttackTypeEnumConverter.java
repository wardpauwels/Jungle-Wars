package be.howest.junglewars.data.converters;


import be.howest.junglewars.gameobjects.enemy.AttackType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class AttackTypeEnumConverter implements AttributeConverter<AttackType[], String> {

    @Override
    public String convertToDatabaseColumn(AttackType[] attributes) {
        String[] names = new String[attributes.length];

        for(int i = 0; i < attributes.length; i++){
            names[i] = attributes[i].name();
        }

        return String.join(",", (CharSequence[]) names);
    }

    @Override
    public AttackType[] convertToEntityAttribute(String dbData) {
        return AttackType.values();
    }
}