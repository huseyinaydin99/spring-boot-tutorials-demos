package tr.com.huseyinaydin.service.impl;

import tr.com.huseyinaydin.dto.PhotoRequestDto;
import tr.com.huseyinaydin.model.Photo;
import tr.com.huseyinaydin.repository.PhotoRepository;
import tr.com.huseyinaydin.service.PhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Huseyin_Aydin
 * @category Spring Boot examples
 * @since 1994
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class PhotoServiceImpl implements PhotoService {

    private final ModelMapper modelMapper;
    private final PhotoRepository photoRepository;

    @Override
    public void addNewPhoto(PhotoRequestDto photoRequestDto) throws Exception {
        Photo photo = photoRepository.save(modelMapper.map(photoRequestDto, Photo.class));
        if(Objects.isNull(photo.getId())) {
            log.error("Saving new photo was failed!");
            throw new Exception();
        }
    }

    @Override
    public Photo checkById(Long id) {
        Optional<Photo> photo = photoRepository.findFirstById(id);
        return photo.orElse(null);
    }
}
