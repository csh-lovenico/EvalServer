package live.bokurano.evalserver.repository;

import live.bokurano.evalserver.model.mongo.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
	List<Comment> findAllByCourseIdAndYear(String courseId, int year);
}
