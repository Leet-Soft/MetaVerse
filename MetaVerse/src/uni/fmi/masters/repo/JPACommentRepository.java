package uni.fmi.masters.repo;

import java.util.List;

import uni.fmi.masters.entities.CommentEntity;

public class JPACommentRepository extends BaseRepository<CommentEntity>{

	public JPACommentRepository() {
		super(CommentEntity.class);
	}
	
	
	public List<CommentEntity> getCommentsByUserId(int userId){
		
		return getListBy("user", String.valueOf(userId));
		//return getListBy("user", userId + "");
		
	}

}
