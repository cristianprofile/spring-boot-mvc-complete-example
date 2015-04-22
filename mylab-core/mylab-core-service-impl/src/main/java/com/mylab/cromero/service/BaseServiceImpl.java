package com.mylab.cromero.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mylab.cromero.domain.Base;
import com.mylab.cromero.dto.BaseRequest;
import com.mylab.cromero.dto.BaseResponse;
import com.mylab.cromero.exception.BaseNotFoundException;
import com.mylab.cromero.repository.BaseRepository;
import com.mylab.cromero.service.mapper.LayerMapping;

/**
 * <h1>Base Service Implement!</h1> Bussiness Service example using repository
 * access.
 * <p>
 * <b>Base</b> Access to Base Pizza Example model object using repositories with
 * spring data jpa
 *
 * @author Cristian Romero Matesanz
 *
 * 
 */

@Service
@Transactional
public class BaseServiceImpl implements BaseService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BaseRepository baseRepository;

	@Override
	public void deleteBase(final BaseRequest base) throws BaseNotFoundException {
		this.logger.debug("Begin operation: deleteBase, request:{} ", base);
		// Optional<Base> findOne = baseRepository.findOne((long) 1);
		List<Base> findByNameContaining = baseRepository.findByName(base
				.getName());
		if (findByNameContaining.isEmpty()) {
			throw new BaseNotFoundException(
					"No se ha encontrado ninguna base con ese nombre");
		}

		baseRepository.delete(findByNameContaining.get(0).getId());

		this.logger.debug("End operation: deleteBase ");
	}

	@Override
	public List<BaseResponse> findAllBases() {
		this.logger.debug("Begin operation: findAllBases ");

		List<Base> findAll = baseRepository.findAll();
		
		this.logger.debug("findAllBases finded ");
		
		List<BaseResponse> listBases = findAll
				.stream()
				.map(LayerMapping
						.getBaseToBaseResponseMapperLambdaFunction())
				.collect(Collectors.toList());

		this.logger.debug("End operation: findAllBases {} ", listBases);
		return listBases;
	}

	@Override
	public void saveBase(final BaseRequest base) {
		this.logger.debug("Begin operation: save request:{} ", base);
		Base baseAlmacenar = new Base();
		baseAlmacenar.setName(base.getName());
		Base baseSave = baseRepository.save(baseAlmacenar);
		base.setId(baseSave.getId());
		this.logger.debug("End operation: save request:{} ", base);
	}

	@Override
	public BaseResponse getBase(Long id) throws BaseNotFoundException {
		this.logger.debug("Begin operation: searching base wit id :{} ", id);
		Base base = baseRepository.findOne(id);
		if (base != null) {
			BaseResponse baseResponse = LayerMapping.getBaseToBaseResponseMapperLambdaFunction().apply(base);
			return baseResponse;
		} else {
			throw new BaseNotFoundException();
		}

	}

	@Override
	public void deleteBase(Long id) throws BaseNotFoundException {
		this.logger.debug("Begin operation: deleteBase, request id:{} ", id);
		try {
			baseRepository.delete(id);
		} catch (EmptyResultDataAccessException e) {
			throw new BaseNotFoundException();

		}
		this.logger.debug("End operation: deleteBase by id ");
	}

	@Override
	public void updateBase(final BaseRequest baseUpdate) {
		this.logger.debug("Begin operation: update request:{} ", baseUpdate);
		Base base = baseRepository.findOne(baseUpdate.getId());
		if (base == null) {
			throw new BaseNotFoundException();
		}
		base.setName(baseUpdate.getName());
		this.logger.debug("End operation: update request:{} ", base);
	}

	@Override
	public List<BaseResponse> findAllBasesSorted() {
		this.logger.debug("Begin operation: findAllBasesSorted ");

		ArrayList<Order> arrayList = new ArrayList<Order>();
		arrayList.add(new Order(Direction.ASC, "name"));
		arrayList.add(new Order(Direction.ASC, "version"));

		List<Base> findAll = baseRepository.findAll(new Sort(arrayList));

		List<BaseResponse> listBases = findAll
				.stream()
				.map(LayerMapping
						.getBaseToBaseResponseMapperLambdaFunction())
				.collect(Collectors.toList());

		this.logger.debug("End operation: findAllBasesSorted {} ", listBases);
		return listBases;
	}

	@Override
	public List<BaseResponse> findAllBasesPagination(int pageIndex) {
		this.logger.debug("Begin operation: findAllBasesPagination ");

		PageRequest pageRequest = new PageRequest(pageIndex, 2);

		Page<Base> page = baseRepository.findAll(pageRequest);

		List<BaseResponse> listBases = page
				.getContent()
				.stream()
				.map(LayerMapping
						.getBaseToBaseResponseMapperLambdaFunction())
				.collect(Collectors.toList());

		this.logger.debug("End operation: findAllBasesPagination {} ",
				listBases);
		return listBases;
	}

	@Override
	public List<BaseResponse> findAllBasesPaginationAndSorted(int pageIndex) {
		this.logger.debug("Begin operation: findAllBasesPagination ");

		ArrayList<Order> arrayList = new ArrayList<Order>();
		arrayList.add(new Order(Direction.ASC, "name"));
		arrayList.add(new Order(Direction.ASC, "version"));

		PageRequest pageRequest = new PageRequest(pageIndex, 2, new Sort(
				arrayList));

		Page<Base> page = baseRepository.findAll(pageRequest);

		List<BaseResponse> listBases = page
				.getContent()
				.stream()
				.map(LayerMapping
						.getBaseToBaseResponseMapperLambdaFunction())
				.collect(Collectors.toList());

		this.logger.debug("End operation: findAllBasesPagination {} ",
				listBases);
		return listBases;
	}

	@Override
	public Optional<BaseResponse> findById(Long id) {
		this.logger.debug("Begin operation: findById With optional return object value ");
		Optional<Base> base = baseRepository.findById(id);
		Optional<BaseResponse> baseResponse = Optional.empty();
		if (base.isPresent()) {
			baseResponse = Optional.of(LayerMapping.getBaseToBaseResponseMapperLambdaFunction().apply((base.get())));
		}
		this.logger.debug("End operation: findById With optional return object value{} ",baseResponse);
		return baseResponse;
	}

}
