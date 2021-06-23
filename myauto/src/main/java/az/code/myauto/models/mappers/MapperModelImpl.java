package az.code.myauto.models.mappers;

import az.code.myauto.models.*;
import az.code.myauto.models.dtos.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import java.util.ArrayList;

@Component
public class MapperModelImpl implements MapperModel {
    private final ModelMapper modelMapper;

    public MapperModelImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public <T, Y> T entityToDTO(Y data, Class<T> entityClass) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        T dto = modelMapper.map(data, entityClass);
        return dto;
    }

    @Override
    public Listing updateListDTOToList(ListingCreationDTO dto, Listing entity) {
        entity.getAuto().setModel(null);
        entity.getAuto().setMake(null);
        entity.setCity(null);
        entity.getAuto().setEquipments(null);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.map(dto, entity);
        entity.getImages().get(0).setName(dto.getThumbnailUrl());
        return entity;
    }
    @Override
    public Listing createListDTOToList(ListingCreationDTO dto, Listing entity, UserDTO user) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.map(dto, entity);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        entity.setUser(entityToDTO(user,User.class));
        entity.setImages(new ArrayList<>());
        entity.getImages().add(Image.builder().name(dto.getThumbnailUrl()).listing(entity).build());
        return entity;
    }

    @Override
    public Subscription updateSubDTOToSub(SubscriptionDTO dto, Subscription sub){
        sub.setCity(null);
        sub.setMake(null);
        sub.setModel(null);
        sub.setEquipments(null);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.map(dto, sub);
        sub.setCreatedAt(LocalDateTime.now());
        return sub;
    }

    @Override
    public Subscription createSubDTOToSub(SubscriptionDTO dto, Subscription entity, UserDTO user) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.map(dto, entity);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUser(entityToDTO(user,User.class));
        return entity;
    }
}
