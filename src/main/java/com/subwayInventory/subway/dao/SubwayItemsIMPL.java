package com.subwayInventory.subway.dao;

import java.util.List;
import org.hibernate.Session;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.subwayInventory.subway.entity.SubwayItems;


@Repository
public class SubwayItemsIMPL implements SubwayItemsDAO {

	private final EntityManager entityManager;

	/**
	 * @param entityManager
	 */
	@Autowired
	public SubwayItemsIMPL(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public List<SubwayItems> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<SubwayItems> query = currentSession.createQuery("from SubwayItems");
		return query.getResultList();
	}

	@Override
	@Transactional

	public SubwayItems findById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		return currentSession.get(SubwayItems.class, id);
	}

	@Override
	@Transactional
	public String saveOrUpdate(SubwayItems thisItem) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(thisItem);
		return  thisItem + "'s info have been saved";
	}

	@Override
	@Transactional
	public String deleteById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		SubwayItems item = currentSession.get(SubwayItems.class, id);
		currentSession.delete(item);
	return "the_message";
	}
}