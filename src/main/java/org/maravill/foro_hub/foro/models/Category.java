package org.maravill.foro_hub.foro.models;

import org.maravill.foro_hub.foro.exception.ForoInvalidDataException;

public enum Category {
    GENERAL,
    PROGRAMMING,
    DATABASES,
    DEVOPS,
    SECURITY,
    FRONTEND,
    BACKEND,
    MOBILE,
    CLOUD,
    TOOLS,
    ANNOUNCEMENTS,
    OFF_TOPIC;

    public static Category getCategory(String category){
        if (category == null){
            throw new ForoInvalidDataException("Category cannot be null");
        }
        try{
            return Category.valueOf(category.toUpperCase());
        }catch (Exception _){
            throw new ForoInvalidDataException("Invalid Category: "+category);
        }
    }
}
