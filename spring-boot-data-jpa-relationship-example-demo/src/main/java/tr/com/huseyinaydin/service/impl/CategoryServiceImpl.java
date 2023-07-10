package tr.com.huseyinaydin.service.impl;

import tr.com.huseyinaydin.dto.CategoryRequestDto;
import tr.com.huseyinaydin.model.Category;
import tr.com.huseyinaydin.repository.CategoryRepository;
import tr.com.huseyinaydin.service.CategoryService;
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
public class CategoryServiceImpl implements CategoryService {

    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public void createNewCategory(CategoryRequestDto categoryRequestDto) throws Exception {
        Category category = categoryRepository.save(modelMapper.map(categoryRequestDto, Category.class));
        if (Objects.isNull(category.getId())) {
            log.error("Saving new category was failed!");
            throw new Exception();
        }
    }

    @Override
    public Category checkById(Long id) {
        Optional<Category> category = categoryRepository.findFirstById(id);
        return category.orElse(null);
    }
}
