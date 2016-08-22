package edu.ncsu.csc.itrust.webutils;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("localDateTimeConverter")
public class LocalDateTimeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        try {
            return LocalDateTime.parse(value, DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a"));
        } catch (IllegalArgumentException | DateTimeException e) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Message"), e);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }

        if (!(value instanceof LocalDateTime)) {
            throw new ConverterException("Message");
        }

        return DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm:ss a z").format((LocalDateTime) value);
        // According to a time zone of a specific user.
    }
}