package com.mylab.cromero.repository;

import com.mylab.cromero.repository.domain.Especialidad;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Iterator;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class EspecialidadIntegrationTest {

    @Autowired
    EspecialidadDataOnDemand dod;
    @Autowired
    EspecialidadRepository especialidadRepository;

    @Test
    public void testMarkerMethod() {
    }

    @Test
    public void testCount() {
        Assert.assertNotNull(
                "Data on demand for 'Especialidad' failed to initialize correctly",
                dod.getRandomEspecialidad());
        long count = especialidadRepository.count();
        Assert.assertTrue(
                "Counter for 'Especialidad' incorrectly reported there were no entries",
                count > 0);
    }

    @Test
    public void testFind() {
        Especialidad obj = dod.getRandomEspecialidad();
        Assert.assertNotNull(
                "Data on demand for 'Especialidad' failed to initialize correctly",
                obj);
        Long id = obj.getId();
        Assert.assertNotNull(
                "Data on demand for 'Especialidad' failed to provide an identifier",
                id);
        obj = especialidadRepository.findOne(id);
        Assert.assertNotNull(
                "Find method for 'Especialidad' illegally returned null for id '"
                        + id + "'", obj);
        Assert.assertEquals(
                "Find method for 'Especialidad' returned the incorrect identifier",
                id, obj.getId());
    }

    @Test
    public void testFindAll() {
        Assert.assertNotNull(
                "Data on demand for 'Especialidad' failed to initialize correctly",
                dod.getRandomEspecialidad());
        long count = especialidadRepository.count();
        Assert.assertTrue(
                "Too expensive to perform a find all test for 'Especialidad', as there are "
                        + count
                        + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test",
                count < 250);
        List<Especialidad> result = especialidadRepository.findAll();
        Assert.assertNotNull(
                "Find all method for 'Especialidad' illegally returned null",
                result);
        Assert.assertTrue(
                "Find all method for 'Especialidad' failed to return any data",
                result.size() > 0);
    }

    @Test
    public void testFindEntries() {
        Assert.assertNotNull(
                "Data on demand for 'Especialidad' failed to initialize correctly",
                dod.getRandomEspecialidad());
        long count = especialidadRepository.count();
        if (count > 20) {
            count = 20;
        }
        int firstResult = 0;
        int maxResults = (int) count;
        List<Especialidad> result = especialidadRepository.findAll(
                new org.springframework.data.domain.PageRequest(firstResult
                        / maxResults, maxResults)).getContent();
        Assert.assertNotNull(
                "Find entries method for 'Especialidad' illegally returned null",
                result);
        Assert.assertEquals(
                "Find entries method for 'Especialidad' returned an incorrect number of entries",
                count, result.size());
    }

    @Test
    public void testFlush() {
        Especialidad obj = dod.getRandomEspecialidad();
        Assert.assertNotNull(
                "Data on demand for 'Especialidad' failed to initialize correctly",
                obj);
        Long id = obj.getId();
        Assert.assertNotNull(
                "Data on demand for 'Especialidad' failed to provide an identifier",
                id);
        obj = especialidadRepository.findOne(id);
        Assert.assertNotNull(
                "Find method for 'Especialidad' illegally returned null for id '"
                        + id + "'", obj);
        boolean modified = dod.modifyEspecialidad(obj);
        Integer currentVersion = obj.getVersion();
        especialidadRepository.flush();
        Assert.assertTrue(
                "Version for 'Especialidad' failed to increment on flush directive",
                (currentVersion != null && obj.getVersion() > currentVersion)
                        || !modified);
    }

    @Test
    public void testSaveUpdate() {
        Especialidad obj = dod.getRandomEspecialidad();
        Assert.assertNotNull(
                "Data on demand for 'Especialidad' failed to initialize correctly",
                obj);
        Long id = obj.getId();
        Assert.assertNotNull(
                "Data on demand for 'Especialidad' failed to provide an identifier",
                id);
        obj = especialidadRepository.findOne(id);
        boolean modified = dod.modifyEspecialidad(obj);
        Integer currentVersion = obj.getVersion();
        Especialidad merged = especialidadRepository.save(obj);
        especialidadRepository.flush();
        Assert.assertEquals(
                "Identifier of merged object not the same as identifier of original object",
                merged.getId(), id);
        Assert.assertTrue(
                "Version for 'Especialidad' failed to increment on merge and flush directive",
                (currentVersion != null && obj.getVersion() > currentVersion)
                        || !modified);
    }

    @Test
    public void testSave() {
        Assert.assertNotNull(
                "Data on demand for 'Especialidad' failed to initialize correctly",
                dod.getRandomEspecialidad());
        Especialidad obj = dod.getNewTransientEspecialidad(Integer.MAX_VALUE);
        Assert.assertNotNull(
                "Data on demand for 'Especialidad' failed to provide a new transient entity",
                obj);
        Assert.assertNull("Expected 'Especialidad' identifier to be null",
                obj.getId());
        try {
            especialidadRepository.save(obj);
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e
                    .getConstraintViolations().iterator(); iter.hasNext(); ) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName())
                        .append(".").append(cv.getPropertyPath()).append(": ")
                        .append(cv.getMessage()).append(" (invalid value = ")
                        .append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        especialidadRepository.flush();
        Assert.assertNotNull(
                "Expected 'Especialidad' identifier to no longer be null",
                obj.getId());
    }

    @Test
    public void testDelete() {
        Especialidad obj = dod.getRandomEspecialidad();
        Assert.assertNotNull(
                "Data on demand for 'Especialidad' failed to initialize correctly",
                obj);
        Long id = obj.getId();
        Assert.assertNotNull(
                "Data on demand for 'Especialidad' failed to provide an identifier",
                id);
        obj = especialidadRepository.findOne(id);
        especialidadRepository.delete(obj);
        especialidadRepository.flush();
        Assert.assertNull("Failed to remove 'Especialidad' with identifier '"
                + id + "'", especialidadRepository.findOne(id));
    }
}
