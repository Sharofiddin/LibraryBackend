package my.projects.library.services;

import java.util.List;

import org.springframework.stereotype.Service;

import my.projects.library.beans.Publisher;
import my.projects.library.db.MyBatisHelper;
@Service
public class PublisherService {

	List<Publisher> findAll(){
		return new MyBatisHelper().selectList("selectPublishers");
	}
}