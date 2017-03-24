package citi.serviceImpl;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import citi.service.StationeryService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class StationeryServiceImplTest {
	@Resource
	StationeryService stationeryServiceImpl;
	@Test
	public void testGetStationery() {
		assertEquals(1, stationeryServiceImpl.getStationery());
		
	}

}
