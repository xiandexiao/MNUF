package mnu.bbs.domain.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import mnu.bbs.domain.dto.TodayPostDto;
import mnu.bbs.domain.dto.UserPostDto;
import mnu.bbs.domain.entity.Post;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
//mnu.bbs.domain.mapper.PostMapper.selectUserPostList

@Repository
public interface PostMapper extends BaseMapper<Post> {
	
	@Select("SELECT u.id AS 'userId',u.name AS 'userName',u.avatar AS 'userAvatar',p.id AS 'postId'," +
			"p.title AS 'postTitle',p.visited AS 'postVisited',p.commentCount AS 'commentCount' " +
			"FROM post p,user u WHERE p.userId = u.id ORDER BY p.visited DESC LIMIT #{record}")
	List<UserPostDto> selectHotPosts(@Param("record") int record);
	
	/**
	 *
	 */
	@Select("SELECT u.id AS 'userId',p.id AS 'postId',u.avatar AS 'userAvatar',u.`name` AS 'userName'," +
			"p.title AS 'postTitle',p.visited AS 'postVisited',p.url AS 'postUrl',p.content AS 'postContent'," +
			"p.commentCount AS 'commentCount',p.createTime AS 'createTime'" +
			" FROM user u,post p WHERE p.userId = u.id and p.id = #{id};")
	UserPostDto selectPostById(@Param("id") Integer id);
	
	/**
	 * 查找今日发布的贴子
	 */
	@Select("select p.id AS 'postId',u.id AS 'userId',p.title AS 'title',p.createTime AS 'createTime',u.name AS 'name' " +
			"from post p,user u where to_days(p.createTime) = to_days(now()) AND p.userId = u.id;")
	List<TodayPostDto> selectTodayPost();
	
	/**
	 * 查找特定用户发布的所有贴子并最新时间排序
	 */
	@Select("SELECT p.* FROM post p,user u WHERE p.userId=u.id AND u.id = #{id} ORDER BY p.createTime DESC")
	List<Post> selectPostByUserId(@Param("id") Integer id);
	
	/**
	 * 查询所有贴子包含发帖人信息
	 */
	@Select("select p.id AS 'postId',u.id AS 'userId',p.title AS 'postTitle',p.visited AS 'postVisited',p.commentCount," +
			"p.createTime,p.updateTime,p.type AS 'postType',u.name AS 'userName',u.avatar AS 'userAvatar',p.url AS 'postUrl'," +
			"p.content AS 'postContent' from post p,user u WHERE p.userId = u.id;")
	List<UserPostDto> selectAllPost();
	
	/**
	 * 查询最新发布的贴子，并可以限制条数
	 */
	@Select("select p.id AS 'postId',u.id AS 'userId',p.title AS 'postTitle',p.visited AS 'postVisited',p.commentCount," +
			"p.createTime,p.updateTime,p.type AS 'postType',u.name AS 'userName',p.url AS 'postUrl'," +
			"p.content AS 'postContent' from post p,user u WHERE p.userId = u.id ORDER BY p.createTime DESC LIMIT #{record};")
	List<UserPostDto> selectNewPost(@Param("record") int record);
	
	/**
	 * <p>
	 * 查询 : 根据stype类型查询板块列表，分页显示
	 * </p>
	 *
	 * @param page
	 *            翻页对象，可以作为 xml 参数直接使用，传递参数 Page 即自动分页
	 * @param type
	 *
	 */
	/*@Select("select p.id AS 'postId',u.id AS 'userId',p.title AS 'postTitle',p.visited AS 'postVisited'," +
			"p.commentCount,p.createTime,p.updateTime,p.type AS 'postType',u.name AS 'userName',u.avatar AS 'userAvatar'," +
			"p.url AS 'postUrl',p.content AS 'postContent' from post p,user u WHERE p.userId = u.id AND p.type=#{type}")*/
}
