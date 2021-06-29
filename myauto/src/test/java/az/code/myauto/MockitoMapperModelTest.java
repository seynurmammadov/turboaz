package az.code.myauto;

import az.code.myauto.models.*;
import az.code.myauto.models.dtos.ListingCreationDTO;
import az.code.myauto.models.mappers.MapperModelImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.Arrays;

import static org.junit.Assert.*;

import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class MockitoMapperModelTest {
    @Mock
    ModelMapper modelMapper;
    @InjectMocks
    MapperModelImpl mapperModel;
    @InjectMocks
    ListingCreationDTO listingCreationDTO;
    @InjectMocks
    Listing entity;

    @Test
    public void testUpdateListDTOToList(){
        listingCreationDTO = new ListingCreationDTO();
        listingCreationDTO.setMakeId(1l);
        listingCreationDTO.setModelId(1l);
        listingCreationDTO.setYear(2016);

        entity = new Listing();
        entity.setAuto(new Auto());
        entity.getAuto().setMake(new Make());
        entity.getAuto().getMake().setId(1l);
        entity.getAuto().setModel(new Model());
        entity.getAuto().getModel().setId(1l);
        entity.getAuto().setYear(2016);
        entity.setImages((Arrays.asList(new Image())));

        modelMapper = new ModelMapper();
        mapperModel = new MapperModelImpl(modelMapper);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        Listing listing1 = new Listing();
        listing1.setAuto(new Auto());
        listing1.setImages(Arrays.asList(new Image()));

        assertEquals(mapperModel.updateListDTOToList(listingCreationDTO, listing1), entity);


    }
}
