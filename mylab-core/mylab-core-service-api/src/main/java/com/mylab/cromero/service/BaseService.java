package com.mylab.cromero.service;

import com.mylab.cromero.repository.dto.BaseRequest;
import com.mylab.cromero.repository.dto.BaseResponse;
import com.mylab.cromero.repository.exception.BaseNotFoundException;

import java.util.List;
import java.util.Optional;

/**
 * <h1>BaseService</h1>
 * BaseService interface definition
 * <p>
 * <b>BaseService</b> definition of methods of interface
 * for sevice layer
 *
 * @author Cristian Romero Matesanz
 */
public interface BaseService {

    /**
     * delete a base of pizza from repository
     *
     * @param base base to delete
     * @throws BaseNotFoundException
     */
    void deleteBase(final BaseRequest base) throws BaseNotFoundException;

    /**
     * list all base of pizza from repository
     *
     * @return
     */
    List<BaseResponse> findAllBases();

    /**
     * save a base of pizza from repository
     *
     * @param base base to save
     */
    void saveBase(final BaseRequest base);

    /**
     * get a base of pizza from repository
     *
     * @param id base to load
     * @return
     */
    BaseResponse getBase(final Long id) throws BaseNotFoundException;

    /**
     * delete base by id value
     *
     * @param id
     * @throws BaseNotFoundException
     */
    void deleteBase(Long id) throws BaseNotFoundException;

    /**
     * update base of pizza
     *
     * @param baseUpdate
     */
    void updateBase(BaseRequest baseUpdate);

    /**
     * List of sorted Bases
     *
     * @return
     */
    List<BaseResponse> findAllBasesSorted();

    /**
     * List of Bases with pagination
     *
     * @return
     */
    List<BaseResponse> findAllBasesPagination(int pageIndex);

    /**
     * List of Bases with pagination and sort
     *
     * @param pageIndex
     * @return
     */
    List<BaseResponse> findAllBasesPaginationAndSorted(int pageIndex);


    Optional<BaseResponse> findById(Long id);

}
