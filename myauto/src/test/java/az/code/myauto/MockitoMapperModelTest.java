package az.code.myauto;

import az.code.myauto.models.*;
import az.code.myauto.models.dtos.CarSpecDTO;
import az.code.myauto.models.dtos.ListingCreationDTO;
import az.code.myauto.models.dtos.SubscriptionDTO;
import az.code.myauto.models.dtos.UserDTO;
import az.code.myauto.models.enums.Color;
import az.code.myauto.models.mappers.MapperModelImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;


@ExtendWith(MockitoExtension.class)
public class MockitoMapperModelTest {

    ModelMapper modelMapper;

    MapperModelImpl mapperModel;

    ListingCreationDTO listingCreationDTO;

    Listing entity;
    SubscriptionDTO subscriptionDTO;
    Subscription subscriptionEntity;

    @Before
    public void setup() {
        modelMapper = new ModelMapper();
        mapperModel = new MapperModelImpl(modelMapper);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
    }

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

        Listing listing1 = new Listing();
        listing1.setAuto(new Auto());
        listing1.setImages(Arrays.asList(new Image()));

        assertEquals(mapperModel.updateListDTOToList(listingCreationDTO, listing1), entity);
    }
    @Test
    public void test2UpdateListDTOToList(){
        listingCreationDTO = new ListingCreationDTO();
        listingCreationDTO.setMakeId(1l);
        listingCreationDTO.setModelId(1l);
        listingCreationDTO.setYear(2016);
        CarSpecDTO carSpecDTO1 = new CarSpecDTO(1l, "ABS");
        CarSpecDTO carSpecDTO2 = new CarSpecDTO(2l, "SUNROOF");
        listingCreationDTO.setEquipments(new ArrayList<>(Arrays.asList(carSpecDTO1, carSpecDTO2)));

        entity = new Listing();
        entity.setAuto(new Auto());
        entity.getAuto().setMake(new Make());
        entity.getAuto().getMake().setId(1l);
        entity.getAuto().setModel(new Model());
        entity.getAuto().getModel().setId(1l);
        entity.getAuto().setYear(2016);
        Equipment equipment1 = new Equipment();
        equipment1.setId(1l);
        equipment1.setName("ABS");
        Equipment equipment2 = new Equipment();
        equipment2.setId(2l);
        equipment2.setName("SUNROOF");
        entity.getAuto().setEquipments(new ArrayList<>(Arrays.asList(equipment1, equipment2)));

        entity.setImages((Arrays.asList(new Image())));

        Listing listing1 = new Listing();
        listing1.setAuto(new Auto());
        listing1.setImages(Arrays.asList(new Image()));

        assertEquals(mapperModel.updateListDTOToList(listingCreationDTO, listing1), entity);
    }
    @Test
    public void listingEntityToDTO(){
         Listing listing = new Listing();
        ListingCreationDTO listingCreationDTO2 = mapperModel.entityToDTO(listing, ListingCreationDTO.class);
        assertEquals(listingCreationDTO2.getClass(), ListingCreationDTO.class);
    }

    @Test
    public void subscriptionEntityToDTO(){
        Subscription subscription = new Subscription();
        SubscriptionDTO subscriptionDTO = mapperModel.entityToDTO(subscription, SubscriptionDTO.class);
        assertEquals(subscriptionDTO.getClass(), SubscriptionDTO.class);
    }

    @Test
    public void createSubDTOToSub(){
        subscriptionDTO = new SubscriptionDTO();
        subscriptionEntity = new Subscription();
        UserDTO userDTO = new UserDTO();
        subscriptionDTO.setName("Test123");
        subscriptionDTO.setMakeId(1l);
        subscriptionDTO.setModelId(1l);
        subscriptionDTO.setCityId(1l);
        subscriptionDTO.setCreatedAt(LocalDateTime.now());

        subscriptionEntity.setName("Test123");
        subscriptionEntity.setMake(new Make());
        subscriptionEntity.getMake().setId(1l);
        subscriptionEntity.setModel(new Model());
        subscriptionEntity.getModel().setId(1l);
        subscriptionEntity.setCity(new City());
        subscriptionEntity.getCity().setId(1l);
        subscriptionEntity.setEquipments(null);
        subscriptionEntity.setCreatedAt(LocalDateTime.now());


        subscriptionEntity.setUser(mapperModel.entityToDTO(userDTO,User.class));

        Subscription subscription2 = new Subscription();

        Subscription subscription3 = mapperModel.createSubDTOToSub(subscriptionDTO, subscription2, userDTO);

        subscription3.getUser().setCreatedAt(subscriptionEntity.getUser().getCreatedAt());
        subscription3.setCreatedAt(subscriptionEntity.getCreatedAt());

        assertEquals(subscription3, subscriptionEntity);

    }
    @Test
    public void createSubDTOToSubTest2(){
        subscriptionDTO = new SubscriptionDTO();
        subscriptionEntity = new Subscription();
        UserDTO userDTO = new UserDTO();
        subscriptionDTO.setName("Test123");
        subscriptionDTO.setMakeId(1l);
        subscriptionDTO.setModelId(1l);
        subscriptionDTO.setCityId(1l);
        CarSpecDTO carSpecDTO1 = new CarSpecDTO(1l, "ABS");
        CarSpecDTO carSpecDTO2 = new CarSpecDTO(2l, "SUNROOF");
        subscriptionDTO.setEquipments(new ArrayList<>(Arrays.asList(carSpecDTO1, carSpecDTO2)));
        subscriptionDTO.setColor("SILVER");
        subscriptionDTO.setCreatedAt(LocalDateTime.now());

        subscriptionEntity.setName("Test123");
        subscriptionEntity.setMake(new Make());
        subscriptionEntity.getMake().setId(1l);
        subscriptionEntity.setModel(new Model());
        subscriptionEntity.getModel().setId(1l);
        subscriptionEntity.setCity(new City());
        subscriptionEntity.getCity().setId(1l);
        Equipment equipment1 = new Equipment();
        equipment1.setId(1l);
        equipment1.setName("ABS");
        Equipment equipment2 = new Equipment();
        equipment2.setId(2l);
        equipment2.setName("SUNROOF");
        subscriptionEntity.setEquipments(new ArrayList<>(Arrays.asList(equipment1, equipment2)));
        subscriptionEntity.setColor(Color.SILVER);
        subscriptionEntity.setCreatedAt(LocalDateTime.now());

        subscriptionEntity.setUser(mapperModel.entityToDTO(userDTO,User.class));

        Subscription subscription2 = new Subscription();

        Subscription subscription3 = mapperModel.createSubDTOToSub(subscriptionDTO, subscription2, userDTO);

        subscription3.getUser().setCreatedAt(subscriptionEntity.getUser().getCreatedAt());
        subscription3.setCreatedAt(subscriptionEntity.getCreatedAt());

        assertEquals(subscription3, subscriptionEntity);

    }
}
