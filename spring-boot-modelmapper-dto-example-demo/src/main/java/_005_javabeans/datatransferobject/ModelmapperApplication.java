package _005_javabeans.datatransferobject;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import _005_javabeans.datatransferobject.dto.PersonDTO;
import _005_javabeans.datatransferobject.model.Person;

/**
*
@author Huseyin_Aydin
@since 1994
@category Spring Boot Examples
*
**/

public class ModelmapperApplication {

	public static void main(String[] args) {
		simpleModelMappingDemo();
		System.out.println("-----------------");
		explicitModelMappingDemo();
	}

	private static void simpleModelMappingDemo() {
		Person sourceUser = new Person(1, "Hüseyin Aydın", "truncatecreate@gmail.com", "ShootShootAbecigostikGönderBeatleriDiCeySibo5191", "Niğde/Kurdunus Diyarı Canım");
		PersonDTO targetUserDTO = new PersonDTO();
		ModelMapper modelMapper = new ModelMapper();

		modelMapper.map(sourceUser, targetUserDTO);

		System.out.println(targetUserDTO.getName());
		System.out.println(targetUserDTO.getEmailAddress());
		System.out.println(targetUserDTO.getMobileNumber());
		System.out.println(targetUserDTO.getUserCity());
	}

	private static void explicitModelMappingDemo() {
		Person sourceUser = new Person(1, "Hüseyin Aydın", "truncatecreate@gmail.com", "ShootShootAbecigostikGönderBeatleriDiCeySibo5191", "Niğde/Kurdunus Diyarı Canım");
		PersonDTO targetUserDTO = new PersonDTO();
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.addMappings(new PropertyMap<Person, PersonDTO>() {
			protected void configure() {
				map().setUserCity(source.getCity());
			}
		});
		
		modelMapper.map(sourceUser, targetUserDTO);

		System.out.println(targetUserDTO.getName());
		System.out.println(targetUserDTO.getEmailAddress());
		System.out.println(targetUserDTO.getMobileNumber());
		System.out.println(targetUserDTO.getUserCity());
	}
}