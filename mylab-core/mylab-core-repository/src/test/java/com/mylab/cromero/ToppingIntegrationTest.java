package com.mylab.cromero;

import java.util.Iterator;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.mylab.cromero.domain.Topping;
import com.mylab.cromero.repository.ToppingRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestRepositoryConfigIT.class)
@Transactional
public class ToppingIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

    @Autowired
    ToppingDataOnDemand dod;

    @Autowired
    ToppingRepository toppingRepository;

    @Test
    public void testCount() {
        Assert.assertNotNull(
                "Data on demand for 'Topping' failed to initialize correctly",
                dod.getRandomTopping());
        long count = toppingRepository.count();
        Assert.assertTrue(
                "Counter for 'Topping' incorrectly reported there were no entries",
                count > 0);
    }

    @Test
    public void testFind() {
        Topping obj = dod.getRandomTopping();
        Assert.assertNotNull(
                "Data on demand for 'Topping' failed to initialize correctly",
                obj);
        Long id = obj.getId();
        Assert.assertNotNull(
                "Data on demand for 'Topping' failed to provide an identifier",
                id);
        obj = toppingRepository.findOne(id);
        Assert.assertNotNull(
                "Find method for 'Topping' illegally returned null for id '"
                        + id + "'", obj);
        Assert.assertEquals(
                "Find method for 'Topping' returned the incorrect identifier",
                id, obj.getId());
    }

    @Test
    public void testFindAll() {
        Assert.assertNotNull(
                "Data on demand for 'Topping' failed to initialize correctly",
                dod.getRandomTopping());
        long count = toppingRepository.count();
        Assert.assertTrue(
                "Too expensive to perform a find all test for 'Topping', as there are "
                        + count
                        + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test",
                count < 250);
        List<Topping> result = toppingRepository.findAll();
        Assert.assertNotNull(
                "Find all method for 'Topping' illegally returned null", result);
        Assert.assertTrue(
                "Find all method for 'Topping' failed to return any data",
                result.size() > 0);
    }

    @Test
    public void testFindEntries() {
        Assert.assertNotNull(
                "Data on demand for 'Topping' failed to initialize correctly",
                dod.getRandomTopping());
        long count = toppingRepository.count();
        if (count > 20)
            count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Topping> result = toppingRepository.findAll(
                new org.springframework.data.domain.PageRequest(firstResult
                        / maxResults, maxResults)).getContent();
        Assert.assertNotNull(
                "Find entries method for 'Topping' illegally returned null",
                result);
        Assert.assertEquals(
                "Find entries method for 'Topping' returned an incorrect number of entries",
                count, result.size());
    }

    @Test
    public void testFlush() {
        Topping obj = dod.getRandomTopping();
        Assert.assertNotNull(
                "Data on demand for 'Topping' failed to initialize correctly",
                obj);
        Long id = obj.getId();
        Assert.assertNotNull(
                "Data on demand for 'Topping' failed to provide an identifier",
                id);
        obj = toppingRepository.findOne(id);
        Assert.assertNotNull(
                "Find method for 'Topping' illegally returned null for id '"
                        + id + "'", obj);
        boolean modified = dod.modifyTopping(obj);
        Integer currentVersion = obj.getVersion();
        toppingRepository.flush();
        Assert.assertTrue(
                "Version for 'Topping' failed to increment on flush directive",
                (currentVersion != null && obj.getVersion() > currentVersion)
                        || !modified);
    }

    @Test
    public void testSaveUpdate() {
        Topping obj = dod.getRandomTopping();
        Assert.assertNotNull(
                "Data on demand for 'Topping' failed to initialize correctly",
                obj);
        Long id = obj.getId();
        Assert.assertNotNull(
                "Data on demand for 'Topping' failed to provide an identifier",
                id);
        obj = toppingRepository.findOne(id);
        boolean modified = dod.modifyTopping(obj);
        Integer currentVersion = obj.getVersion();
        Topping merged = toppingRepository.save(obj);
        toppingRepository.flush();
        Assert.assertEquals(
                "Identifier of merged object not the same as identifier of original object",
                merged.getId(), id);
        Assert.assertTrue(
                "Version for 'Topping' failed to increment on merge and flush directive",
                (currentVersion != null && obj.getVersion() > currentVersion)
                        || !modified);
    }

    @Test
    public void testSave() {
        Assert.assertNotNull(
                "Data on demand for 'Topping' failed to initialize correctly",
                dod.getRandomTopping());
        Topping obj = dod.getNewTransientTopping(Integer.MAX_VALUE);
        Assert.assertNotNull(
                "Data on demand for 'Topping' failed to provide a new transient entity",
                obj);
        Assert.assertNull("Expected 'Topping' identifier to be null",
                obj.getId());
        try {
            toppingRepository.save(obj);
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e
                    .getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName())
                        .append(".").append(cv.getPropertyPath()).append(": ")
                        .append(cv.getMessage()).append(" (invalid value = ")
                        .append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        toppingRepository.flush();
        Assert.assertNotNull(
                "Expected 'Topping' identifier to no longer be null",
                obj.getId());
    }

    @Test
    public void testDelete() {
        Topping obj = dod.getRandomTopping();
        Assert.assertNotNull(
                "Data on demand for 'Topping' failed to initialize correctly",
                obj);
        Long id = obj.getId();
        Assert.assertNotNull(
                "Data on demand for 'Topping' failed to provide an identifier",
                id);
        obj = toppingRepository.findOne(id);
        toppingRepository.delete(obj);
        toppingRepository.flush();
        Assert.assertNull("Failed to remove 'Topping' with identifier '" + id
                + "'", toppingRepository.findOne(id));
    }
}
