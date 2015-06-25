package org.ednovo.gooru.infrastructure.persistence.hibernate;

import java.util.List;
import java.util.Map;

import org.ednovo.gooru.core.api.model.UserClass;
import org.ednovo.gooru.core.constant.ConstantProperties;
import org.ednovo.gooru.core.constant.ParameterProperties;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class ClassRepositoryHibernate extends BaseRepositoryHibernate implements ClassRepository, ConstantProperties, ParameterProperties {

	private static final String GET_CLASSES = "select class_uid as classUid,name, user_group_code as classCode, minimum_score as minimumScore, visibility, username, gooru_uid as gooruUId, image_path as thumbnail, gender, member_count as memberCount, cc.gooru_oid as courseGooruOid from class c inner join user_group ug  on ug.user_group_uid = c.class_uid inner join party p on p.party_uid = ug.user_group_uid inner join  user on  created_by_uid = gooru_uid inner join profile pr on pr.user_uid = gooru_uid inner join content cc on cc.content_id = course_content_id ";

	private static final String GET_STUDY_CLASSES = "select class_uid as classUid,name, user_group_code as classCode, minimum_score as minimumScore, visibility, username, u.gooru_uid as gooruUId, image_path as thumbnail, gender, member_count as memberCount from class c inner join user_group ug  on ug.user_group_uid = c.class_uid inner join party p on p.party_uid = ug.user_group_uid inner join  user u on  created_by_uid = gooru_uid inner join profile pr on pr.user_uid = gooru_uid inner join content cc on cc.content_id = course_content_id inner join user_group_association uga on uga.user_group_uid = ug.user_group_uid where u.gooru_uid = :gooruUId order by uga.association_date desc";

	@Override
	public UserClass getClassById(String classUid) {
		Criteria criteria = getSession().createCriteria(UserClass.class);
		criteria.add(Restrictions.eq(PARTY_UID, classUid));
		@SuppressWarnings("rawtypes")
		List results = criteria.list();
		return (UserClass) (results.size() > 0 ? results.get(0) : null);
	}

	@Override
	public List<Map<String, Object>> getClasses(int limit, int offset) {
		Query query = getSession().createSQLQuery(GET_CLASSES);
		query.setFirstResult(offset);
		query.setMaxResults(limit != 0 ? (limit > MAX_LIMIT ? MAX_LIMIT : limit) : LIMIT);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return list(query);
	}

	@Override
	public List<Map<String, Object>> getClasses(String gooruUid, int limit, int offset) {
		StringBuilder sql = new StringBuilder(GET_CLASSES);
		sql.append("where gooru_uid = :gooruUId order by p.created_on desc");
		Query query = getSession().createSQLQuery(sql.toString());
		query.setParameter(GOORU_UID, gooruUid);
		query.setFirstResult(offset);
		query.setMaxResults(limit != 0 ? (limit > MAX_LIMIT ? MAX_LIMIT : limit) : LIMIT);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return list(query);
	}

	@Override
	public Map<String, Object> getClass(String classUid) {
		StringBuilder sql = new StringBuilder(GET_CLASSES);
		sql.append("where party_uid = :partyUid order by p.created_on desc");
		Query query = getSession().createSQLQuery(sql.toString());
		query.setParameter(PARTY_UID, classUid);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> results = list(query);
		return results.size() > 0 ? results.get(0) : null;
	}

	@Override
	public List<Map<String, Object>> getStudyClasses(String gooruUid, int limit, int offset) {
		Query query = getSession().createSQLQuery(GET_STUDY_CLASSES);
		query.setParameter(GOORU_UID, gooruUid);
		query.setFirstResult(offset);
		query.setMaxResults(limit != 0 ? (limit > MAX_LIMIT ? MAX_LIMIT : limit) : LIMIT);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return list(query);
	}

}