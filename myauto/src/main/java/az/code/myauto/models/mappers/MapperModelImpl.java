package az.code.myauto.models.mappers;

import az.code.myauto.models.*;
import az.code.myauto.models.dtos.*;
import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

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
    public Listing listingCreationDTOToListing(ListingCreationDTO dto, Listing entity) {
        entity.getAuto().setModel(null);
        entity.getAuto().setMake(null);
        entity.setCity(null);
        entity.getAuto().setEquipments(null);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.map(dto, entity);
        return entity;
    }

    @Override
    public Subscription subscriptionDTOToSubscription(SubscriptionDTO dto, Subscription sub){
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
}
