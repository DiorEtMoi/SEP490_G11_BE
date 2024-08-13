package com.restaurent.manager.service.impl;

import com.restaurent.manager.dto.request.dish.DishCategoryRequest;
import com.restaurent.manager.dto.response.DishCategoryResponse;
import com.restaurent.manager.entity.Account;
import com.restaurent.manager.entity.DishCategory;
import com.restaurent.manager.exception.AppException;
import com.restaurent.manager.exception.ErrorCode;
import com.restaurent.manager.mapper.DishCategoryMapper;
import com.restaurent.manager.repository.DishCategoryRepository;
import com.restaurent.manager.service.IAccountService;
import com.restaurent.manager.service.IDishCategoryService;
import com.restaurent.manager.utils.SlugUtils;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@FieldDefaults(makeFinal = true)
@RequiredArgsConstructor
public class DishCategoryService implements IDishCategoryService {
    DishCategoryRepository dishCategoryRepository;
    DishCategoryMapper dishCategoryMapper;
    IAccountService accountService;
    @Override
    public DishCategoryResponse createDishCategory(DishCategoryRequest request) {
        if(dishCategoryRepository.existsByNameAndAccount_Id(request.getName(), request.getAccountId())){
            throw new AppException(ErrorCode.DISH_CATEGORY_EXIST);
        }
        DishCategory dishCategory = dishCategoryMapper.toDishCategory(request);
        dishCategory.setCode(SlugUtils.toSlug(dishCategory.getName()));
        Account account = accountService.findAccountByID(request.getAccountId());
        dishCategory.setAccount(account);
        return dishCategoryMapper.toDishCategoryResponse(
                dishCategoryRepository.save(
                        dishCategory));
    }

    @Override
    public List<DishCategoryResponse> getAllDishCategoryByAccountId(Long accountId) {
        return dishCategoryRepository.findByAccount_Id(accountId).stream().map(dishCategoryMapper::toDishCategoryResponse)
                .toList();
    }

    @Override
    public DishCategory findById(Long id) {
        return dishCategoryRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.NOT_EXIST)
        );
    }

    @Override
    public DishCategory findByCode(String code) {
        return dishCategoryRepository.findByCode(code).orElseThrow(
                () -> new AppException(ErrorCode.INVALID_CODE)
        );
    }

    @Override
    public void deleteCategoryById(Long id) {
        DishCategory category = findById(id);
        dishCategoryRepository.delete(category);
    }

    @Override
    public DishCategoryResponse updateDishCategoryById(DishCategoryRequest request, Long id) {
        DishCategory dishCategory = findById(id);
        dishCategoryMapper.updateDishCategory(dishCategory,request);
        dishCategoryRepository.save(dishCategory);
        return dishCategoryMapper.toDishCategoryResponse(dishCategory);
    }
}
