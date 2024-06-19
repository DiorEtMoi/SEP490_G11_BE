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
}
