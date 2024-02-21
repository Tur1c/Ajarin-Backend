package co.id.ajarin.mapper;

import co.id.ajarin.entity.CategoryEntity;
import co.id.ajarin.model.category.CategoryModel;

public class CategoryMapper {
    
    public static CategoryModel.Category mapToCategoryModel (CategoryEntity categoryEntity) {
        return new CategoryModel.Category(categoryEntity.getCategory_name());
    }
}
