package com.example.ks_internship.app.api;

import android.net.Uri;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class UriDeserializer implements JsonDeserializer<Uri> {

    @Override
    public Uri deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String uriString=  json.getAsString();
        Uri uri = Uri.parse(uriString);
        return uri;
    }
}
