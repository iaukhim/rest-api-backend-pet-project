package com.unknown.supportapp.dto.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.unknown.supportapp.dto.acccount.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Component
public class CustomAccountSerializer extends StdSerializer<AccountDto> {

    @Autowired
    public CustomAccountSerializer(){
        this(null);
    }

    public CustomAccountSerializer(Class<AccountDto> t) {
        super(t);
    }

    @Override
    public void serialize(AccountDto value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("id", value.getId());
        gen.writeStringField("email", value.getEmail());
        gen.writeEndObject();
    }
}
