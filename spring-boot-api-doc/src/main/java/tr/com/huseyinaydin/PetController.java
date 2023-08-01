package tr.com.huseyinaydin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//بسم الله الرحمن الرحيم

/**
 * 
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 * 
 */

@RestController
@RequestMapping("/pet")
@Api(value = "My Pet API documentation")
public class PetController {

	private List<Pet> petList = new ArrayList<>();

	@PostConstruct
	public void init() {
		petList.add(new Pet(1, "Test Pet", new Date()));
	}

	@PostMapping
	@ApiOperation(value = "New Pet object add method", notes = "Attention this method for used")
	public ResponseEntity<Pet> ekle(@RequestBody @ApiParam(value = "animal") Pet pet) {
		petList.add(pet);
		return ResponseEntity.ok(pet);
	}

	@GetMapping
	@ApiOperation(value = "Pet list method", notes = "This method get all")
	public ResponseEntity<List<Pet>> tumunuListele() {
		return ResponseEntity.ok(petList);
	}
}