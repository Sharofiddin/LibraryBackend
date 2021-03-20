package my.projects.library.services;

import java.util.List;

import org.springframework.stereotype.Service;

import my.projects.library.beans.Publisher;
import my.projects.library.db.MyBatisHelper;

@Service
public class PublisherService {

	public List<Publisher> findAll() {
		return new MyBatisHelper().selectList("selectPublishers");
	}

	public void insertPublisher(Publisher publisher) {
		new MyBatisHelper().insert("insertPublisher", publisher);
	}

	public Boolean updatePublisher(Publisher publisher){
		int count = new MyBatisHelper().update("updatePublisher", publisher);
		return count > 0;
	}

	public Boolean deletePublisher(Long id){
		int count = new MyBatisHelper().update("deletePublisher", id);
		return count > 0;
	}
}
