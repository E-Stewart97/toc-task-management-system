package com.se_devops.toc_task_management_system.model.converter;

import com.se_devops.toc_task_management_system.model.enums.UserRole;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class UserRoleConverter implements AttributeConverter<UserRole, String> {

    @Override
    public String convertToDatabaseColumn(UserRole userRole) {
        if (userRole == null) {
            return null;
        }
        
        // Map enum values to database values
        return switch (userRole) {
            case ADMIN -> "Admin";
            case PROJECT_MANAGER, TEAM_LEAD, DEVELOPER, TESTER, VIEWER -> "Regular";
        };
    }

    @Override
    public UserRole convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        
        // Map database values to enum values
        return switch (dbData) {
            case "Admin" -> UserRole.ADMIN;
            case "Regular" ->
                // Default to DEVELOPER for Regular users
                // This could be refined based on business requirements
                    UserRole.DEVELOPER;
            default -> throw new IllegalArgumentException("Unknown database value: " + dbData);
        };
    }
}