package be.afelio.software_academy.spring_boot.chinook.persistence;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import be.afelio.software_academy.spring_boot.chinook.api.dto.AlbumDto;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class FindAllAlbumTest {

@Autowired ApplicationRepository repository;
	
	@Test
	public void test() {
		List<AlbumDto> expected = repository.findAllAlbumDto();
		assertNotNull(expected);
		assertEquals(347, expected.size());
    }
	
}
