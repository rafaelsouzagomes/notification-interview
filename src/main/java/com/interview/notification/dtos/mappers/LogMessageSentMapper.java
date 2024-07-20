package com.interview.notification.dtos.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.interview.notification.dtos.LogMessageSentDTO;
import com.interview.notification.model.LogMessageSent;

@Mapper
public interface LogMessageSentMapper {
	
    LogMessageSentMapper INSTANCE = Mappers.getMapper(LogMessageSentMapper.class);

    @Mapping(source = "idLogMessageSent", target = "idLogMessageSent")
    @Mapping(source = "user_origin.name", target = "username_origin")
    @Mapping(source = "user_destination.name", target = "username_destination")
    @Mapping(source = "category.description", target = "category_name")
    @Mapping(source = "channel.description", target = "channel_name")
    @Mapping(source = "date", target = "date")
    LogMessageSentDTO toDTO(LogMessageSent logMessageSent);

    @Mapping(source = "idLogMessageSent", target = "idLogMessageSent")
    @Mapping(source = "username_origin", target = "user_origin.name")  
    @Mapping(source = "username_destination", target = "user_destination.name")  
    @Mapping(source = "category_name", target = "category.description")  
    @Mapping(source = "channel_name", target = "channel.description")
    @Mapping(source = "date", target = "date")
    LogMessageSent toEntity(LogMessageSentDTO logMessageSentDTO);
    
    @Mapping(source = "idLogMessageSent", target = "idLogMessageSent")
    @Mapping(source = "user_origin.name", target = "username_origin")
    @Mapping(source = "user_destination.name", target = "username_destination")
    @Mapping(source = "category.description", target = "category_name")
    @Mapping(source = "channel.description", target = "channel_name")
    List<LogMessageSentDTO> toDTOList(List<LogMessageSent> logMessageSentList);
    
    
    List<LogMessageSent> toEntityList(List<LogMessageSentDTO> logMessageSentDTOList);
}