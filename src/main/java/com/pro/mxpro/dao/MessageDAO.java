package com.pro.mxpro.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.pro.mxpro.vo.MessageVO;

@Repository("messageDAO")
public class MessageDAO {
	
	@Resource
	SqlSession sqlSession;
	
	private static final String NAMESPACE = "com.pro.mxpro.mybatis.MessageMapper";
	
	public int countMessage(int mymsgId){
		return sqlSession.selectOne(NAMESPACE + ".countMessage",mymsgId);
	}
	
	public List<MessageVO> getMessage(Map<String, Object> map){
		return sqlSession.selectList(NAMESPACE + ".getMessage",map);
	}

	public int getUserId(String nickname) {
		return sqlSession.selectOne(NAMESPACE + ".getUserId",nickname);
	}

	public int chkMymsg(MessageVO messageVO) {
		Integer mymsgId = sqlSession.selectOne(NAMESPACE + ".chkMymsg",messageVO);
		return mymsgId == null ? 0 : mymsgId ;
	}

	public void newMymsg(MessageVO messageVO) {
		sqlSession.selectOne(NAMESPACE + ".newMymsg",messageVO);
	}

	public List<MessageVO> getAllMsg(int id) {
		return sqlSession.selectList(NAMESPACE + ".getAllMsg",id);
	}

	public void insertMessage(MessageVO messageVO) {
		sqlSession.insert(NAMESPACE + ".insertMsg",messageVO);
	}

}
