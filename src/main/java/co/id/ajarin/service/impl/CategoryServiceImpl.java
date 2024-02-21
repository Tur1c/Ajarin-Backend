package co.id.ajarin.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.ajarin.entity.CategoryEntity;
import co.id.ajarin.mapper.CategoryMapper;
import co.id.ajarin.model.category.CategoryModel;
import co.id.ajarin.repository.CategoryRepository;
import co.id.ajarin.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryModel.Category> getAllCategory() {
        List<CategoryEntity> categories = categoryRepository.findAll();
        return categories.stream().map((category) -> CategoryMapper.mapToCategoryModel(category)).collect(Collectors.toList());
    }

    
    
}
