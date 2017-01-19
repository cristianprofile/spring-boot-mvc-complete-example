package com.mylab.cromero.service;

import com.mylab.cromero.repository.BaseRepository;
import com.mylab.cromero.repository.domain.Base;
import com.mylab.cromero.repository.dto.BaseRequest;
import com.mylab.cromero.repository.dto.BaseResponse;
import com.mylab.cromero.repository.exception.BaseNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BaseServiceImplTest {

    @InjectMocks
    private BaseServiceImpl baseService;

    @Mock
    private BaseRepository baseRepository;

    @Test(expected = BaseNotFoundException.class)
    public void testDeleteBaseNotExist() {

        // create empty repository list to return when i call to findbyname
        // method
        List<Base> listaBasesRepositorio = new ArrayList<Base>();
        when(this.baseRepository.findByName("margarita")).thenReturn(listaBasesRepositorio);
        BaseRequest base = new BaseRequest();
        base.setName("margarita");
        baseService.deleteBase(base);

    }

    @Test
    public void testDeleteBaseOk() {
        List<Base> listaBasesRepositorio = new ArrayList<Base>();
        Base base2 = new Base();
        base2.setId(33L);
        base2.setName("margarita");
        listaBasesRepositorio.add(base2);
        when(this.baseRepository.findByName("margarita")).thenReturn(
                listaBasesRepositorio);
        when(this.baseRepository.save(any(Base.class))).thenReturn(
                base2);
        BaseRequest base = new BaseRequest();
        base.setName("margarita");
        baseService.saveBase(base);
        // save must be called once time
        verify(this.baseRepository, times(1)).save(any(Base.class));
        baseService.deleteBase(base);
        // delete must be called once time with base 2 id
        verify(this.baseRepository, times(1)).delete(base2.getId());
    }

    @Transactional
    @Test
    public void testFindAllSortedOk() {


        List<Base> listaBasesRepositorio = new ArrayList<Base>();
        Base baseAtun = new Base();
        baseAtun.setName("atun");
        listaBasesRepositorio.add(baseAtun);

        Base baseMargarita = new Base();
        baseMargarita.setName("margarita");
        listaBasesRepositorio.add(baseMargarita);

        Base basePinya = new Base();
        basePinya.setName("piña");
        listaBasesRepositorio.add(basePinya);

        when(this.baseRepository.findAll(any(Sort.class))).thenReturn(
                listaBasesRepositorio);

        List<BaseResponse> findAllBasesSorted = baseService
                .findAllBasesSorted();

        List<String> collectPizzas = findAllBasesSorted.stream().map(BaseResponse::getName).collect(Collectors.toList());

        assertThat(findAllBasesSorted, hasSize(3));
        assertThat(collectPizzas, contains("atun", "margarita", "piña"));

        verify(this.baseRepository, times(1)).findAll(any(Sort.class));

    }

}
